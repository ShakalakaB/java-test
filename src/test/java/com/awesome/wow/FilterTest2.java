package com.awesome.wow;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest2 {

    @Test
    void filter() {
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
    void filter2() {
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
    void filter3() {
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
    void filter4() {
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