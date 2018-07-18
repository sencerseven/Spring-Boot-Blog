package com.sencerseven.blog.bootstrap;

import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.domain.Users;
import com.sencerseven.blog.repository.CategoryRepository;
import com.sencerseven.blog.repository.PostRepository;
import com.sencerseven.blog.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PostBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    CategoryRepository categoryRepository;
    PostRepository postRepository;
    UsersRepository usersRepository;

    public PostBootstrap(CategoryRepository categoryRepository, PostRepository postRepository, UsersRepository usersRepository) {
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        categoryRepository.saveAll(categoryList());
        postRepository.saveAll(postList());

    }

    public List<Category> categoryList(){
        List<Category> categories = new ArrayList<>();

        Category category = new Category();
        category.setName("teknoloji");
        category.setDescription("teknoloji");
        category.setUrl("teknoloji");
        category.setActive(1);
        categories.add(category);

        return categories;

    }

    public List<Post> postList(){

        Optional<Category> categoryOptional = categoryRepository.findById(1L);
        Optional<Users> usersOptional = usersRepository.findById(1L);

        if(!categoryOptional.isPresent())
            return null;

        List<Post> postList = new ArrayList<>();

        Post post = new Post();
        post.setTitle("akıllı telefon");
        post.setDescription("akıllı telefon desc");
        post.setUrl("akilli-telefon");
        post.setImageUrl("http://localhost:8080/images/blog/blog-1.jpg");
        post.setText("<p>Donec eleifend accumsan nibh eu efficitur. Vivamus lacinia ut turpis egestas convallis. Quisque nec\n" +
                "                    accumsan justo. Maecenas auctor in nulla nec tincidunt. Pellentesque rutrum molestie tortor, ut egestas\n" +
                "                    risus commodo a. Praesent a orci nec libero fringilla euismod eu id massa. Nunc eget bibendum odio, sed\n" +
                "                    sodales eros.Vivamus lacinia, mi eu ultrices mattis.</p>\n" +
                "\n" +
                "                <blockquote class=\"blockquote text-center\">\n" +
                "                    <h6 class=\"mb-0 blockquote-text\">Praesent consectetur vel dui sed molestie. Aliquam imperdiet cursus\n" +
                "                        dapibus. Quisque vestibulum blandit tellus, nec volutpat turpis. Lorem ipsum dolor sit amet,\n" +
                "                        consectetur adipiscing elit. Integer posuere erat a ante.</h6>\n" +
                "                    <footer class=\"blockquote-footer mt-2\">Someone famous in <cite title=\"Source Title\">Source Title</cite>\n" +
                "                    </footer>\n" +
                "                </blockquote>\n" +
                "\n" +
                "                <p>Quisque nec accumsan justo. Maecenas auctor in nulla nec tincidunt. Pellentesque rutrum molestie tortor,\n" +
                "                    ut egestas risus commodo a. Praesent a orci nec libero fringilla euismod eu id massa. Nunc eget bibendum\n" +
                "                    odio, sed sodales eros.Vivamus lacinia, mi eu ultrices mattis.</p>");
        post.setCategory(categoryOptional.get());
        post.setUsers(usersOptional.get());
        post.setCreatedAt(new Date());

        postList.add(post);

        return postList;


    }


}
