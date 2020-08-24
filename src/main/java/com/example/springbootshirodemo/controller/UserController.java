package com.example.springbootshirodemo.controller;

import com.example.springbootshirodemo.bean.Permission;
import com.example.springbootshirodemo.bean.Role;
import com.example.springbootshirodemo.bean.User;
import com.example.springbootshirodemo.dao.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    /**
     * 身份认证测试接口
     * @param request
     * @return
     */
    @RequestMapping("/admin")
    public String admin(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        return "success";
    }

    /**
     * 角色认证测试接口
     * @param request
     * @return
     */
    @RequestMapping("/student")
    public String student(HttpServletRequest request) {
        return "success";
    }

    /**
     * 权限认证测试接口
     * @param request
     * @return
     */
    @RequestMapping("/teacher")
    public String teacher(HttpServletRequest request) {
        return "success";
    }
    /**
     * 用户登录接口
     * @param user user
     * @param request request
     * @return string
     */
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) {

        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        try{
            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            request.getSession().setAttribute("user", user);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            request.getSession().setAttribute("user", user);
            request.setAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletResponse response){
        return "index";
    }

    /*@RequestMapping("/saveuser")
    public void saveuser(){
        Permission permission = new Permission("查询");
        Permission permission1 = new Permission("删除");
        Set<Permission> permissions = new HashSet<>();
        permissions.add(permission);
        permissions.add(permission1);
        Permission permission2 = new Permission("更新");
        Permission permission3 = new Permission("增加");
        Set<Permission> permissions1 = new HashSet<>();
        permissions1.add(permission2);
        permissions1.add(permission3);
        Role role = new Role("test1",permissions);
        Role role1 = new Role("test2",permissions1);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        roles.add(role1);
        User user = new User("csdn3","123456",roles);
        System.out.println(user);
        userRepository.save(user);
    }*/

}
