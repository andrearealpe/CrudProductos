/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.hyunseda.market.service;

import co.com.hyunseda.market.domain.Category;
import java.util.List;

/**
 *
 * @author earea
 */
public interface ICategoryService {
    boolean saveCategory(Category category);    
    List<Category> findAllCategories();  
    Category findCategoryById(Long id); 
    boolean editCategory(Long id, Category category);  
    boolean deleteCategory(Long id); 
}
