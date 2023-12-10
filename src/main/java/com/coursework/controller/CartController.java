package com.coursework.controller;

import com.coursework.models.Cart;
import com.coursework.models.Item;
import com.coursework.models.User;
import com.coursework.services.CartService;
import jakarta.servlet.http.HttpSession;
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
public class CartController {
    private final CartService cartService;

    @GetMapping("/cart")
    public String items(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "authorization";
        }
        List<Cart> items = cartService.list();

        model.addAttribute("items", items);
        return "cart";
    }

    @PostMapping("/cart/restore/{id}")
    public String restoreItem(HttpSession session, @PathVariable int id){
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {

            return "authorization";
        }

        cartService.restoreItem(id);
        return "redirect:/";
    }

    @PostMapping("/cart/delete/{id}")
    public String deleteItem(HttpSession session, @PathVariable int id){
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null || !loggedInUser.isSuperAdmin()) {
            // Перенаправление на страницу с сообщением о запрете доступа
            return "access-denied";
        }

        cartService.deleteItem(id);
        return "redirect:/cart";
    }
}
