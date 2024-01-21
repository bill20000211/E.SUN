package com.javabycode.springboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabycode.springboot.model.users;
import com.javabycode.springboot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private String salt = "1234567890";
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<users> queryAllUsers() {
        logger.info("Retrieving all users");

        List<users> allUsers = userRepository.findAll(); // 您的邏輯來獲取用戶列表

        logger.info("Retrieved {} users", allUsers.size());

        return allUsers;
    }

    public boolean isPhoneNumberUnique(String phonenumber) {
        // 檢查手機號碼重複
        users existingUser = userRepository.findByphone_number(phonenumber);
        return existingUser == null;
    }

    public String hashPassword(String password, String salt) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error while hashing a password: " + e.getMessage(), e);
        }
    }

    public boolean Service_user_register(users user) {
        logger.info("Retrieving all users");

        LocalDateTime now = LocalDateTime.now();
        
        String phone_number = user.getphone_number();
        String password_hash = user.getpassword_hash();
        String user_name = user.getuser_name();
        String registration_time = now.toString();
        // String last_login_time = null;

        password_hash = hashPassword(user.getpassword_hash(), salt);

        if (isPhoneNumberUnique(phone_number)) {
            // 執行註冊操作
            // 例如，创建一个用户对象并调用 registerUser 方法
            users newUser = new users();
            newUser.setphone_number(phone_number);
            newUser.setuser_name(user_name);
            newUser.setpassword_hash(password_hash);
            newUser.setregistration_time(registration_time);
            userRepository.save(newUser);
            return true;
        } else {
            // 手机号码已存在，可以返回错误信息或采取其他适当的措施
            return false;
        }
    }

    public boolean Service_user_login(users user, HttpSession Session) {

        String phone_number = user.getphone_number();
        String password_hash = user.getpassword_hash();

        password_hash = hashPassword(user.getpassword_hash(), salt);

        users existingUser = userRepository.findByphone_number(phone_number);

        if (existingUser == null) {
            // 手机号码不存在，可以返回错误信息或采取其他适当的措施
            return false;
        }
        
        if (!existingUser.getpassword_hash().equals(password_hash)) {
            // 密碼不匹配，可以返回錯誤信息或採取其他適當的措施
            return false;
        }

        // 執行登入操作
        // 例如，更新用戶的最後登錄時間
        LocalDateTime now = LocalDateTime.now();
        existingUser.setlast_login_time(now.toString());
        userRepository.save(existingUser);

        Session.setAttribute("loggedInUser", existingUser);

        return true;
    }
}