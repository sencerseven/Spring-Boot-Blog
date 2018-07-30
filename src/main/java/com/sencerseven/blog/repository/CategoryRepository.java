package com.sencerseven.blog.repository;

import com.sencerseven.blog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Set<Category> findCategoriesByActive(boolean status);

    Optional<Category> findCategoriesByUrl(String url);



    @Modifying
    @Query(value = "delete from Category c where c.id = ?1")
    void deleteCategoryById(Long id);

}
