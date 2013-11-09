package com.github.robbmj.googleprep.datastructures.graph;

// Vertices: nodes
// Edges: links

// Discreet Graph: no two nodes are connected
// Nodes are adjacent if they are connected
// Path: the sequence of edges that connect two nodes
// Connected Graph: the is a path to every node
// self loop: is an edge that goes from a node to its self
// multi-graph: can contain parallel edges
// directed graph: if the edges have an orientation (direction)
// cyclic graph means you can make at least one cycle on the graph
// acyclic means that there is no circular path
// connected-directed graph is only connected if you can get to all nodes by following the edge directions
// the degree of a node is the number of edges coming out of it
// isomorphic - same graph with different orientation
// Planar graph - graph where no edges over lap
// Sparse graph - lots of nodes not many edges

// Adjacency Matrix: O(V^2) memory, answers is A connected to be really quickly, good for dense graphs

// Edge List: O(V^2) memory, answers what are all the nodes connected to A quickly, good for sparse graphs

public class Graph<E extends Vertex> {
	
	
}
