package co.com.hyunseda.market.service;

import co.com.hyunseda.market.access.DataBaseManager;
import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProductServiceTest {

    private DataBaseManager databaseManager;
    private ProductService productService;

    @Before
    public void setUp() {
        // Inicializar la base de datos y el servicio de productos
        databaseManager = new DataBaseManager();
        productService = new ProductService(databaseManager, new ProductValidator());
    }

    @Test
    public void testSaveProduct_Success() {
        System.out.println("saveProduct - success case");

        // Crear un producto válido
        String name = "Test Product";
        String description = "This is a test product.";
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Category 1");

        // Guardar el producto y verificar que el resultado sea true
        boolean result = productService.saveProduct(name, description, category);
        assertTrue(result);
    }

    @Test
    public void testSaveProduct_Failure_InvalidProduct() {
        System.out.println("saveProduct - failure case (invalid product)");

        // Intentar guardar un producto con nombre vacío (inválido)
        String name = "";
        String description = "This is a test product with an invalid name.";
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Category 1");

        // Guardar el producto y verificar que el resultado sea false
        boolean result = productService.saveProduct(name, description, category);
        assertFalse(result);
    }

    @Test
    public void testFindAllProducts() {
        System.out.println("findAllProducts");

        // Guardar un producto en la base de datos
        String name = "Test Product 2";
        String description = "Another test product.";
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Category 1");
        productService.saveProduct(name, description, category);

        // Verificar que haya al menos un producto en la lista de productos
        List<Product> products = productService.findAllProducts();
        assertNotNull(products);
        assertTrue(!products.isEmpty());
    }

    @Test
    public void testFindProductById() {
        System.out.println("findProductById");

        // Guardar un producto en la base de datos
        String name = "Test Product 3";
        String description = "Another test product.";
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Category 1");
        productService.saveProduct(name, description, category);

        // Encontrar el producto por su ID
        List<Product> products = productService.findAllProducts();
        Product savedProduct = products.get(0);
        Product result = productService.findProductById(savedProduct.getProductId());

        // Verificar que el producto encontrado sea el correcto
        assertNotNull(result);
        assertEquals(savedProduct.getProductId(), result.getProductId());
    }

    @Test
    public void testDeleteProduct() {
        System.out.println("deleteProduct");

        // Guardar un producto en la base de datos
        String name = "Test Product 4";
        String description = "Another test product.";
        Category category = new Category();
        category.setCategoryId(1L);
        category.setName("Category 1");
        productService.saveProduct(name, description, category);

        // Encontrar el producto por su ID
        List<Product> products = productService.findAllProducts();
        Product savedProduct = products.get(0);

        // Eliminar el producto
        boolean result = productService.deleteProduct(savedProduct.getProductId());
        assertTrue(result);

        // Verificar que el producto fue eliminado
        Product deletedProduct = productService.findProductById(savedProduct.getProductId());
        assertNull(deletedProduct);
    }
}