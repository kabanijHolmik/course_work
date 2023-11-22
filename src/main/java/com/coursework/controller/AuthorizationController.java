package com.coursework.controller;

import com.coursework.MD5Utils;
import com.coursework.models.User;
import com.coursework.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthorizationController {
    private final UserService userService;
    @GetMapping("/authorization")
    public String showAuthorizationForm() {
        return "authorization";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = new User(username, password, null); // Создаем объект User с логином и паролем

        if (userService.checkUser(user)) {
            model.addAttribute("message", "Авторизация успешна");
            return "redirect:/";
        } else {
            model.addAttribute("message", "Неверный логин или пароль");
            return "authorization";
        }
    }
}
