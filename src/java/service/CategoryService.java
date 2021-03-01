/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.CategoryDAO;
import entity.TblCategory;
import java.util.List;

/**
 *
 * @author TiTi
 */
public class CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }

    public List<TblCategory> getAllCategories() {
        return categoryDAO.getAllCategories();
    }
    
    public TblCategory getCategory(int id) {
        return categoryDAO.getCategory(id);
    }
}
