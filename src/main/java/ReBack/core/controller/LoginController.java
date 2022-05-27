package ReBack.core.controller;

import ReBack.core.controller.request.LoginRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model) {

        model.addAttribute("member", new LoginRequest());
        return "login";
    }

}
