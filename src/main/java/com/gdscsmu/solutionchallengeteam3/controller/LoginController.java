package com.gdscsmu.solutionchallengeteam3.controller;

import com.gdscsmu.solutionchallengeteam3.domain.auth.SessionUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request) {
        session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/private")
    public String privatePage() {
        return "privatePage";
    }
    @GetMapping("/admin")
    public String adminPage() {
        return "adminPage";
    }

    @GetMapping("/session/user")
    public ResponseEntity<?> getSessionUser(HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        if (sessionUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sessionUser);
    }

}
