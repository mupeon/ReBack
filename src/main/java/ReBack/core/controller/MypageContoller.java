package ReBack.core.controller;

import ReBack.core.data.Member;

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
public class MypageContoller {
    @Autowired
    private final MemberRepository memberRepository;

    @GetMapping("/me/authorMypage")
    public String authorMypage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
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
        return "mypage/authorMypage";
    }


    @GetMapping("/me/comranyMyPage")
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
        return "mypage/comranyMyPage";
    }


    @GetMapping("/me/memberMypage")
    public String memberMypage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
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
        return "mypage/memberMypage";
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
