package ReBack.core.controller;

import ReBack.core.data.ClothingSponsor;
import ReBack.core.data.FinancialSupport;
import ReBack.core.data.Member;
import ReBack.core.repository.ClothingSponsorRepository;
import ReBack.core.repository.FinancialSupportRepository;
import ReBack.core.repository.MemberRepository;
import ReBack.core.security.SecurityUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Getter
@Setter
@Controller
@RequiredArgsConstructor
public class DonationController {

    @Autowired
    private final ClothingSponsorRepository clothingSponsorRepository;
    @Autowired
    private final FinancialSupportRepository financialSupportRepository;
    @Autowired
    private final MemberRepository memberRepository;

    @GetMapping("/donation") // 후원 메인
    public String donation(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        System.out.println("id값:" + id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "donation/donationMain";
    }

    @GetMapping("/donation/selectForm") // 후원 종류 선택
    public String selectForm(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        System.out.println("id값:" + id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "donation/donationSelectForm";
    }

    @GetMapping("/donation/financialSupport") //금전 후원
    public String financialSupport(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        System.out.println("id값:" + id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        return "donation/financialSupport";
    }

    @GetMapping("/donation/clothingSponsor") //의류 후원
    public String clothingSponsor(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        System.out.println("id값:" + id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }

        return "donation/clothingSponsor";
    }


    @GetMapping("/donation/clothesManage") // 의류 후원 관리 페이지
    public String clothesManage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        System.out.println("id값:" + id);
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

            ClothingSponsor clothingSponsor = clothingSponsorRepository.findById(id).orElse(null);
            model.addAttribute("clothes", clothingSponsorRepository.findAll());
            model.addAttribute("clothingSponsorCode", clothingSponsor.getClothingSponsorCode());
            model.addAttribute("amount", clothingSponsor.getAmount());
            model.addAttribute("pickupDate", clothingSponsor.getPickupDate());
            model.addAttribute("pickupArea", clothingSponsor.getPickupArea());
            model.addAttribute("issueStatus", clothingSponsor.getIssueStatus());
            model.addAttribute("appStatus", clothingSponsor.getAppStatus());
        }
        return "donation/clothManage";
    }

    @GetMapping("/donation/financialManage") // 금전 후원 관리 페이지
    public String financialManage(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false)  Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());


            FinancialSupport financialSupport = financialSupportRepository.findById(id).orElse(null);
            model.addAttribute("financial", financialSupportRepository.findAll());
            model.addAttribute("financialSupportCode", financialSupport.getFinancialSupportCode());
            model.addAttribute("financialDate", financialSupport.getFinancialDate());
            model.addAttribute("financialAmount", financialSupport.getFinancialAmount());
            model.addAttribute("financialType", financialSupport.getFinancialType());
            model.addAttribute("statusIssue", financialSupport.getStatusIssue());
            model.addAttribute("statusApp", financialSupport.getStatusApp());
            model.addAttribute("birth", financialSupport.getBirth());
            model.addAttribute("birth1", financialSupport.getBirth1());
        }
        return "donation/financialManage";
    }

    @GetMapping("/donation/manager") // 후원 상세 보기(관리)
    public String applicationManager(@AuthenticationPrincipal SecurityUser principal, Model model,
                                     @RequestParam(required = false) Member memberCode) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        List<FinancialSupport> financialSupport = financialSupportRepository.findByMemberCode(memberCode);
        List<ClothingSponsor> clothingSponsor = clothingSponsorRepository.findByMemberCode(memberCode);

        System.out.println("financialSupport   "+financialSupport.size());
        System.out.println("clothingSponsor   "+clothingSponsor.size());

        model.addAttribute("fslist",financialSupport);
        model.addAttribute("cslist",clothingSponsor);


        return "donation/applicationManager";
    }

    @GetMapping("/donation/chart")
    public String donationChart(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Member memberCode) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }

        model.addAttribute("financial", financialSupportRepository.findAll());
        model.addAttribute("findex", financialSupportRepository.findAll().size());
        model.addAttribute("findByType1", financialSupportRepository.findByType1().size());
        model.addAttribute("findByType2", financialSupportRepository.findByType2().size());

        model.addAttribute("clothes", clothingSponsorRepository.findAll());
        model.addAttribute("cindex", clothingSponsorRepository.findAll().size());
        model.addAttribute("findByArea1", clothingSponsorRepository.findByArea1().size());
        model.addAttribute("findByArea2", clothingSponsorRepository.findByArea2().size());
        model.addAttribute("findByArea3", clothingSponsorRepository.findByArea3().size());
        model.addAttribute("findByArea4", clothingSponsorRepository.findByArea4().size());

        model.addAttribute("findByAge1", financialSupportRepository.findByAge1().size()); //10대
        model.addAttribute("findByAge2", financialSupportRepository.findByAge2().size()); //10대
        model.addAttribute("findByAge3", financialSupportRepository.findByAge3().size()); //10대
        model.addAttribute("findByAge4", financialSupportRepository.findByAge4().size()); //10대
        model.addAttribute("findByAge5", financialSupportRepository.findByAge5().size()); //10대
        model.addAttribute("findByAge6", financialSupportRepository.findByAge6().size()); //10대
        model.addAttribute("findByAge7", financialSupportRepository.findByAge7().size()); //10대
        model.addAttribute("findByAge8", financialSupportRepository.findByAge8().size()); //10대
//        model.addAttribute("findByAge80", financialSupportRepository.findByAge80().size()); //10대

        model.addAttribute("findByAgeAll", financialSupportRepository.findByAgeAll().size()); //10대
//        model.addAttribute("findByBirth2",financialSupportRepository.findByBirth2().size());
//        model.addAttribute("findByBirth3",financialSupportRepository.findByBirth3().size());
//        model.addAttribute("findByBirth4",financialSupportRepository.findByBirth4().size());
//        model.addAttribute("findByBirth5",financialSupportRepository.findByBirth5().size());
//        model.addAttribute("findByBirth6",financialSupportRepository.findByBirth6().size());
//        model.addAttribute("findByBirth7",financialSupportRepository.findByBirth7().size());
//        model.addAttribute("findByBirth8",financialSupportRepository.findByBirth8().size());


        return "donation/donationChart";
    }

}