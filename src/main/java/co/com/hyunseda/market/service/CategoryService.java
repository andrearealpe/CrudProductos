/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.access.IDataBaseManager;
import co.com.hyunseda.market.domain.Category;
import java.util.List;


/**
 *
 * @author earea
 */
public class CategoryService implements ICategoryService{
    
    private final IDataBaseManager DB;

    public CategoryService(IDataBaseManager db) {
        this.DB = db;
    }

    @Override
    public boolean saveCategory(Category category) {
        return DB.saveCategory(category);
    }
    
    @Override
    public List<Category> findAllCategories() {
        return DB.findAllCategories();
    }

    @Override
    public Category findCategoryById(Long id) {
        return DB.findCategoryById(id);
    }

    @Override
    public boolean editCategory(Long id, Category category) {
        return DB.editCategory(id, category);
    }

    @Override
    public boolean deleteCategory(Long id) {
        return DB.deleteCategory(id);
    }

}
