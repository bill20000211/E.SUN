package com.javabycode.springboot.Presentation;

import com.javabycode.springboot.model.users;
import com.javabycode.springboot.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register_form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new users());
        return "registration-form"; // 返回一个视图名称，用于显示注册表单
    }

    @PostMapping("/register_process")
    public String processRegistrationForm(@ModelAttribute("user") users user, @RequestParam("confirm_password") String confirmPassword) {

        logger.info("Processing registration form for: {}", user);
        logger.info("Confirm password: {}", confirmPassword);

        logger.info("User Phonenumber: {}", user.getphone_number());
        logger.info("User Password: {}", user.getpassword_hash());
        logger.info("User Name: {}", user.getuser_name());
        
        if (!user.getpassword_hash().equals(confirmPassword)) {
            // 如果不匹配，设置错误消息并返回注册表单
            return "registration-form";
        }

        // 处理注册逻辑
        boolean result = userService.Service_user_register(user);

        if (!result) {
            // 注册失败，返回注册表单
            return "registration-form";
        }
        return "user_login"; // 返回一个视图名称，用于显示注册成功页面
    }

    @GetMapping("/user_login")
    public String user_login(Model model) {
        model.addAttribute("user", new users());
        return "user_login";
    }

    @PostMapping("/user_login_process")
    public String user_login_process(@ModelAttribute("user") users user, HttpSession session) {
        logger.info("Processing login form for: {}", user);
        logger.info("User Phonenumber: {}", user.getphone_number());
        logger.info("User Password: {}", user.getpassword_hash());
        
        boolean result = userService.Service_user_login(user, session);

        logger.info("Session: {}", session.getAttribute("loggedInUser"));

        if (!result) {
            // 登入失敗，返回登入失敗
            return "user_login";
        }
        else {
            return "redirect:/book_inventory"; // 進入書籍借用畫面，顯示可以書籍借用情況
        }
    }

    @RequestMapping("/user_query")
    public String user_query(Model model) {
        List<users> userList = userService.queryAllUsers();
        model.addAttribute("users", userList);
        return "user_query";
        
    }
}