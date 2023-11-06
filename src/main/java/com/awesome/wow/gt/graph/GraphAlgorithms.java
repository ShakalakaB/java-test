package com.awesome.wow.gt.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        Set<Vertex<T>> visitedSet = new HashSet<>();
        List<Vertex<T>> vertexList = new ArrayList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.offer(start);
        visitedSet.add(start);
        vertexList.add(start);

        while (queue.peek() != null) {
            Vertex<T> vertex = queue.poll();

            for (VertexDistance<T> vertexDistance : graph.getAdjList().get(vertex)) {
                if (!visitedSet.contains(vertexDistance.getVertex())) {
                    visitedSet.add(vertexDistance.getVertex());
                    vertexList.add(vertexDistance.getVertex());
                    queue.offer(vertexDistance.getVertex());
                }
            }
        }
        return vertexList;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     *
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     *
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        List<Vertex<T>> vertexList = new ArrayList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        dfs(start, graph, visitedSet, vertexList);
        return vertexList;
    }

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin Prims on.
     * @param graph The graph we are applying Prims to.
     * @return The MST of the graph or null if there is no valid MST.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Set<Edge<T>> mst = new HashSet<>();
        PriorityQueue<Edge<T>> queue = new PriorityQueue<>(Comparator.comparing(Edge::getWeight));

        visitedSet.add(start);
        for (VertexDistance<T> vertexDistance : graph.getAdjList().get(start)) {
            Edge<T> edge = new Edge<>(start, vertexDistance.getVertex(), vertexDistance.getDistance());
            queue.offer(edge);
        }


        while (queue.peek() != null && visitedSet.size() < graph.getVertices().size()) {
            Edge<T> edge = queue.poll();

            if (visitedSet.contains(edge.getV())) {
                continue;
            }

            Edge<T> reverseEdge = new Edge<>(edge.getV(), edge.getU(), edge.getWeight());
            visitedSet.add(edge.getV());
            mst.add(edge);
            mst.add(reverseEdge);
            for (VertexDistance<T> vertexDistance : graph.getAdjList().get(edge.getV())) {
                if (!visitedSet.contains(vertexDistance.getVertex())) {
                    Edge<T> edgeItem = new Edge<>(edge.getV(), vertexDistance.getVertex(), vertexDistance.getDistance());
                    queue.offer(edgeItem);
                }
            }
        }

       if (mst.size() != 2 * (graph.getVertices().size() - 1)) {
           return null;
       } else {
           return mst;
       }

    }

    private static <T> void dfs(Vertex<T> vertex, Graph<T> graph, Set<Vertex<T>> visitedSet, List<Vertex<T>> vertexList) {
        visitedSet.add(vertex);
        vertexList.add(vertex);
        for (VertexDistance<T> vertexDistance : graph.getAdjList().get(vertex)) {
            if (!visitedSet.contains(vertexDistance.getVertex())) {
                dfs(vertexDistance.getVertex(), graph, visitedSet, vertexList);
            }
        }
    }
}
