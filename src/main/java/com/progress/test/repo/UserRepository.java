package com.progress.test.repo;

import com.progress.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
