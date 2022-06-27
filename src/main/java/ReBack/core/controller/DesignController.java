package ReBack.core.controller;

import ReBack.core.dto.DesignDTO;
import ReBack.core.dto.PageRequestDTO;
import ReBack.core.repository.CategoryRepository;
import ReBack.core.repository.DesignRepository;
import ReBack.core.repository.MaterialRepository;
import ReBack.core.security.SecurityUser;
import ReBack.core.service.ConsultingService;
import ReBack.core.service.DesignService;
import ReBack.core.service.DesignServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
public class DesignController {
    @Autowired
    DesignRepository designRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MaterialRepository materialRepository;

    private final DesignService designService;

//    private final ConsultingService consultingService;

    @GetMapping("/design/addpost")
    public String designAddPost(Model model, @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("materials", materialRepository.findAll());

        return "design/addpost";
    }



    @GetMapping("/design/list")
    public String designList(PageRequestDTO pageRequestDTO, Model model,  @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        model.addAttribute("result", designService.getList(pageRequestDTO));
        return "design/list";
    }

//    @GetMapping("/design/consultinglist")
//    public String consultingList(PageRequestDTO pageRequestDTO, Model model) {
//        model.addAttribute("result", consultingService.getList(pageRequestDTO));
//        return "design/consultingList";
//    }

//    @GetMapping("/design/list/{designid}")
//    public String designListDetails(@PathVariable("designid") Long designid, Model model) {
//        model.addAttribute("design", designRepository.findById(designid).get());
//        return "design/listDetails";
//    }



    @GetMapping({"/design/read", "/design/modify"})
    public void read(long designCode, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model, @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        System.out.println("requestDTO = " + requestDTO);
        DesignDTO dto = designService.read(designCode);
        System.out.println("dto = " + dto);
        model.addAttribute("dto", dto);

    }

    @PostMapping("/design/remove")
    public String remove(long designCode, RedirectAttributes redirectAttributes, Model model, @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        designService.remove(designCode);
        redirectAttributes.addFlashAttribute("msg", designCode);
        return "redirect:/design/list";
    }

    @PostMapping("/design/modify")
    public String modify(DesignDTO dto, @ModelAttribute("requestDTO")
            PageRequestDTO requestDTO, RedirectAttributes redirectAttributes, Model model, @AuthenticationPrincipal SecurityUser principal, @RequestParam(required = false) Long id) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
        log.info("post modify....................");
        log.info("dto:" + dto);
        designService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        redirectAttributes.addAttribute("designCode", dto.getDesignCode());
        return "redirect:/design/read";
    }
}