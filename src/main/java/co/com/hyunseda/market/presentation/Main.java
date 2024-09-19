package co.com.hyunseda.market.presentation;

import co.com.hyunseda.market.access.IDataBaseManager;
import co.com.hyunseda.market.access.DataBaseManager;
import co.com.hyunseda.market.service.CategoryService;
import co.com.hyunseda.market.service.ICategoryService;

import co.com.hyunseda.market.service.IProductValidator;
import co.com.hyunseda.market.service.IProductService;
import co.com.hyunseda.market.service.ProductService;
import co.com.hyunseda.market.service.ProductValidator;

/**
 *
 * @author Libardo Pantoja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IDataBaseManager dbManager = new DataBaseManager();
        IProductValidator validator = new ProductValidator();
        IProductService productService = new ProductService(dbManager,validator);
        ICategoryService categoryService = new CategoryService(dbManager);
        
        GUIProducts instance = new GUIProducts(productService, null);
        instance.setVisible(true);
    }
}
