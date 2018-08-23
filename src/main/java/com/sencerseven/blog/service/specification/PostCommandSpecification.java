package com.sencerseven.blog.service.specification;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.domain.Post;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class PostCommandSpecification extends BaseSpecification<Post, PostCommand> {


    @Override
    public Specification<Post> getFilter(PostCommand request) {
        return (root, query, cb) -> {
            query.distinct(true);

            return where(
                    equalAttribute("active", request.isActive()
                    )

            ).toPredicate(root, query, cb);
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

}
