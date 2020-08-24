package com.example.springbootshirodemo.dao;

import com.example.springbootshirodemo.bean.Role;
import com.example.springbootshirodemo.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S s);

    @Query(value = "select * from user where username = ?1 ", nativeQuery = true)
    User getByUsername(String name);

    /*@Query(value = "select * from user where username = ?1 ", nativeQuery = true)
    Role getRoles(String name);*/

    @Query(value = "select permissionname from permission where id in " +
            "(select permission_id from role_permission where role_id in " +
            "(select id from role where rolename in ?1))",nativeQuery = true)
    Set<String> getPermissions(Set<String> roles);



}
