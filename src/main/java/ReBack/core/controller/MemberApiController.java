package ReBack.core.controller;


import ReBack.core.controller.request.RegistryRequest;
import ReBack.core.data.Member;
import ReBack.core.data.Product;
import ReBack.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.maven.artifact.repository.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RequiredArgsConstructor
@Controller
public class MemberApiController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;


//    //고객 정보 삭제 처리 요청
//    @RequestMapping("/delete.cu")
//    public String delete(int id) {
//        //선택한 고객 정보를 DB에서 삭제한 후
//        service.customer_delete(id);
//        //목록 화면으로 연결
//        return "redirect:list.cu";
//    }





}
