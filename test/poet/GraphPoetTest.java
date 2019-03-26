/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   We test whether it adds no bridge word first, then with one word to be added then 2.
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    @Test
    public void poetGraphTest() throws IOException {
    	File corpus = new File("D:\\ziad\\\\Sem 6\\Software Construction\\lab 7\\Lab\\ps2\\ps2\\test\\poet\\corpus.txt");
    	GraphPoet poet = new GraphPoet(corpus);
    	
    	assertEquals("These are strange", poet.poem("These are strange"));
    	
    	assertEquals("These are strange new worlds", poet.poem("These are strange worlds"));
    	
    	assertEquals("These are strange new worlds to seek new life", poet.poem("These are strange worlds to seek out new life"));
    	
    	
    }
    
}
