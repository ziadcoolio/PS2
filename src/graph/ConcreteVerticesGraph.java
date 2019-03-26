/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices;
    
    // Abstraction function:
    //   represents the vertices of the graph along with its edges
    // Representation invariant:
    //   vertice cannot be empty 
    // Safety from rep exposure:
    //   the vertice is private and final and cannot be changed from outside the code. 
    
    // TODO constructor
    public ConcreteVerticesGraph() {
    	vertices= new ArrayList<>();
    }
    // TODO checkRep
    
    @Override public boolean add(String vertex) {
    	return vertices.add(new Vertex(vertex));
    }
    
    @Override public int set(String source, String target, int weight) {
    	for(Vertex v: vertices) {
    		if(v.getVertice()==source) {
    			return v.addEdge(target, weight);
    		}
    	}
    	return -1;
    }
    
    @Override public boolean remove(String vertex) {
    	for(Vertex v: vertices) {
    		if(v.getVertice()==vertex) {
    			vertices.remove(v);
    			return true;
    		}
    	}
    	return false;
    }
    
    @Override public Set<String> vertices() {
    	Set<String> vertice = null;
        for(Vertex v: vertices) {
        	vertice.add(v.getVertice());
    	}
        return vertice;
    }
    
    @Override public Map<String, Integer> sources(String target) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(Vertex v: vertices) {
    		for (Entry<String, Integer> entry : v.getEdges().entrySet()) {
        	    String key = entry.getKey();
        	    Integer weight = entry.getValue();
        	    if(key==target) {
        			map.put(v.getVertice(), weight);
        		}
        	}
    		
    	}
    	return map;
    }
    
    @Override public Map<String, Integer> targets(String source) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(Vertex v: vertices) {
    		if(v.getVertice()==source) {
    			map.putAll(v.getEdges());
    		}    		
    	}
    	return map;
    }
    
    // TODO toString()
    @Override public String toString() {
    	String graph="";
    	for(Vertex v: vertices) {
    		graph+=v.toString() + "\n";
    	}
    	return graph;
    }
    
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex {
    
    // TODO fields
	private String vertice;
	Map<String, Integer> edges;
    
    // Abstraction function:
    //   Represents the vertex of the graph and all the othervertices it is connected to with the weight. 
    // Representation invariant:
    //   the vertice should not be empty
    // Safety from rep exposure:
    //   the vertice and the edges list are private so cannot be changed from outside.
    
    // TODO constructor
	public Vertex(String vertice) {
    	this.vertice = vertice;
    	this.edges = new HashMap<String, Integer>();
    	checkRep();
    }
	
    public Vertex(String vertice, Map<String, Integer> edges) {
    	this.vertice = vertice;
    	this.edges = new HashMap<String, Integer>();
    	this.edges = edges;
    	checkRep();
    }
    // TODO checkRep
    private void checkRep() {
    	assert vertice !="";
    	}
    // TODO methods
    
    public String getVertice() {
    	return vertice;
    }
    
    public Map<String, Integer> getEdges() {
    	return edges;
    }
    
    public Integer addEdge(String key, Integer weight) {
    	return edges.put(key, weight);
    }
    
    // TODO toString()
    public String toString() {
    	String edge = "";
    	for (Entry<String, Integer> entry : edges.entrySet()) {
    	    String key = entry.getKey();
    	    Integer weight = entry.getValue();
    	    edge+= "target: " + key + " weight: " + weight + " "; 
    	}
    	return "vertice: " + vertice + "edges: " + edge;
    }
}
