package ReBack.core.controller;


import ReBack.core.controller.request.RegistryRequest;
import ReBack.core.data.Member;
import ReBack.core.data.Role;
import ReBack.core.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
@Controller
public class RegistryController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/registry")
    public String registryForm(Model model) {
        model.addAttribute("member", new RegistryRequest());
        return "registration";
    }

        @GetMapping("/companyregistry")
    public String companyregistryForm(Model model) {
        model.addAttribute("member", new RegistryRequest());
        return "cpregistration";
    }

    @GetMapping("/authorregistry")
    public String authorregistryForm(Model model) {
        model.addAttribute("member", new RegistryRequest());
        return "authorregistration";
    }




    @PostMapping("/registry")
    public String registry(@ModelAttribute RegistryRequest registryRequest) {
        Member member = Member.builder()
                .memberId(registryRequest.getMemberId())
                .memberEmail(registryRequest.getMemberEmail())
                .memberName(registryRequest.getMemberName())
                .memberPostalCode(registryRequest.getMemberPostalCode())
                .memberPhoneNumber(registryRequest.getMemberPhoneNumber())
                .memberAddress(registryRequest.getMemberAddress())
                .memberJoinDate(registryRequest.getMemberJoinDate())
                .password(passwordEncoder.encode(registryRequest.getPassword()))
                .role(registryRequest.getRole())
                .build();
        memberRepository.save(member);

        return "redirect:/login";
    }

    @PostMapping("/companyregistry")
    public String registry1(@ModelAttribute RegistryRequest companyRequest) {
        Member member = Member.builder()
                .memberId(companyRequest.getMemberId())
                .memberEmail(companyRequest.getMemberEmail())
                .memberName(companyRequest.getMemberName())
                .memberPostalCode(companyRequest.getMemberPostalCode())
                .memberPhoneNumber(companyRequest.getMemberPhoneNumber())
                .memberAddress(companyRequest.getMemberAddress())
                .memberJoinDate(companyRequest.getMemberJoinDate())
                .memberBusinessNumber(companyRequest.getMemberBusinessNumber())
                .password(passwordEncoder.encode(companyRequest.getPassword()))
                .role(companyRequest.getRole())
                .build();
        memberRepository.save(member);

        return "redirect:/login";
    }



    @PostMapping("/authorregistry")
    public String authorregistry(@ModelAttribute RegistryRequest authorRequest) {
        Member member = Member.builder()
                .memberId(authorRequest.getMemberId())
                .memberEmail(authorRequest.getMemberEmail())
                .memberName(authorRequest.getMemberName())
                .password(passwordEncoder.encode(authorRequest.getPassword()))
                .role(authorRequest.getRole())
                .build();
        memberRepository.save(member);

        return "redirect:/login";
    }



//    @PostMapping("/mypage")
//    public String mypage(@ModelAttribute RegistryRequest authorRequest) {
//        Member member = Member.builder()
//                .memberId(authorRequest.getMemberId())
//                .memberEmail(authorRequest.getMemberEmail())
//                .memberName(authorRequest.getMemberName())
//                .password(passwordEncoder.encode(authorRequest.getPassword()))
//                .role(authorRequest.getRole())
//                .build();
//        memberRepository.save(member);
//
//        return "mypage";
//    }





    @ModelAttribute("roles")
    public Map<String, Role> roles() {
        Map<String, Role> map = new LinkedHashMap<>();
       map.put("동의", Role.ROLE_MEMBER);
        return map;
    }

    @ModelAttribute("roles1")
    public Map<String, Role> roles1() {
        Map<String, Role> map = new LinkedHashMap<>();
        map.put("동의", Role.ROLE_AUTHOR);
        return map;
    }

    @ModelAttribute("roles2")
    public Map<String, Role> roles2() {
        Map<String, Role> map = new LinkedHashMap<>();
        map.put("동의", Role.ROLE_COMPANY);
        return map;
    }

}
