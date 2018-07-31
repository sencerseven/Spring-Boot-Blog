package com.sencerseven.blog.repository;

import com.sencerseven.blog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {

    Optional<Tag> findTagByTagName(String tagName);
}
