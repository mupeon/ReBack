package ReBack.core.controller;

import ReBack.core.data.Member;
import ReBack.core.data.WriterInformation;
import ReBack.core.dto.ConsultingDTO;
import ReBack.core.dto.PageRequestDTO;
import ReBack.core.dto.WriterInformationDTO;
import ReBack.core.repository.CategoryRepository;
import ReBack.core.repository.MaterialRepository;
import ReBack.core.repository.OrdersRepository;
import ReBack.core.security.SecurityUser;
import ReBack.core.service.WriterInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Cache;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
@RequiredArgsConstructor
public class WriterInformationController {
    private final WriterInformationService writerInformationService;

    private final CategoryRepository categoryRepository;
    private final MaterialRepository materialRepository;
    private final OrdersRepository ordersRepository;

    @GetMapping("/design/writerInformation") // list 조회 역할
    public String writerInformation(Model model, PageRequestDTO pageRequestDTO) {
        System.out.println("writerInformation Controller 동작");
        model.addAttribute("result", writerInformationService.getList(pageRequestDTO));
        return "design/writerInformation";
    }

    @GetMapping("/design/writerInformationRead")
    public void read(long consultingCode, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        WriterInformationDTO dto = writerInformationService.read(consultingCode);
        model.addAttribute("dto", dto);
    }

    @GetMapping("/design/order")
    public String productOrderList(@AuthenticationPrincipal SecurityUser principal, @RequestParam("writerInformationCode") Long writerInformationCode, Model model) {
        if (principal != null) {
            model.addAttribute("principal", principal.getMember());
            model.addAttribute("role", principal.getMember().getRole().getDescription());
        }
//        Orders orders = ordersRepository.findById(id).orElse(null);

//        System.out.println(orders);


//        System.out.println(orders);


        return "product/orderList";
    }

}
