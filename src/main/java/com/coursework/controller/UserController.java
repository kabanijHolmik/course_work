package com.coursework.controller;

import com.coursework.models.User;
import com.coursework.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.list();
        model.addAttribute("users", users);
        return "userList"; // Предполагается, что у вас есть шаблон с именем "userList"
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userDetails"; // Предполагается, что у вас есть шаблон с именем "userDetails"
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser"; // Предполагается, что у вас есть шаблон с именем "createUser" для создания нового пользователя
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser"; // Предполагается, что у вас есть шаблон с именем "editUser" для редактирования пользователя
    }

    @PostMapping("/{id}/edit")
    public String editUser(@PathVariable int id, @ModelAttribute User updatedUser) {
        updatedUser.setId(id);
        userService.updateUser(updatedUser);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
