package com.example.springbootshirodemo.dao;

import com.example.springbootshirodemo.bean.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
}
