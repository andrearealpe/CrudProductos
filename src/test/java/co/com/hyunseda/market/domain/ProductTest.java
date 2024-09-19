package co.com.hyunseda.market.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase Product.
 */
public class ProductTest {

    @Test
    public void testGetProductId() {
        System.out.println("getProductId");
        Product instance = new Product();
        Long expResult = 1L;  // Valor esperado
        instance.setProductId(expResult);
        Long result = instance.getProductId();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetProductId() {
        System.out.println("setProductId");
        Long productId = 2L;
        Product instance = new Product();
        instance.setProductId(productId);
        assertEquals(productId, instance.getProductId());
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        Product instance = new Product();
        String expResult = "Test Product";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "New Product";
        Product instance = new Product();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Product instance = new Product();
        String expResult = "This is a test description";
        instance.setDescription(expResult);
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "Another description";
        Product instance = new Product();
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        Product instance = new Product();
        Category expResult = new Category();
        expResult.setCategoryId(1L);
        expResult.setName("Electronics");
        instance.setCategory(expResult);
        Category result = instance.getCategory();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        Category category = new Category();
        category.setCategoryId(2L);
        category.setName("Clothing");
        Product instance = new Product();
        instance.setCategory(category);
        assertEquals(category, instance.getCategory());
    }
}
