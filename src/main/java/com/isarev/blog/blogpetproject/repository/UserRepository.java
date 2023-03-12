package com.isarev.blog.blogpetproject.repository;

import com.isarev.blog.blogpetproject.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsUserByEmail(String email);
    User findByEmail(String email);
}
