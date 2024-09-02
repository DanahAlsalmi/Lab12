package com.example.blog.Controller;

import com.example.blog.Model.Blog;
import com.example.blog.Model.User;
import com.example.blog.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/get-all")
    public ResponseEntity getAllBlogs() {
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    @GetMapping("/get-my")
    public ResponseEntity getMyBlog(@AuthenticationPrincipal User user){

        return ResponseEntity.status(200).body(blogService.getMyBlog(user.getId()));

    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("Successfully added");
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog , @PathVariable Integer blogId){
        blogService.updateBlog(user.getId(), blog, blogId);
        return ResponseEntity.status(200).body("Successfully updated");
    }

    @DeleteMapping("/del/{blogId}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blogId){
        blogService.deleteBlog(user.getId(), blogId);
        return ResponseEntity.status(200).body("Successfully deleted");
    }

    @GetMapping("/get-by/{id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable Integer id){
        return ResponseEntity.status(200).body(blogService.getBlogById(user.getId(), id));
    }

    @GetMapping("/get-by-title/{title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user, @PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(user.getId(), title));
    }
}
