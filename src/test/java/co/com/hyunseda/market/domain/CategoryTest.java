/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package co.com.hyunseda.market.domain;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author earea
 */
public class CategoryTest {
    
    public CategoryTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getCategoryId method, of class Category.
     */
    @Test
    public void testGetCategoryId() {
        System.out.println("getCategoryId");
        Category instance = new Category();
        Long expResult = null;
        Long result = instance.getCategoryId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategoryId method, of class Category.
     */
    @Test
    public void testSetCategoryId() {
        System.out.println("setCategoryId");
        Long categoryId = null;
        Category instance = new Category();
        instance.setCategoryId(categoryId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Category.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Category instance = new Category();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Category.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Category instance = new Category();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
