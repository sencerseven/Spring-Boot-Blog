package com.sencerseven.blog.repository;

import com.sencerseven.blog.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
