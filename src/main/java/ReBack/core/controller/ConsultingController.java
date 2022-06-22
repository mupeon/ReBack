package ReBack.core.controller;

import ReBack.core.dto.ConsultingDTO;
import ReBack.core.dto.PageRequestDTO;
import ReBack.core.dto.PayDTO;
import ReBack.core.dto.PayListDTO;
import ReBack.core.repository.CategoryRepository;
import ReBack.core.repository.MaterialRepository;
import ReBack.core.security.SecurityUser;
import ReBack.core.service.ConsultingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ConsultingController {
    private final ConsultingService consultingService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MaterialRepository materialRepository;

    @GetMapping("/design/consulting") // 등록
    public String designMatching(Model model, PageRequestDTO pageRequestDTO, @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("materials", materialRepository.findAll());
        model.addAttribute("result", consultingService.getConsultingList(pageRequestDTO));
        return "design/consulting";
    }

    @GetMapping("/design/consultingList") // 조회
    public String consultingList(PageRequestDTO pageRequestDTO, Model model,  @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("result", consultingService.getList(pageRequestDTO));
        return "design/consultingList";
    }

    @GetMapping({"/design/consultingRead", "/design/consultingModify"}) // 상세조회, 수정
    public void read(long consultingCode, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model,  @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        ConsultingDTO dto = consultingService.read(consultingCode);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/design/consultingRemove") // 삭제
    public String remove(long consultingCode, @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id, RedirectAttributes redirectAttributes, Model model)  {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        consultingService.remove(consultingCode);
        redirectAttributes.addFlashAttribute("msg", consultingService);
        return "redirect:/design/consultingList";
    }

    @PostMapping("/design/consultingModify")
    public String modify(ConsultingDTO dto, @ModelAttribute("requestDTO")
            PageRequestDTO requestDTO, RedirectAttributes redirectAttributes,  @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        log.info("post modify....................");
        log.info("dto:" + dto);
        consultingService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("consultingCode", dto.getConsultingCode());
        return "redirect:/design/consultingRead";
    }

    @GetMapping("/design/consultingWriterList")
    public String consultingWriterList(PageRequestDTO pageRequestDTO, Model model,  @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("result", consultingService.getConsultingList(pageRequestDTO));
        return "design/consultingWriterList";
    }

    // 주문 페이지
    @GetMapping("/design/consulting/payList")
    public String consultingPay(@RequestParam("start") String start,
                                @RequestParam("end") String end,
                                @RequestParam("place") String place,
                                @RequestParam("name") String name,
                                Model model,
                                @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id
    ) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        PayDTO payList = consultingService.getPayList(start, end, place, name);
        model.addAttribute("result", payList);
        return "design/payList";
    }

}
