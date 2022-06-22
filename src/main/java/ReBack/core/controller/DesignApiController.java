package ReBack.core.controller;

import ReBack.core.dto.ConsultingDTO;
import ReBack.core.dto.DesignDTO;
import ReBack.core.repository.DesignRepository;
import ReBack.core.security.SecurityUser;
import ReBack.core.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Log4j2
public class DesignApiController {

    @Autowired
    private DesignServiceImpl designServiceImpl;

    @Autowired
    private DesignService designService;

    @Autowired
    private ConsultingServiceImpl consultingServiceImpl;

    @PostMapping("/design/addpost")
    public String designAdd(@Validated @RequestPart(value = "key") DesignDTO designDTO,
<<<<<<< Updated upstream
                             @RequestPart(value = "file") MultipartFile file,
                             HttpServletRequest request) {
=======
                            @RequestPart(value = "file") MultipartFile file,
                            @AuthenticationPrincipal SecurityUser principal,
                            HttpServletRequest request) {
>>>>>>> Stashed changes


        String fileName;
        if (file == null) {
            fileName = "";
            System.out.println("파일없음");
        } else {
            fileName = file.getOriginalFilename();
            String filepath = request.getSession().getServletContext().getRealPath("") + "file\\"; // webapps/file
            System.out.println("filepath  :    " + filepath);

            try {
                file.transferTo(new File(filepath + fileName));
                System.out.println("업로드 성공");
                designDTO.setDesignFileName(fileName);
                designDTO.setDesignFilePath("/file/" + fileName);
                System.out.println("designDTO = " + designDTO.getCategoryCode());
                System.out.println("designDTO = " + designDTO.getMaterialCode());
            } catch (IllegalStateException | IOException e) {
                System.out.println("실패");
                e.printStackTrace();
            }
        }

        designServiceImpl.save(designDTO, principal);
        return "redirect:/design/list";
    }


    @PostMapping("/design/consulting")
    public void consultingAdd(@Validated @RequestPart(value = "key") ConsultingDTO consultingDTO,
                           @RequestPart(value = "file") MultipartFile file,
                           HttpServletRequest request) throws Exception {

        String consultingDate = consultingDTO.getConsultingDate();
        String startingTime = consultingDTO.getStartingTimeString();
        String endTime = consultingDTO.getEndTimeString();

        System.out.println("consultingDate = " + consultingDate);
        System.out.println("startingTime = " + startingTime);
        System.out.println("endTime = " + endTime);

        String staringDateString = consultingDate+'T'+startingTime;
        String endDateString = consultingDate+'T'+endTime;
        LocalDateTime startingDate = LocalDateTime.parse(staringDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        LocalDateTime endDate = LocalDateTime.parse(endDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        System.out.println("staringDateString = " + staringDateString);
        System.out.println("endDateString = " + endDateString);
        System.out.println("startingDate = " + startingDate);
        System.out.println("endDate = " + endDate);

        consultingDTO.setEndTime(endDate);
        consultingDTO.setStartingTime(startingDate);

        String fileName;
        if (file == null) {
            fileName = "";
        } else {
            fileName = file.getOriginalFilename();
            String filepath = request.getSession().getServletContext().getRealPath("") + "file\\" ; // webapps/file
            System.out.println("filepath  :    " + filepath);

            try {
                file.transferTo(new File(filepath+fileName));
                System.out.println("업로드 성공");
                consultingDTO.setFileName(fileName);
                consultingDTO.setFilePath("/file/" + fileName);
            } catch (IllegalStateException | IOException e) {
                System.out.println("실패");
                e.printStackTrace();
            }
        }

        consultingServiceImpl.save(consultingDTO);
    }

    @DeleteMapping("/design/{designCode}")
    public String deleteDesign(@PathVariable("designCode") Long designCode, Model model) {
        if (designCode != null) {
            designService.deleteDesign(designCode);
        }
        return "redirect:/design/list";

    }

}