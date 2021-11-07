package com.bsuir.counter.repo;

import com.bsuir.counter.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
