package com.example.onlinequizsystemmanagment.controller;

import com.example.onlinequizsystemmanagment.model.Quiz;
import com.example.onlinequizsystemmanagment.model.User;
import com.example.onlinequizsystemmanagment.repository.QuizRepository;
import com.example.onlinequizsystemmanagment.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/index")
    public String index(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<Quiz> quizzes;

        if (filter != null && !filter.isEmpty()) {
            quizzes = quizRepository.findByTag(filter);
        } else {
            quizzes = quizRepository.findAll();
        }

        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("quizzes", quizzes);

        return "index";
    }

    @GetMapping("/home")
    public String home(
            @AuthenticationPrincipal CurrentUser currentUser,
            Model model
    ) {
      if(currentUser.getUser().isAdmin()){
          return "redirect:/admin/home";
      } else {
          return "redirect:/user/home";

      }
    }


    @PostMapping("/index/delete")
    public String delete(@RequestParam Long id, Model model) {
        quizRepository.deleteById(id);
        Iterable<Quiz> quizzes = quizRepository.findAll();

        model.addAttribute("quiz", new Quiz());
        model.addAttribute("quizzes", quizzes);

        return "redirect:/index";
    }
}