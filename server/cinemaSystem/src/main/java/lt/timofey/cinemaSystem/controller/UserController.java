package lt.timofey.cinemaSystem.controller;

import jakarta.validation.Valid;
import lt.timofey.cinemaSystem.entity.User;
import lt.timofey.cinemaSystem.service.UserDetailsImpl;
import lt.timofey.cinemaSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String getUserProfile(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("/edit")
    public String edit(Model model,@AuthenticationPrincipal UserDetailsImpl personDetails ) {
        User person= personDetails.getUser();
        //System.out.println(person);
        model.addAttribute("person", person);
        return "user/edit";
    }
    @PatchMapping("/update")
    public String update(@ModelAttribute("person") @Valid User person, @AuthenticationPrincipal UserDetailsImpl userDetails, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "edit_or_addInfo";
        }

        userService.update(userDetails.getUser().getId(),person);
        userDetails.update(person);
        return "redirect:/";
    }

}
