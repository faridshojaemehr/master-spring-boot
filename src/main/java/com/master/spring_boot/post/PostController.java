package com.master.spring_boot.post;

import com.master.spring_boot.jsonplaceholder.JsonPlaceholderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private JsonPlaceholderService jsonPlaceholderService;
    public PostController(JsonPlaceholderService jsonPlaceholderService) {
        this.jsonPlaceholderService = jsonPlaceholderService;
    }

    @GetMapping
    public List<Post> getPosts() {
        return jsonPlaceholderService.getPosts();
    }

    @GetMapping("{id}")
    public Post getPostsById(@PathVariable("id") Integer id) {
        return jsonPlaceholderService.getPostsById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
       return   jsonPlaceholderService.createPost(post);
    }

    @PutMapping("{id}")
    public Post updatePost(@PathVariable("id") Integer id,@RequestBody Post post) {
        return jsonPlaceholderService.updatePost(id,post);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") Integer id) {
         jsonPlaceholderService.deletePostById(id);
    }


}
