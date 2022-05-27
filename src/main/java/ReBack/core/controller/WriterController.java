package ReBack.core.controller;

import ReBack.core.data.WriterInformation;
import ReBack.core.repository.LectureRepository;
import ReBack.core.repository.MemberRepository;
import ReBack.core.repository.WriterInformationRepository;
import ReBack.core.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@org.springframework.stereotype.Controller
public class WriterController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    WriterInformationRepository writerInformationRepository;

    @Autowired
    LectureRepository lectureRepository;

    @GetMapping("/writerpage/writerprofile")
    public String writerprofile(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            model.addAttribute("info", writerInformationRepository.findAll());
        }
        return "writerpage/writerProfile";
    }

    @GetMapping("/writerpage/lecturemanage")
    public String lecturemanage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            model.addAttribute("lecture", lectureRepository.findAll());
        }
        return "writerpage/lectureManage";
    }

    @GetMapping("/writerpage/writerprofilemanage")
    public String writerprofilemanage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required=false) Long id) {
        System.out.println("받은id = "+id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            WriterInformation writerInformation = writerInformationRepository.findById(id).orElse(null);
            model.addAttribute("writerInformationCode", writerInformation.getWriterInformationCode());
            model.addAttribute("writerLecturePlace", writerInformation.getWriterLecturePlace());
            model.addAttribute("availableStartTime", writerInformation.getAvailableStartTime());
            model.addAttribute("availableFinishTime", writerInformation.getAvailableFinishTime());
            model.addAttribute("availableDay", writerInformation.getAvailableDay());
            model.addAttribute("trainingTopic", writerInformation.getTrainingTopic());
            model.addAttribute("trainingStatus", writerInformation.getTrainingStatus());
            model.addAttribute("feedbackStatus", writerInformation.getFeedbackStatus());

        }
        return "writerpage/writerProfileManage";
    }
}
