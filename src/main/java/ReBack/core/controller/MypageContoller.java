package ReBack.core.controller;

import ReBack.core.controller.request.AuthorRequest;
import ReBack.core.data.Lecture;
import ReBack.core.data.Member;

import ReBack.core.data.Role;
import ReBack.core.repository.LectureRepository;
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

import java.util.List;


@Getter
@Setter
@Slf4j
@Controller
@RequiredArgsConstructor
public class MypageContoller {
    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private LectureRepository lectureRepository;

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
        model.addAttribute("Add1",memberRepository.findByAdd1().size());
        model.addAttribute("Add2",memberRepository.findByAdd2().size());
        model.addAttribute("Add3",memberRepository.findByAdd3().size());
        model.addAttribute("Add4",memberRepository.findByAdd4().size());
        model.addAttribute("Add5",memberRepository.findByAdd5().size());
        model.addAttribute("Add6",memberRepository.findByAdd6().size());
        model.addAttribute("Add7",memberRepository.findByAdd7().size());
        model.addAttribute("Add8",memberRepository.findByAdd8().size());
        model.addAttribute("Add9",memberRepository.findByAdd9().size());
        model.addAttribute("Add10",memberRepository.findByAdd10().size());
        model.addAttribute("Add11",memberRepository.findByAdd11().size());
        model.addAttribute("Add12",memberRepository.findByAdd12().size());
        model.addAttribute("Add13",memberRepository.findByAdd13().size());
        model.addAttribute("Add14",memberRepository.findByAdd14().size());
        model.addAttribute("Add15",memberRepository.findByAdd15().size());
        model.addAttribute("Add16",memberRepository.findByAdd16().size());


        model.addAttribute("Role1",memberRepository.findByRole1().size());
        model.addAttribute("Role2",memberRepository.findByRole2().size());
        model.addAttribute("Role3",memberRepository.findByRole3().size());



        model.addAttribute("Summers1",memberRepository.findBySummers1().size());
        model.addAttribute("Summers2",memberRepository.findBySummers2().size());
        model.addAttribute("Summers3",memberRepository.findBySummers3().size());
        model.addAttribute("Summers4",memberRepository.findBySummers4().size());
        model.addAttribute("Summers5",memberRepository.findBySummers5().size());
        model.addAttribute("Summers6",memberRepository.findBySummers6().size());
        model.addAttribute("Summers7",memberRepository.findBySummers7().size());
        model.addAttribute("Summers8",memberRepository.findBySummers8().size());
        model.addAttribute("SummersAll",memberRepository.findBySummersAll().size());


//        model.addAttribute("MemberJoinDate1",memberRepository.findByMemberJoinDate1().size());
//        model.addAttribute("MemberJoinDate2",memberRepository.findByMemberJoinDate2().size());
//        model.addAttribute("MemberJoinDate3",memberRepository.findByMemberJoinDate3().size());
//        model.addAttribute("MemberJoinDate4",memberRepository.findByMemberJoinDate4().size());
//        model.addAttribute("MemberJoinDate5",memberRepository.findByMemberJoinDate5().size());
//        model.addAttribute("MemberJoinDate6",memberRepository.findByMemberJoinDate6().size());
//        model.addAttribute("MemberJoinDate7",memberRepository.findByMemberJoinDate7().size());
//        model.addAttribute("MemberJoinDate8",memberRepository.findByMemberJoinDate8().size());
//        model.addAttribute("MemberJoinDate9",memberRepository.findByMemberJoinDate9().size());
//        model.addAttribute("MemberJoinDate10",memberRepository.findByMemberJoinDate10().size());
//        model.addAttribute("MemberJoinDate11",memberRepository.findByMemberJoinDate11().size());
//        model.addAttribute("MemberJoinDate12",memberRepository.findByMemberJoinDate12().size());

        return "mypage/adminMyPage";
    }



    @GetMapping("/me/companyMyPage")
    public String comranyMyPage(@AuthenticationPrincipal SecurityUser principal, Model model, Lecture lecture, @RequestParam(required = false) Long id) {
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

//        List<Lecture> lectures = lectureRepository.findByStatus1(lecture.getMember());
//
//        model.addAttribute("lectureStatus1", lecture);
//
//        model.addAttribute("Status1",lectureRepository.findByStatus1().size());
//        model.addAttribute("Status2",lectureRepository.findByStatus2().size());
//        model.addAttribute("Status3",lectureRepository.findByStatus3().size());
//        model.addAttribute("ApplicationNumber",lectureRepository.findByApplicationNumber().size());

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
