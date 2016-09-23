package dev.bondarmih.sddt.repository;

import dev.bondarmih.sddt.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.FROM;
import static org.hibernate.hql.internal.antlr.SqlTokenTypes.SELECT;


/**
 * Created by bondarm on 22.09.16.
 */

@RepositoryRestResource(path = "posts")
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    @Query("SELECT MAX(p.id) FROM Post p")
    Long findMaxId();

    Post findTop1ByOrderByIdDesc();
}
