package com.example.proyectoFormativo.Seeds;

import com.example.proyectoFormativo.Model.Category;
import com.example.proyectoFormativo.Repository.ICategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component
public class CategorySeeder implements CommandLineRunner {

    private final ICategoryRepository categoryRepository;

    public CategorySeeder(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<String> category = Arrays.asList("Terror", "Suspenso", "Manga", "Fantasia");

        for (String categoryName: category){
            categoryRepository.findByName(categoryName).ifPresentOrElse(
                    response -> System.out.println("Category existente " + response.getName()),
                    () -> {
                        Category category1 = new Category();
                        category1.setName(categoryName);
                        categoryRepository.save(category1);
                        System.out.println("Categoria creada: " + categoryName);
                    }
            );
        }
    }
}
