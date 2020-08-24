package com.example.springbootshirodemo;

import com.example.springbootshirodemo.bean.Permission;
import com.example.springbootshirodemo.bean.Role;
import com.example.springbootshirodemo.bean.User;
import com.example.springbootshirodemo.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class SpringbootShiroDemoApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Test
    void contextLoads() {
        User user = userRepository.getByUsername("csdn1");
        Set<String> roles = new HashSet<>();
        for (Role role : user.getRoles()){
            roles.add(role.getRolename());
            System.out.println(role.getRolename());
        }
        Set<String> permissions = userRepository.getPermissions(roles);
        for (String str : permissions){
            System.out.println(str);
        }
        /*Set<Role> roles = new HashSet<>();
        Set<Permission> permissions = new HashSet<>();
        //permissions.add(new Permission("user:*"));
        //permissions.add(new Permission("student:*"));
        //roles.add(new Role("admin",permissions));
        //roles.add(new Role("teacher",permissions));
        roles.add(new Role("student"));
        //User user1 = new User("csdn1","123456",roles);
        //User user2 = new User("csdn2","123456",roles);
        User user3 = new User("csdn3","123456",roles);
        //userRepository.save(user1);
        //userRepository.save(user2);
        userRepository.save(user3);*/


    }

}
