package com.coursework.controller;

import com.coursework.MD5Utils;
import com.coursework.models.User;
import com.coursework.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = new User(username, password, null);

        if (userService.checkUser(user)) {
            model.addAttribute("message", "Авторизация успешна");
            User loggedUser = UserService.getUserByUsername(user.getLogin());
            System.out.println(loggedUser.toString());
            // Добавление пользователя в сессию
            session.setAttribute("loggedInUser", loggedUser);

            return "redirect:/";
        } else {
            model.addAttribute("message", "Неверный логин или пароль");
            return "authorization";
        }
    }


    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        System.out.println(user.toString());
        if (user.getLogin() == null || user.getPassword() == null || user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            model.addAttribute("error", "Пожалуйста, заполните все поля");
            System.out.println("check");
            return "registration";
        }
        if (userService.hasSameUsername(user)){
            model.addAttribute("error", "Имя пользователя занято");
            System.out.println("username");
            return  "registration";

        }
        System.out.println("good");
        // Проведем регистрацию пользователя
        userService.saveUser(user);

        // Перенаправим пользователя на страницу входа
        return "/authorization";
    }
}
