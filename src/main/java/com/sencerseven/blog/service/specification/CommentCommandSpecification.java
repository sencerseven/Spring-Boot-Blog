package com.sencerseven.blog.service.specification;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.domain.Comment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;

import static org.springframework.data.jpa.domain.Specification.where;

@Component
public class CommentCommandSpecification extends BaseSpecification<Comment,CommentCommand> {

    @Override
    public Specification<Comment> getFilter(CommentCommand request) {
        return (root,query,cb) -> {
            query.distinct(true);
            return where(
                    where(containsAttribute("text",request.getText()))

            ).and(equalAttribute("active",request.isActive())
            ).toPredicate(root,query,cb);
        };
    }


    private Specification<Comment> containsAttribute(String attribute,String value){
        return (root,query,cb) -> {
            if(value == null){
                return null;
            }
            Predicate specification =  cb.like(root.get(attribute),contains(value));
            return specification;
        };
    }

    private Specification<Comment> equalAttribute(String attribute,boolean value){
        return (root,query,cb) -> {
            if(Boolean.valueOf(value) == null){
                return null;
            }
            Predicate specification =  cb.equal(root.get(attribute),value);
            return specification;
        };
    }
}
