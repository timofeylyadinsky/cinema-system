package lt.timofey.cinemaSystem.controller;

import lt.timofey.cinemaSystem.entity.User;
import lt.timofey.cinemaSystem.service.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/profile")
    public String getUserProfile(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        return "profile";
    }

}
