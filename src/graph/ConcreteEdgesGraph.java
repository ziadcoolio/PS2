/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices;
    private final List<Edge> edges;
    
    // Abstraction function:
    //   Represent a graph i.e. all the vertices it has and their edges.
    // Representation invariant:
    //   Vertices should be non empty in order to be added. 
    // Safety from rep exposure:
    //   the lists for the vertices and the edges are private and final and so cannot be changed fronm outside. 
    
    // TODO constructor
    public ConcreteEdgesGraph() {
    	vertices = new HashSet();
    	edges = new ArrayList();
    	checkRep();
    }
    
    // TODO checkRep
    
    private void checkRep() {
     //
    }
    
    @Override public boolean add(String vertex) {
        return vertices.add(vertex);
    }
    
    @Override public int set(String source, String target, int weight) {
        Edge edge = new Edge(source, target, weight);
        int pos = edges.size();
        edges.add(edge);
        return pos;
    }
    
    @Override public boolean remove(String vertex) {
       return vertices.remove(vertex);
    }
    
    @Override public Set<String> vertices() {
        return vertices;
    }
    
    @Override public Map<String, Integer> sources(String target) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(Edge e: edges) {
    		if(e.getTarget()==target) {
    			map.put(e.getSource(), e.getWeight());
    		}
    	}
    	return map;
    }
    
    @Override public Map<String, Integer> targets(String source) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	for(Edge e: edges) {
    		if(e.getSource()==source) {
    			map.put(e.getTarget(), e.getWeight());
    		}
    	}
    	return map;
    }
    
    // TODO toString(
    @Override public String toString() {
    	String str = "";
    	for(Edge n: edges) {
    		str+=n.toString() + "\n"; 
    	}
    	return str;
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {
    
    // TODO fields
	private final String source;
	private final String target;
	private int weight;

	// Abstraction function:
    // 		represents the edge between 2 vertices and their weight 
	
    // Representation invariant:
	// 		source and target must be non-empty string
	// 		weight must be an int value and should not be null 
	
    // Safety from rep exposure:
    //   source,target are immutable since they are private and final. 
    
    // TODO constructor
	public Edge(String source, String target, int weight) {
		this.source=source;
		this.target=target;
		this.weight=weight;
		checkRep();
	}
	
	public String getTarget() {
		return target;
	}
	
	public String getSource() {
		return source;
	}
	
	public int getWeight() {
		return weight;
	}
    
    // TODO checkRep
    private void checkRep() {
    	assert source !="";
    	assert target!="";
    	}
	
    // TODO methods
    public void changeWeight(int x) {
    	weight = x;
    }
    
	
    // TODO toString()
	@Override public String toString() {
		String str = "Source: " + source + " Target: " + target + " Weight: " + weight;
		return str;
	}
    
}
