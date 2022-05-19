package com.rinfotek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {//pass Long ,from user class

@Query("Select u from User u where u.email = ?1")
User findByEmail(String email);
}
