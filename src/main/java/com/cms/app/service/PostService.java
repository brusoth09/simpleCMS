package com.cms.app.service;

import com.cms.app.dao.PostDao;
import com.cms.app.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by burusothman on 8/31/16.
 */
@Service(value = "postService")
public class PostService {
    @Autowired
    PostDao postDao;

    public void createPost(Post post) {
        String current_username = SecurityContextHolder.getContext().getAuthentication().getName();
        post.setUsername(current_username);
        postDao.save(post);
    }

    public void updatePost(Post post) {
        postDao.update(post);
    }

    public List<Post> getAll() {
        return postDao.getAll();
    }

    public List<Post> getAllByUser() {
        String current_username = SecurityContextHolder.getContext().getAuthentication().getName();
        return postDao.getAllByUser(current_username);
    }

    public Post get(int id){
        return postDao.get(id);
    }

}
