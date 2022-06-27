package ReBack.core.controller;

import ReBack.core.data.Consulting;
import ReBack.core.dto.ConsultingMatchingDTO;
import ReBack.core.dto.PageRequestDTO;
import ReBack.core.dto.WriterProfileDTO;
import ReBack.core.service.ConsultingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ConsultingMatchingController {
    private final ConsultingServiceImpl consultingService;

    @PostMapping("/design/matching")
    public List<WriterProfileDTO> consultingWriterList(@RequestBody ConsultingMatchingDTO consultingMatchingDTO) {
        System.out.println("consultingMatchingDTO = " + consultingMatchingDTO);
        List<WriterProfileDTO> writerProfile = consultingService.getWriterProfile(consultingMatchingDTO);
        return writerProfile;
    }
}
