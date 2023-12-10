package com.coursework.controller;

import com.coursework.models.ChangeLog;
import com.coursework.models.User;
import com.coursework.services.ChangeLogService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class ChangeLogController {

    private final ChangeLogService changeLogService;


    @GetMapping("/changelog")
    public String showChangeLog(HttpSession session, Model model) {
        // Получение информации о пользователе из сессии
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        System.out.println(loggedInUser.toString());
        // Проверка, если пользователь не авторизован или не является superAdmin
        if (loggedInUser == null || !loggedInUser.isSuperAdmin()) {
            // Перенаправление на страницу с сообщением о запрете доступа
            return "access-denied";
        }

        List<ChangeLog> changeLogEntries = changeLogService.list();
        model.addAttribute("changeLogEntries", changeLogEntries);
        return "changelog";
    }

}
