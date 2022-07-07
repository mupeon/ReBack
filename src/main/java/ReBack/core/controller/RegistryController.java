package ReBack.core.controller;



import ReBack.core.controller.request.RegistryRequest;
import ReBack.core.data.WriterInformation;
import ReBack.core.repository.MemberRepository;
import ReBack.core.repository.WriterInformationRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Getter
@Setter
@Slf4j
@Controller
@RequiredArgsConstructor
public class RegistryController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final WriterInformationRepository writerInformationRepository;
    @GetMapping("/registry")
    public String registryForm(Model model) {
        model.addAttribute("member", new RegistryRequest());
        return "member/registration";
    }

    @GetMapping("/companyregistry")
    public String companyregistryForm(Model model) {
        model.addAttribute("member", new RegistryRequest());
        return "member/cpregistration";
    }

    @GetMapping("/authorregistry")
    public String authorregistryForm(Model model) {
        model.addAttribute("member", new RegistryRequest());

        return "member/authorregistration";
    }

//    @GetMapping("member/authorTime")
//    public String authorTim(Model model) {
//        model.addAttribute("member", new RegistryRequest());
//
//        return "member/authorTime";
//    }

}
