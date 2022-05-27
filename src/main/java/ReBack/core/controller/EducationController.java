package ReBack.core.controller;

import ReBack.core.repository.LectureRepository;
import ReBack.core.repository.MemberRepository;
import ReBack.core.repository.WriterInformationRepository;
import ReBack.core.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class EducationController {

    @Autowired
    WriterInformationRepository writerInformationRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LectureRepository lectureRepository;

    @GetMapping("/education")
    public String education(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "/education/education";
    }

    @GetMapping("/education/fitedu")
    public String fitedu(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "/education/fitedu";
    }

    @GetMapping("/education/shareedu")
    public String shareedu(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
//        System.out.println(model.addAttribute("info", writerInformationRepository.findAll()) + "찍히나 ?");
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            model.addAttribute("info", writerInformationRepository.findAll());
        }
        return "/education/shareedu";}

    @GetMapping("/education/shareedu/writerResult/{memberCode}")
    public String writerResult(@AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id , @PathVariable Long memberCode, Model model){
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            model.addAttribute("member", memberRepository.findByMemberCode(memberCode));
            model.addAttribute("info2", writerInformationRepository.findAll());
            model.addAttribute("lecture", lectureRepository.findAll());
        }
        return "/education/writerResult";
    }
}
