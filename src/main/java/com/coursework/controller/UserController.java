package com.coursework.controller;

import com.coursework.models.User;
import com.coursework.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {
    private final UserService userService;

    @Controller
    public class AdminController {

        @GetMapping("/admin/manage-users")
        public String manageUsers(HttpSession session, Model model) {
            // Получение информации о пользователе из сессии
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            // Проверка, если пользователь не авторизован или не является superAdmin
            if (loggedInUser == null || !loggedInUser.isSuperAdmin()) {
                // Перенаправление на страницу с сообщением о запрете доступа
                return "access-denied";
            }

            // Получение списка пользователей и передача его в модель для отображения
            List<User> users = userService.list();
            model.addAttribute("users", users);

            return "manage-users";
        }
    }



    @GetMapping("/{id}")
    public String getUserById(HttpSession session,@PathVariable int id, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "authorization";
        }
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "userDetails"; // Предполагается, что у вас есть шаблон с именем "userDetails"
    }

    @GetMapping("/create")
    public String createUserForm(HttpSession session,Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "authorization";
        }
        model.addAttribute("user", new User());
        return "createUser"; // Предполагается, что у вас есть шаблон с именем "createUser" для создания нового пользователя
    }

    @PostMapping("/create")
    public String createUser(HttpSession session,@ModelAttribute User user) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "authorization";
        }
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(HttpSession session,@PathVariable int id, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "authorization";
        }
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser"; // Предполагается, что у вас есть шаблон с именем "editUser" для редактирования пользователя
    }

    @PostMapping("/{id}/edit")
    public String editUser(HttpSession session,@PathVariable int id, @ModelAttribute User updatedUser) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "authorization";
        }
        updatedUser.setId(id);
        userService.updateUser(updatedUser);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(HttpSession session,@PathVariable int id) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "authorization";
        }
        userService.deleteUser(id);
        return "redirect:/admin/manage-users";
    }
}
