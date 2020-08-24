package com.example.springbootshirodemo.dao;

import com.example.springbootshirodemo.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
