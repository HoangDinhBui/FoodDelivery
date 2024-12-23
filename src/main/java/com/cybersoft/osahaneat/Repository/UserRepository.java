package com.cybersoft.osahaneat.Repository;

import com.cybersoft.osahaneat.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByUsername(String userName);
}
