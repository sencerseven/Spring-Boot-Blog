package com.sencerseven.blog.repository;

import com.sencerseven.blog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Set<Category> findCategoriesByActive(int status);

}
