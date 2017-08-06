/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs143;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xuri
 */
public class SortedDecimalMapTest {
    
    SortedDecimalMap<Product> map;
    
    @Before
    public void setUp() {
         map = new SortedDecimalMap<>(5);
    }

    /**
     * Test of contains method, of class SortedDecimalMap.
     */
    @Test
    public void testContains() {
        //empty map
        assertFalse(map.contains(00001));
        
        assertTrue(map.insert(new Product(00001,"Chunked Cookie",1,1)));
        assertTrue(map.insert(new Product(10001,"Smashed Cookie",1,2)));
        
        assertTrue(map.contains(00001));
        assertTrue(map.contains(10001));
        
        //over digits
        assertFalse(map.contains(100000));
        
        //non exist
        assertFalse(map.contains(00011));
        
        //key<0
        assertFalse(map.contains(-1));
    }

    /**
     * Test of get method, of class SortedDecimalMap.
     */
    @Test
    public void testGet() {
        //empty map
        assertSame(null,map.get(10000));
        
        //over digits
        assertSame(null,map.get(100000));
        
        //add two products with different key len
        Product smashedCookie = new Product(10001,"Smashed Cookie",1,2);
        Product halfLie3 = new Product(00012,"Half-Lie 3",2,2);
        assertTrue(map.insert(smashedCookie));
        assertTrue(map.insert(halfLie3));
        
        //get the product
        assertEquals(smashedCookie,map.get(10001));
        assertEquals(halfLie3, map.get(00012));
        
        //delete the cookie and test the (empty)node
        assertTrue(map.remove(10001));
        assertSame(null, map.get(10001));
        
        //get a product with key<0
        assertSame(null,map.get(-1));
    }

    /**
     * Test of insert method, of class SortedDecimalMap.
     */
    @Test
    public void testInsert() {
        //the digitCount was set to 5
        //normal insert
        assertTrue(map.insert(new Product(10001,"Chuncked Cookie",1,2)));
        
        //over digits insertion
        assertFalse(map.insert(new Product(100001,"BreakingGood",10,1)));
        
        //duplicate(same key) insertion
        assertFalse(map.insert(new Product(10001,"Chunked Cookie",1,2)));
        
        //insert a never exist product which key is <0
        assertFalse(map.insert(new Product(-1,"huh?",Integer.MAX_VALUE,Integer.MIN_VALUE)));
    }

    /**
     * Test of remove method, of class SortedDecimalMap.
     */
    @Test
    public void testRemove() {
        //add one of my favorite food and one of my least favorite food
        assertTrue(map.insert(new Product(10000,"Chunked Cookie",1,1)));
        assertTrue(map.insert(new Product(20000,"Fried Shrimp",2,1)));
        
        //remove it
        assertTrue(map.remove(20000));
        
        //remove something that doesn't exist in the map
        assertFalse(map.remove(20001));
        
        //remove something with key length greater than the default key len
        assertFalse(map.remove(100000));
        
        //remove a never existed product with a key<0
        assertFalse(map.remove(-1));
    }

    /**
     * Test of isEmpty method, of class SortedDecimalMap.
     */
    @Test
    public void testIsEmpty() {
        //test with the empty map
        assertTrue(map.isEmpty());
        
        //insert one
        assertTrue(map.insert(new Product(99999,"Void",9,1000)));
        assertTrue(map.insert(new Product(1001,"Introduction to Java",0,2)));
        
        assertFalse(map.isEmpty());
        
        //remove and test
        assertTrue(map.remove(99999));
        assertFalse(map.isEmpty());
        assertTrue(map.remove(1001));
        assertTrue(map.isEmpty());
    }

    /**
     * Test of iterator method, of class SortedDecimalMap.
     */
    @Test
    public void testIterator() {
        for (int i = 0; i <= 9999; i++) {
            assertTrue(map.insert(new Product(i)));
        }

        Iterator<Product> it = map.iterator();

        int counter = 0;
        while (it.hasNext()) {
            Product temp = it.next();
            assertEquals(temp.getKey(), counter++);
            if (temp.getKey() == 9999) {
                it.remove();
            }
        }
        
        assertFalse(map.contains(9999));

    }

}
