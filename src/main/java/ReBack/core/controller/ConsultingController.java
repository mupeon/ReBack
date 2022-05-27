package ReBack.core.controller;

import ReBack.core.dto.PageRequestDTO;
import ReBack.core.repository.CategoryRepository;
import ReBack.core.repository.MaterialRepository;
import ReBack.core.service.ConsultingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class ConsultingController {
    private final ConsultingService consultingService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MaterialRepository materialRepository;

    @GetMapping("/design/consulting")
    public String designMatching(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("materials", materialRepository.findAll());
        return "design/consulting";
    }

    @GetMapping("/design/consultingList")
    public String consultingList(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", consultingService.getList(pageRequestDTO));
        return "design/consultingList";
    }

}
