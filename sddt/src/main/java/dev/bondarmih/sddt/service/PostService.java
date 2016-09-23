package dev.bondarmih.sddt.service;

import dev.bondarmih.sddt.entity.Post;
import dev.bondarmih.sddt.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bondarm on 22.09.16.
 */

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Iterable<Post> getAll() {
        return postRepository.findAll();
    }
}
