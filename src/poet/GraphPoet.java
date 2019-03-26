/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
	private BufferedReader re;
	
    
    // Abstraction function:
	
    //A graph which basically takes a string as input and generates a graph based on the pair of words that are adjacent 
	//to each other. It also generates poems by finding bridges between words.
    // Representation invariant:
    // The vertices of the graph are the strings and their weights are according to the adjacency.
	// All vertices are lowercase words.
    // Safety from rep exposure:
    // graph is final.
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
    	re = new BufferedReader(new FileReader(corpus));
    	String prevWord = null;
    	String l = re.readLine();
    	while(l!=null) {
    		String w[] = l.split("\\s");
    		for(String s: w) {
    			String curr = s;
    			if(prevWord!=null) {
    				Integer n = graph.targets(curr).get(prevWord);
    				if(n==null) {n = 0;}
    				graph.set(curr, prevWord, n+1);
    			}
    			prevWord = curr;
    		}
    	}
    	
    	checkRep(corpus);
    }
   
    // TODO checkRep
    public void checkRep(File given) {
    	assert given != null;
    }
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
    	ArrayList<String> output = new ArrayList<String>();
    	String w[] = input.split("\\s");
    	String pword = null;
    	String bridge = "";
    	
    	for(String s: w) {
    		if(pword!=null) {
    			bridge = bridge(s,pword);
    			if(bridge.length()>0) {output.add(bridge);}}
    		
    		output.add(s);
    		pword = s;
    		}
    	return String.join(" ",output);
    }

	private String bridge(String sou, String tar) {
		String source = sou.toLowerCase();
		String target = tar.toLowerCase();
		String bridge = "";
		Map<String, Integer> temp= graph.targets(source);
		for (Map.Entry<String, Integer> x1 : temp.entrySet()) {
			String br  = x1.getKey();
			Map<String, Integer> temp2  = graph.targets(br);
			for(Map.Entry<String,Integer>x2: temp2.entrySet()) {
				String s3 = x2.getKey();
				if(s3.equals(target)) {
					bridge = br;
				}
			}}	
		return bridge;
					
	}
    
	public String toString() {
		return graph.toString();
	}
    
}
