package ReBack.core.controller;

import ReBack.core.data.Member;
import ReBack.core.repository.MemberRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @DeleteMapping("/memberDelete")
    public void memberDelete(@RequestBody Member member) {
        Optional<Member> deleteMember = memberRepository.findByMemberCode(member.getMemberCode());

        if (deleteMember.isPresent()) {
            System.out.println(deleteMember);
            memberRepository.delete(member);
        }

    }

    @PutMapping("/memberCorrection") //상품 수정
    public void memberCorrection(@RequestBody Member member) {
        System.out.println("수정api");
        Optional<Member> memberCorrection = memberRepository.findById(member.getMemberCode());

        if (memberCorrection.isPresent()) {
            memberRepository.save(member);
        }


    }



}
