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

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/donation/applicationSituation") // 후원 현황
    public String applicationSituation(@AuthenticationPrincipal SecurityUser principal, Model model, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());

//            model.addAttribute("member", memberRepository.findAll());


        }

        return "donation/applicationSituation";
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
    public String donationChart(@AuthenticationPrincipal SecurityUser principal, Model model,
                                @RequestParam(required = false) Long id) {

        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }

        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("cloths", clothingSponsorRepository.findAll());
        model.addAttribute("financials", financialSupportRepository.findAll());

        return "donation/donationChart";
    }

    @GetMapping("/donation/map")
    public String map() {
        System.out.println("지도 api 실행");

        return "donation/donationMap";
    }


}