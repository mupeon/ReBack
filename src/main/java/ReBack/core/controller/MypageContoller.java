package ReBack.core.controller;

import ReBack.core.controller.request.AuthorRequest;
import ReBack.core.data.Member;

import ReBack.core.data.Role;
import ReBack.core.repository.MemberRepository;
import ReBack.core.repository.WriterInformationRepository;
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
public class MypageContoller {
    @Autowired
    private final MemberRepository memberRepository;

    private final WriterInformationRepository writerInformationRepository;

    @GetMapping("/me/authorMyPage")
    public String authorMyPage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id, Role role) {
        model.addAttribute("writerInformation", new AuthorRequest());
        System.out.println(role);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            Member member = memberRepository.findById(id).orElse(null);
//            model.addAttribute("members", memberRepository.findAll());
            model.addAttribute("memberId", member.getMemberId());
            model.addAttribute("memberName", member.getMemberName());
//            model.addAttribute("password", member.getPassword());
            model.addAttribute("memberEmail", member.getMemberEmail());
            model.addAttribute("memberPhoneNumber", member.getMemberPhoneNumber());
//            model.addAttribute("memberPostalCode", member.getMemberPostalCode());
            model.addAttribute("memberAddress", member.getMemberAddress());
//            model.addAttribute("memberPoint", member.getMemberPoint());
//            model.addAttribute("memberBusinessNumber", member.getMemberBusinessNumber());
            model.addAttribute("memberJoinDate", member.getMemberJoinDate());
            model.addAttribute("role", member.getRole());
        }
        return "myPage/authorMyPage";
    }
    @GetMapping("/me/adminMyPage")
    public String adminMyPage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id, Role role) {
        model.addAttribute("writerInformation", new AuthorRequest());
        System.out.println(role);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            Member member = memberRepository.findById(id).orElse(null);
//            model.addAttribute("members", memberRepository.findAll());
            model.addAttribute("memberId", member.getMemberId());
            model.addAttribute("memberName", member.getMemberName());
//            model.addAttribute("password", member.getPassword());
            model.addAttribute("memberEmail", member.getMemberEmail());
            model.addAttribute("memberPhoneNumber", member.getMemberPhoneNumber());
//            model.addAttribute("memberPostalCode", member.getMemberPostalCode());
            model.addAttribute("memberAddress", member.getMemberAddress());
//            model.addAttribute("memberPoint", member.getMemberPoint());
//            model.addAttribute("memberBusinessNumber", member.getMemberBusinessNumber());
            model.addAttribute("memberJoinDate", member.getMemberJoinDate());
            model.addAttribute("role", member.getRole());
        }
        return "myPage/adminMyPage";
    }



    @GetMapping("/me/companyMyPage")
    public String comranyMyPage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        System.out.println("id값:" + id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            Member member = memberRepository.findById(id).orElse(null);
            // ->  null값도 가져올 수 있으니 주의
//            model.addAttribute("members", memberRepository.findAll());
            model.addAttribute("memberId", member.getMemberId());
            model.addAttribute("memberName", member.getMemberName());
//            model.addAttribute("password", member.getPassword());
            model.addAttribute("memberEmail", member.getMemberEmail());
            model.addAttribute("memberPhoneNumber", member.getMemberPhoneNumber());
//            model.addAttribute("memberPostalCode", member.getMemberPostalCode());
            model.addAttribute("memberAddress", member.getMemberAddress());
//            model.addAttribute("memberPoint", member.getMemberPoint());
//            model.addAttribute("memberBusinessNumber", member.getMemberBusinessNumber());
            model.addAttribute("memberJoinDate", member.getMemberJoinDate());
            model.addAttribute("role", member.getRole());
        }
        return "myPage/companyMyPage";
    }


    @GetMapping("/me/memberMyPage")
    public String memberMypage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        System.out.println("id값:" + id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            Member member = memberRepository.findById(id).orElse(null);
            // ->  null값도 가져올 수 있으니 주의
//            model.addAttribute("members", memberRepository.findAll());
            model.addAttribute("memberCode", member.getMemberCode());
            model.addAttribute("memberId", member.getMemberId());
            model.addAttribute("memberName", member.getMemberName());
//            model.addAttribute("password", member.getPassword());
            model.addAttribute("memberEmail", member.getMemberEmail());
            model.addAttribute("memberPhoneNumber", member.getMemberPhoneNumber());
//            model.addAttribute("memberPostalCode", member.getMemberPostalCode());
            model.addAttribute("memberAddress", member.getMemberAddress());
//            model.addAttribute("memberPoint", member.getMemberPoint());
//            model.addAttribute("memberBusinessNumber", member.getMemberBusinessNumber());
            model.addAttribute("memberJoinDate", member.getMemberJoinDate());
            model.addAttribute("role", member.getRole());
        }
        return "myPage/memberMyPage";
    }



//    @GetMapping("/me/memberMypage")
//    public String memberMypage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
//        System.out.println("id값:" + id);
//        if (principal != null) {
//            model.addAttribute("principal", principal.getMember());
//            model.addAttribute("role", principal.getMember().getRole().getDescription());
//
//            Member member = memberRepository.findById(id).orElse(null);
//            // ->  null값도 가져올 수 있으니 주의
////            model.addAttribute("members", memberRepository.findAll());
//            model.addAttribute("memberId", member.getMemberId());
//            model.addAttribute("memberName", member.getMemberName());
////            model.addAttribute("password", member.getPassword());
//            model.addAttribute("memberEmail", member.getMemberEmail());
//            model.addAttribute("memberPhoneNumber", member.getMemberPhoneNumber());
////            model.addAttribute("memberPostalCode", member.getMemberPostalCode());
//            model.addAttribute("memberAddress", member.getMemberAddress());
////            model.addAttribute("memberPoint", member.getMemberPoint());
////            model.addAttribute("memberBusinessNumber", member.getMemberBusinessNumber());
//            model.addAttribute("memberJoinDate", member.getMemberJoinDate());
//            model.addAttribute("role", member.getRole());
//        }
//        return "mypage/memberMypage";
//    }
}
