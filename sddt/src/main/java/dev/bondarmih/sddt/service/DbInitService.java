package dev.bondarmih.sddt.service;

import dev.bondarmih.sddt.entity.Post;
import dev.bondarmih.sddt.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by bondarm on 22.09.16.
 */

@Service
public class DbInitService {

    @Autowired
    private PostRepository postRepository;

    @PostConstruct
    private void initDb() {

//        Long maxId = postRepository.findMaxId();
//        Long nextId = maxId == null ? 1 : maxId + 1;
//
//        Post post = new Post();
//        post.setMessage("This is my " + nextId + " post");
//        this.postRepository.save(post);


    }

}
