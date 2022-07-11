package ReBack.core.controller;

import ReBack.core.controller.request.RegistryRequest;
import ReBack.core.data.Member;
import ReBack.core.data.WriterInformation;
import ReBack.core.repository.MemberRepository;
import ReBack.core.repository.WriterInformationRepository;
import ReBack.core.security.SecurityUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Getter
@Setter
@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private WriterInformationRepository writerInformationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/idCheck")
    public String insertUser(@RequestBody Member member) {
        System.out.println(member.getMemberId());
        String inputId = member.getMemberId();
        if (inputId == "" || inputId == null) {
            return "ng";
        }

        Optional<Member> idCheck = memberRepository.findByMemberId(inputId);
        if (idCheck.isPresent() == true) {
            return "no";
        } else {
            return "ok";
        }
    }


    @PostMapping("/singUp")
    public String singUp(@RequestBody Member member) {
        String idCk = member.getMemberId();
        String inputemail = member.getMemberEmail();
        String pnN = member.getMemberPhoneNumber();
        Optional<Member> pnnCheck = memberRepository.findByMemberPhoneNumber(pnN);
        Optional<Member> idCheck = memberRepository.findByMemberId(idCk);
        Optional<Member> emailCheck = memberRepository.findByMemberEmail(inputemail);
        if (idCheck.isPresent() == true) {
            System.out.println("idCheck");
            return "no";
        } else if (emailCheck.isPresent() == true) {
            System.out.println("emailCheck");
            return "eino";
        } else if (pnnCheck.isPresent() == true) {
            System.out.println("pnnCheck");
            return "pnno";
        } else if (member.getMemberId() == "" || member.getPassword() == "" ||
                member.getMemberEmail() == "" || member.getMemberName() == "" ||
                member.getMemberPhoneNumber() == "" || member.getMemberAddress() == "" || member.getMemberPostalCode() == "") {
            return "kkkk";
        } else {
            String encodedPassword = passwordEncoder.encode(member.getPassword());
            member.setPassword(encodedPassword);
            memberRepository.save(member);
            return "ok";
        }
    }


    @PostMapping("/saveG")
    public String saveG(@RequestBody WriterInformation writerInformation) {
        System.out.println(writerInformation.getWriter().getRole());
        writerInformationRepository.save(writerInformation);
        return "ok";
    }


    @DeleteMapping("/timeDelete")
    public void timeDelete(@RequestBody WriterInformation writerInf) {
        Optional<WriterInformation> deleteTime = writerInformationRepository.findByWriterInformationCode(writerInf.getWriterInformationCode());

        if (deleteTime.isPresent()) {
            System.out.println(deleteTime);
            writerInformationRepository.delete(writerInf);
        }

    }


    @DeleteMapping("/memberDelete")
    public void memberDelete(@RequestBody Member member) {
        Optional<Member> deleteMember = memberRepository.findByMemberCode(member.getMemberCode());

        if (deleteMember.isPresent()) {
            System.out.println(deleteMember);
            memberRepository.delete(member);
        }

    }

    @PutMapping("/memberCorrection")
    public String memberCorrection(@AuthenticationPrincipal SecurityUser principal,
                                   @RequestBody Member member) {
        System.out.println("  getMemberCode   " + member.getMemberCode());
        System.out.println("  getMemberName   " + member.getMemberName());

        Optional<Member> member1 = memberRepository.findById(member.getMemberCode());
        System.out.println("getMemberCode getMemberCode getMemberCode" + member1.get().getMemberCode());
        if (member1.get().getMemberCode() == member.getMemberCode()) {
            member1.get().setMemberName(member.getMemberName());
            memberRepository.save(member1.get());
            return "ok";
        } else {
            return "no";
        }

    }

    @PostMapping("/memberPwCorrection")
    public String memberPwCorrection(@AuthenticationPrincipal SecurityUser principal, @RequestBody Member member) {
        System.out.println(member.getMemberCode());

        System.out.println(member.getPassword());

        Optional<Member> member1 = memberRepository.findByMemberCode(member.getMemberCode());

        System.out.println("로그인한 사용자 DB 비밀번호 :: "+member1.get().getPassword());

        if (passwordEncoder.matches(member.getPassword(), member1.get().getPassword())) {
            return "ok";
        } else {
            return "no";
        }
    }

    @PutMapping("/memberPwssCorrection")
    public String memberPwssCorrection(@AuthenticationPrincipal SecurityUser principal,
                                       @RequestBody Member member) {
        System.out.println(member.getPassword());
        System.out.println(member.getMemberCode());
        String encodedPassword = passwordEncoder.encode(member.getPassword());

        Optional<Member> member1 = memberRepository.findByMemberCode(member.getMemberCode());
        System.out.println("변경전 :: " + member1.get().getPassword());

        member1.get().setPassword(encodedPassword);

        System.out.println("변경후 :: " + member1.get().getPassword());

        memberRepository.save(member1.get());

        return "ok";

    }

}
