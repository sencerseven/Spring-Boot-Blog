package com.sencerseven.blog.service.specification;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.domain.Tag;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class PostCommandSpecification extends BaseSpecification<Post, PostCommand> {


    @Override
    public Specification<Post> getFilter(PostCommand request) {
        return (root, query, cb) -> {
            query.distinct(true);
            Join<Post,Category> joinCategory = root.join("category");
            Join<Post,Tag> joinTag = root.join("tags");
            return where(
                        where(joinAttribute(joinTag.get("tagName"),(request.getTags() != null) ? request.getTags() : null))
                        .or(containsAttribute("title",request.getTitle()))
                        .or(joinAttribute(joinCategory.get("name"),(request.getCategory() != null) ? request.getCategory().getName() : null))
                    ).and(equalAttribute("active", request.isActive())
            ).toPredicate(root, query, cb);
        };
    }

    private Specification<Post> containsAttribute(String attribute,String value){
        return (root,query,cb) -> {
            if(value == null){
                return null;
            }
            Predicate specification =  cb.like(root.get(attribute),contains(value));
            return specification;
        };
    }

    private Specification<Post> equalAttribute(String attribute, boolean value) {
        return (root, query, cb) -> {
            if (Boolean.valueOf(value) == null) {
                return null;
            }
            Predicate specification = cb.equal(root.get(attribute), value);
            return specification;
        };
    }

    private Specification<Post> joinAttribute(Path<Object> path, String value) {
        return (root, query, cb) -> {
            if (value == null) {
                return null;
            }
            Predicate specification = cb.equal(path, value);
            return specification;
        };
    }

}
