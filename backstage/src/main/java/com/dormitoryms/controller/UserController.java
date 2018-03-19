package com.dormitoryms.controller;

import com.dormitoryms.domain.User;
import com.dormitoryms.security.JwtUser;
import com.dormitoryms.service.IUserService;
import com.dormitoryms.utils.GetIp;
import com.dormitoryms.utils.ParameterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 用户管理Controller
 *
 * @author hackyo
 * Created on 2018/3/18 11:53.
 */
@CrossOrigin(origins = {"http://localhost:8070", "https://spicybar.cn", "https://www.spicybar.cn"})
@RestController
@RequestMapping(value = "/user", produces = "text/html;charset=UTF-8")
public class UserController {

    private HttpServletRequest request;
    private IUserService userService;
    private ObjectMapper mapper;

    @Autowired
    public UserController(HttpServletRequest request, IUserService userService) {
        this.request = request;
        this.userService = userService;
        this.mapper = new ObjectMapper();
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    @PostMapping(value = "/login", params = {"username", "password"})
    public String login(String username, String password) throws JsonProcessingException {
        return mapper.writeValueAsString(userService.login(username, password, GetIp.getUserIp(request)));
    }

    /**
     * 用户注册
     *
     * @param user          用户信息
     * @param bindingResult 验证用户信息
     * @return 操作结果
     */
    @PostMapping(value = "/register", params = {"username", "password"})
    public String register(@Validated User user, BindingResult bindingResult) throws JsonProcessingException {
        String returnValue;
        if (bindingResult.hasErrors()) {
            throw new ParameterException(bindingResult);
        } else {
            user.setLastLoginIp(GetIp.getUserIp(request));
            returnValue = userService.register(user);
        }
        return mapper.writeValueAsString(returnValue);
    }

    /**
     * 用户名是否被注册
     *
     * @param username 用户名
     * @return 是否被注册
     */
    @GetMapping(value = "/usernameExists", params = {"username"})
    public String usernameExists(String username) throws JsonProcessingException {
        return mapper.writeValueAsString(userService.usernameExists(username));
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/getInfo")
    public String getInfo() throws JsonProcessingException {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return mapper.writeValueAsString(user);
    }

    /**
     * 刷新密钥
     *
     * @param authorization 原密钥
     * @return 新密钥
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/refreshToken")
    public String refreshToken(@RequestHeader("Authorization") String authorization) throws JsonProcessingException {
        return mapper.writeValueAsString(userService.refreshToken(authorization, GetIp.getUserIp(request)));
    }

}
