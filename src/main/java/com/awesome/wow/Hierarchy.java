package com.awesome.wow;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <p>A <tt>Hierarchy</tt> stores an arbitrary <em>forest</em> (an ordered collection of ordered trees)
 * as an array indexed by DFS-order traversal.
 * A node is represented by a unique ID.
 * Parent-child relationships are identified by the position in the array and the associated depth.
 * Tree root has depth 0, immediate children have depth 1, their children have depth 2, etc.
 * </p>
 *
 * <p>Depth of the first element is 0. If the depth of a node is D, the depth of the next node in the array can be:</p>
 * <ul>
 *   <li>D + 1 if the next node is a child of this node;</li>
 *   <li>D if the next node is a sibling of this node;</li>
 *   <li>d < D - in this case the next node is not related to this node.</li>
 * </ul>
 *
 * <p>Example:</p>
 * <code>
 * nodeIds: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * depths: 0, 1, 2, 3, 1, 0, 1, 0, 1, 1, 2
 * the forest can be visualized as follows:
 * 1
 * - 2
 * - - 3
 * - - - 4
 * - 5
 * 6
 * - 7
 * 8
 * - 9
 * - 10
 * - - 11
 * </code>
 * Note that the depth is equal to the number of hyphens for each node.
 * */
interface Hierarchy {
    int size();

    int nodeId(int index);

    int depth(int index);

    default String formatString() {
        return IntStream.range(0, size()).mapToObj(i -> "" + nodeId(i) + ":" + depth(i) ).collect(Collectors.joining(", ", "[", "]"));
    }
}

class Filter {
    /**
     * A node is present in the filtered hierarchy iff its node ID passes the predicate and all of its ancestors pass it as well.
     * */
    static Hierarchy filter(Hierarchy hierarchy, IntPredicate nodeIdPredicate) {
        List<Integer> nodeIds = new ArrayList<>();
        List<Integer> depths = new ArrayList<>();

        int lastPassDepth = -1;

        int i = 0;
        while (i < hierarchy.size()) {
            if (!nodeIdPredicate.test(hierarchy.nodeId(i))) {
                int lastFailedDepth = hierarchy.depth(i);

                i++;
                while (i < hierarchy.size()) {
                    if (hierarchy.depth(i) <= lastFailedDepth) {
                        break;
                    }
                    i++;
                }
                continue;
            }

            if (hierarchy.depth(i) == 0) {
                lastPassDepth = -1;
            }

            if (nodeIdPredicate.test(hierarchy.nodeId(i))
                    && hierarchy.depth(i) <= lastPassDepth + 1) {
                nodeIds.add(hierarchy.nodeId(i));
                depths.add(hierarchy.depth(i));
                lastPassDepth = hierarchy.depth(i);
            }
            i++;
        }

        return new ArrayBasedHierarchy(nodeIds.stream().mapToInt(num -> num).toArray(),
                depths.stream().mapToInt(num -> num).toArray());
    }
}

class ArrayBasedHierarchy implements Hierarchy {
    private final int[] myNodeIds;
    private final int[] myDepths;

    public ArrayBasedHierarchy(int[] nodeIds, int[] depths) {
        myNodeIds = nodeIds;
        myDepths = depths;
    }

    @Override
    public int size() {
        return myDepths.length;
    }

    @Override
    public int nodeId(int index) {
        return myNodeIds[index];
    }

    @Override
    public int depth(int index) {
        return myDepths[index];
    }
}

class FilterTest {
    @Test
    public void testFilter() {
        Hierarchy unfiltered = new ArrayBasedHierarchy(
                //         x  x     x  x     x  x     x   x
                new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
                new int[] {0, 1, 2, 3, 1, 0, 1, 0, 1, 1, 2}
        );
        Hierarchy filteredActual = Filter.filter(unfiltered, nodeId -> nodeId % 3 != 0);
        Hierarchy filteredExpected = new ArrayBasedHierarchy(
                new int[] {1, 2, 5, 8, 10, 11},
                new int[] {0, 1, 1, 0, 1, 2}
        );

        Assert.assertEquals(filteredExpected.formatString(), filteredActual.formatString());
    }

    @Test
    void testFilter2() {
        Hierarchy unfiltered = new ArrayBasedHierarchy(
                new int[] {6, 6, 6},
                new int[] {0, 0, 0}
        );
        Hierarchy filteredActual = Filter.filter(unfiltered, nodeId -> nodeId % 3 != 0);
        Hierarchy filteredExpected = new ArrayBasedHierarchy(
                new int[] {},
                new int[] {}
        );

        Assert.assertEquals(filteredExpected.formatString(), filteredActual.formatString());
    }

    @Test
    void testFilter3() {
        Hierarchy unfiltered = new ArrayBasedHierarchy(
                new int[] {1, 1, 1},
                new int[] {0, 0, 0}
        );
        Hierarchy filteredActual = Filter.filter(unfiltered, nodeId -> nodeId % 3 != 0);
        Hierarchy filteredExpected = new ArrayBasedHierarchy(
                new int[] {1, 1, 1},
                new int[] {0, 0, 0}
        );

        Assert.assertEquals(filteredExpected.formatString(), filteredActual.formatString());
    }

    @Test
    void testFilter4() {
        Hierarchy unfiltered = new ArrayBasedHierarchy(
                new int[] {1, 8, 4, 5, 7, 6, 3, 9, 2},
                new int[] {0, 1, 2, 3, 4, 4, 3, 2, 1}
        );
        Hierarchy filteredActual = Filter.filter(unfiltered, nodeId -> nodeId % 3 != 0);
        Hierarchy filteredExpected = new ArrayBasedHierarchy(
                new int[] {1, 8, 4, 5, 7, 2},
                new int[] {0, 1, 2, 3, 4, 1}
        );

        Assert.assertEquals(filteredExpected.formatString(), filteredActual.formatString());
    }
}
