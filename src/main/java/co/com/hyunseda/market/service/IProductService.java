
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import java.util.List;

/**
 *
 * @author earea
 */

public interface IProductService {
    boolean saveProduct(String name, String description, Category category);
    
    List<Product> findAllProducts();
    Product findProductById(Long id);
    List<Product> findProductByName(String name);
    public List<Product> findProductByCategory(Long categoryId);
    
    boolean deleteProduct(Long id);
    boolean editProduct(Long id, Product product,Category category);
}

