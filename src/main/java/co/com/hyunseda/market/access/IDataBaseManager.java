package co.com.hyunseda.market.access;

import co.com.hyunseda.market.domain.Category;
import co.com.hyunseda.market.domain.Product;
import java.util.List;

/**
 *
 * @author earea
 */

public interface IDataBaseManager {
    
    // CRUD para productos
    boolean save(Product product);
    List<Product> findAll();
    Product findById(Long id);
    List<Product> findByName(String name);
    List<Product> findByCategory(Long category);
    boolean edit(Long id, Product product, Category category);
    boolean delete(Long id);
    
    // CRUD para categorías
    boolean saveCategory(Category category);
    List<Category> findAllCategories();
    Category findCategoryById(Long id);
    boolean editCategory(Long id, Category category);
    boolean deleteCategory(Long id);
}
