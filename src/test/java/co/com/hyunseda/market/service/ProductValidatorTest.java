package co.com.hyunseda.market.service;

import co.com.hyunseda.market.domain.Product;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for ProductValidator.
 */
public class ProductValidatorTest {

    private ProductValidator productValidator;

    @Before
    public void setUp() {
        productValidator = new ProductValidator();
    }

    @Test
    public void testIsValid_withValidProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Test Product");

        // Act
        boolean result = productValidator.isValid(product);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsValid_withNullProduct() {
        // Arrange
        Product product = null;

        // Act
        boolean result = productValidator.isValid(product);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsValid_withEmptyProductName() {
        // Arrange
        Product product = new Product();
        product.setName("");

        // Act
        boolean result = productValidator.isValid(product);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsValid_withWhitespaceProductName() {
        // Arrange
        Product product = new Product();
        product.setName("   ");  // Name contains only spaces

        // Act
        boolean result = productValidator.isValid(product);

        // Assert
        assertFalse(result);
    }
}