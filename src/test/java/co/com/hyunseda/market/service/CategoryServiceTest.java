/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.domain.Category;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author earea
 */
public class CategoryServiceTest {
    
    public CategoryServiceTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of saveCategory method, of class CategoryService.
     */
    @Test
    public void testSaveCategory() {
        System.out.println("saveCategory");
        Category category = null;
        CategoryService instance = null;
        boolean expResult = false;
        boolean result = instance.saveCategory(category);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllCategories method, of class CategoryService.
     */
    @Test
    public void testFindAllCategories() {
        System.out.println("findAllCategories");
        CategoryService instance = null;
        List<Category> expResult = null;
        List<Category> result = instance.findAllCategories();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCategoryById method, of class CategoryService.
     */
    @Test
    public void testFindCategoryById() {
        System.out.println("findCategoryById");
        Long id = null;
        CategoryService instance = null;
        Category expResult = null;
        Category result = instance.findCategoryById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editCategory method, of class CategoryService.
     */
    @Test
    public void testEditCategory() {
        System.out.println("editCategory");
        Long id = null;
        Category category = null;
        CategoryService instance = null;
        boolean expResult = false;
        boolean result = instance.editCategory(id, category);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCategory method, of class CategoryService.
     */
    @Test
    public void testDeleteCategory() {
        System.out.println("deleteCategory");
        Long id = null;
        CategoryService instance = null;
        boolean expResult = false;
        boolean result = instance.deleteCategory(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
