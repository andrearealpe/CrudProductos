package co.com.hyunseda.market.service;

import co.com.hyunseda.market.domain.Product;
import co.com.hyunseda.market.access.IDataBaseManager;
import co.com.hyunseda.market.domain.Category;

import java.util.List;

/**
 *
 * @author Libardo Pantoja, Julio Hurtado
 */

public class ProductService implements IProductService {
    
    private final IDataBaseManager DB;
    private final IProductValidator productValidator;

    public ProductService(IDataBaseManager databaseManager, IProductValidator productValidator) {
        this.DB = databaseManager;
        this.productValidator = productValidator;
    }

    @Override
    public boolean saveProduct(String name, String description, Category category) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setCategory(category);

        // Validar
        if (!productValidator.isValid(newProduct)) {
            return false;
        }
        return DB.save(newProduct);
    }

    @Override
    public List<Product> findAllProducts() {
        return DB.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return DB.findById(id);
    }
    @Override
    public List<Product> findProductByName(String name) {
        return DB.findByName(name);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return DB.delete(id);
    }

    @Override
    public boolean editProduct(Long productId, Product prod, Category category) {
        // Validar
        if (!productValidator.isValid(prod)) {
            return false;
        }
        return DB.edit(productId, prod, category);
    }

    @Override
    public List<Product> findProductByCategory(Long categoryId) {
        return DB.findByCategory(categoryId);
    }

}
