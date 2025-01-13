package com.baovola.baovola;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoulangerieController {

    @GetMapping("/dashboard")
    public String accueil(Model model) {
        model.addAttribute("body", "dashboard");
        return "layout";  // Retourne le template layout.html
    }

    @GetMapping("/ingredient")
    public String ingredient(Model model) {
        model.addAttribute("body", "ingredient");
        return "layout";  // Retourne le template layout.html
    }
}