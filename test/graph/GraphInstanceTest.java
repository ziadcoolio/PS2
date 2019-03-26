/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //     input is empty graph 
    //     compare graph made with the empty graph produced using emptyInstance()
    
    
    private static final String T = null;
	private static final String L = null;

	/**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test i will use it. GETREKT 
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    

    @Test
       public void testRemove() {
          
           assertEquals("true since the vertex gets removed",
                   true, emptyInstance().remove(L));
       }
       
       @Test
       public void testSet() {
         
           assertEquals("0 is returned since no previous edge existed",
                   0, emptyInstance().set(L,T,10));
       }
       
       @Test
       public void testVertices() {
          Set<String> set=new HashSet<String>();
          set.add("A");
          set.add("B");
          set.add("C");
           assertEquals("expected new graph to have no vertices",
                  set , emptyInstance().vertices());
       }

       @Test
       public void testAddVertices() {
           // TODO you may use, change, or remove this test
           assertEquals("expected a new graph with a single vertice",
                   true, emptyInstance().add("String"));
       }
       
       @Test
       public void testSourcesVertices() {
           // TODO you may use, change, or remove this test
       	Map<String, Integer> graph = new HashMap<String, Integer>();
       	graph.put("Farrukh",12);
       	graph.put("Hamdan", 2);
           assertEquals("expected a new graph with a single vertice",
                   graph, emptyInstance().sources("Haleema"));
       }
       
       @Test
       public void testTargetVertices() {
           // TODO you may use, change, or remove this test
       	Map<String, Integer> graph = new HashMap<String, Integer>();
       	graph.put("Ziad",10);
       	graph.put("Haleema", 12);
           assertEquals("expected a new graph with a single vertice",
                   graph, emptyInstance().sources("Farrukh"));
       }
    
}
