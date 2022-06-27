package ReBack.core.controller;


import ReBack.core.controller.request.AuthorRequest;
import ReBack.core.controller.request.RegistryRequest;
import ReBack.core.data.Design;
import ReBack.core.data.Member;
import ReBack.core.data.Role;
import ReBack.core.data.WriterInformation;
import ReBack.core.repository.MemberRepository;
import ReBack.core.repository.WriterInformationRepository;
import ReBack.core.security.SecurityUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
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



    @PostMapping("/registry")
    public String registry(@ModelAttribute RegistryRequest registryRequest) {
        Member member = Member.builder()
                .memberId(registryRequest.getMemberId())
                .memberEmail(registryRequest.getMemberEmail())
                .memberName(registryRequest.getMemberName())
                .memberPostalCode(registryRequest.getMemberPostalCode())
                .memberPhoneNumber(registryRequest.getMemberPhoneNumber())
                .memberAddress(registryRequest.getMemberAddress())
//                .memberJoinDate(registryRequest.getMemberJoinDate())
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
//                .memberJoinDate(companyRequest.getMemberJoinDate())
                .memberBusinessNumber(companyRequest.getMemberBusinessNumber())
                .password(passwordEncoder.encode(companyRequest.getPassword()))
                .role(companyRequest.getRole())
                .build();
        memberRepository.save(member);

        return "redirect:/login";
    }




    @PostMapping("/authorregistry")
    public String authorCalendar(@ModelAttribute RegistryRequest registryRequest) {
        Member member = Member.builder()
                .memberId(registryRequest.getMemberId())
                .memberEmail(registryRequest.getMemberEmail())
                .memberName(registryRequest.getMemberName())
                .password(passwordEncoder.encode(registryRequest.getPassword()))
                .role(registryRequest.getRole())
                .build();
        memberRepository.save(member);


//        WriterInformation writerInformation = WriterInformation.builder()
//                .memberCode(member)
//                .writerLecturePlace(registryRequest.getWriterLecturePlace())
//                .availableStartTime(registryRequest.getAvailableStartTime())
//                .availableFinishTime(registryRequest.getAvailableFinishTime())
//                .availableDay(registryRequest.getAvailableDay())
//                .build();
//        writerInformationRepository.save(writerInformation);
        return "redirect:/login";

    }




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
        map.put("동의", Role.ROLE_ADMIN);
        return map;
    }

}
