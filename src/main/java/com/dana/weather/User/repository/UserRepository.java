package com.dana.weather.User.repository;

import com.dana.weather.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByIdAndPassword(String id, String password);
}
