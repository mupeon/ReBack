package ReBack.core.controller;

import ReBack.core.repository.MemberRepository;
import ReBack.core.security.SecurityUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Setter
@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberRepository memberRepository;

    @GetMapping("/signupType")
    public String signupType(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "member/signupType";

    }

}
