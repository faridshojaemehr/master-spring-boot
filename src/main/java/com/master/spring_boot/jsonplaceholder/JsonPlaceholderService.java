package com.master.spring_boot.jsonplaceholder;

import com.master.spring_boot.post.Post;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface JsonPlaceholderService{

    @GetExchange("/posts")
    List<Post> getPosts();

    @GetExchange("/posts/{id}")
    Post getPostsById(@PathVariable("id") Integer id);

    @PostExchange("/posts")
    Post createPost(@RequestBody Post post);

    @GetExchange("/posts/{id}")
    Post updatePost(@PathVariable("id") Integer id,@RequestBody Post post);

    @DeleteExchange("/posts/{id}")
    void deletePostById(@PathVariable("id") Integer id);

}
