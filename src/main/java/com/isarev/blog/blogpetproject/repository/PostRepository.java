package com.isarev.blog.blogpetproject.repository;

import com.isarev.blog.blogpetproject.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
