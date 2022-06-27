package ReBack.core.controller;

import ReBack.core.repository.*;
import ReBack.core.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@org.springframework.stereotype.Controller
    public class LectureController {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    WriterInformationRepository writerInformationRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MaterialRepository materialRepository;


    @GetMapping("/writerpage/lecturelist")
    public String lecturelist(@AuthenticationPrincipal SecurityUser principal, String memberCode, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }

        model.addAttribute("lecture", lectureRepository.findAll());
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("material", materialRepository.findAll());
        return "writerpage/lectureList";
    }

    @GetMapping("/writerpage/lecturemanage")
    public String lecturemanage(@AuthenticationPrincipal SecurityUser principal, String memberCode, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("memberCode", memberCode);
        model.addAttribute("lecture", lectureRepository.findAll());
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("material", materialRepository.findAll());
        return "writerpage/lectureManage";
    }

}
