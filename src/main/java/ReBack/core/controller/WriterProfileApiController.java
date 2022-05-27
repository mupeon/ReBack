package ReBack.core.controller;

import ReBack.core.data.WriterInformation;
import ReBack.core.repository.LectureRepository;
import ReBack.core.repository.WriterInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class WriterProfileApiController {

    @Autowired
    WriterInformationRepository writerInformationRepository;

    @Autowired
    LectureRepository lectureRepository;

    @PutMapping("/writerprofile/update")
    public void writerProfileUpdate(@RequestBody WriterInformation writerInformation) {

        writerInformationRepository.save(writerInformation);
    }

    @DeleteMapping("/writerprofile/delete")
    public void productDelete(@RequestBody WriterInformation writerInformation) {
        Optional<WriterInformation> deleteWriterInformation = writerInformationRepository.findById(writerInformation.getWriterInformationCode());

        if (deleteWriterInformation.isPresent()) {
            writerInformationRepository.delete(writerInformation);
        }

    }

//    @PostMapping("/add")
//    public void writerInformationAdd(@Validated @RequestPart(value = "key") WriterInformation writerInformation,
//                                     @RequestPart(value = "file") MultipartFile file,
//                                     HttpServletRequest request) throws Exception {
//        String fileName;
////        if (file == null) {
////            fileName = "";
////        } else {
////            fileName = file.getOriginalFilename(); //
////            String filepath = request.getSession().getServletContext().getRealPath("") + "file\\" ; // webapps/file
////            System.out.println("filepath  :    " + filepath);
////            try {
////
////                file.transferTo(new File(filepath+fileName));
////                System.out.println("업로드 성공");
////                writerInformation.setWriterInformationFileName(fileName);
////                writerInformation.setWriterInformationFilePath("/file/" + fileName);
////
////            } catch (IllegalStateException | IOException e) {
////                System.out.println("실패");
////                e.printStackTrace();
////            }
////        }
//
//        writerInformationRepository.save(writerInformation);
//
//    }
}
