package com.example.blog.Service;

import com.example.blog.Api.ApiException;
import com.example.blog.Model.Blog;
import com.example.blog.Model.User;
import com.example.blog.Repository.AuthRepository;
import com.example.blog.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {


    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;


    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
    public List<Blog> getMyBlog(Integer id) {

        User user=authRepository.findUserById(id);

        return blogRepository.findAllByUser(user);
    }

    public void addBlog(Integer userId,Blog blog) {
        User user=authRepository.findUserById(userId);
        if (user==null){
            throw new ApiException("User not found");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId,Blog blog,Integer blogId) {
        User user=authRepository.findUserById(userId);
        if (user==null){
            throw new ApiException("User not found");
        }
        Blog b=blogRepository.findBlogById(blogId);
        if (b==null){
            throw new ApiException("Blog not found");
        } else if (b.getUser().getId()!= userId) {
            throw new ApiException("User id mismatch");
        }
        b.setTitle(blog.getTitle());
        b.setBody(blog.getBody());
        b.setUser(user);
        blogRepository.save(b);
    }
    public void deleteBlog(Integer userId,Integer blogId) {
        User user=authRepository.findUserById(userId);
        if (user==null){
            throw new ApiException("User not found");
        }
        Blog b=blogRepository.findBlogById(blogId);
        if (b==null){
            throw new ApiException("Blog not found");
        }else if (b.getUser().getId()!= userId) {
            throw new ApiException("User id mismatch");
        }
        blogRepository.delete(b);

    }

    public Blog getBlogById(Integer userId,Integer blogId) {
        User user=authRepository.findUserById(userId);
        if (user==null){
            throw new ApiException("User not found");
        }
        Blog b=blogRepository.findBlogById(blogId);
        if (b==null){
            throw new ApiException("Blog not found");
        }else if (b.getUser().getId()!= userId) {
            throw new ApiException("User id mismatch");
        }
        return b;
    }

    public Blog getBlogByTitle(Integer userId,String title) {
        User user=authRepository.findUserById(userId);
        if (user==null){
            throw new ApiException("User not found");
        }
        Blog b=blogRepository.findBlogByTitle(title);
        if (b==null){
            throw new ApiException("Blog not found");
        }else if (b.getUser().getId()!= userId) {
            throw new ApiException("User id mismatch");
        }
        return b;
    }
}
