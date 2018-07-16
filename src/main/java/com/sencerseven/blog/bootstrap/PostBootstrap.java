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
        post.setText("Bakıyorum da geç kalmışım senli günlere, bizli günlere; eksik kalmış anılarımız, bitmiş duruyor amaçlarımız. Doğruya hangi amaca hizmet veriyor yaşamımız? Milyonlarca yalanın etrafında dönüp dolaşıyoruz.");
        post.setCategory(categoryOptional.get());
        post.setUsers(usersOptional.get());
        post.setCreatedAt(new Date());

        postList.add(post);

        return postList;


    }


}
