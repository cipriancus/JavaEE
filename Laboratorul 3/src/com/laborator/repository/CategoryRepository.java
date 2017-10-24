package com.laborator.repository;

import com.laborator.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Category repository for storing all breeds
 * NOTE : It's a Singleton
 */
public class CategoryRepository {
    private static List<Category> allCategory;
    private static CategoryRepository categoryRepository = null;

    protected CategoryRepository() {
        allCategory = new ArrayList<Category>();
        readBreed();
    }

    public List<Category> getAllCategory() {
        return allCategory;
    }

    public Category getCategoryByName(String category) {
        for (Category iterator : allCategory) {
            if (iterator.getBreed().equals(category))
                return iterator;
        }
        return null;
    }

    public void addCategory(Category category) {
        allCategory.add(category);
    }

    public static CategoryRepository getInstance() {
        if (categoryRepository == null)
            categoryRepository = new CategoryRepository();
        return categoryRepository;
    }

    private void readBreed() {
        allCategory.add(new Category("Wirehair"));
        allCategory.add(new Category("Egyptian"));
        allCategory.add(new Category("Ragdoll"));
        allCategory.add(new Category("Scottish"));
        allCategory.add(new Category("Abyssinian"));
        allCategory.add(new Category("MaineCoon"));
        allCategory.add(new Category("British"));
        allCategory.add(new Category("Exotic"));
        allCategory.add(new Category("Stray"));
    }
}
