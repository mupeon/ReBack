package ReBack.core.controller;

import ReBack.core.data.Lecture;
import ReBack.core.data.WriterInformation;
import ReBack.core.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LectureApiController {

    @Autowired
    LectureRepository lectureRepository;

    @PostMapping({"lecture/add"})
    public String lectureAdd(@Validated @RequestPart(value = "key") Lecture lecture, @RequestPart(value = "file")
            MultipartFile file, HttpServletRequest request) throws Exception {
        System.out.println(lecture);
        String fileName;
        if (file == null) {
            fileName = "";
        } else {
            fileName = file.getOriginalFilename();
            String filepath = request.getSession().getServletContext().getRealPath("") + "file\\";
            System.out.println("filepath  :    " + filepath);

            try {
                file.transferTo(new File(filepath + fileName));
                System.out.println("업로드 성공");
                lecture.setLectureFileName(fileName);
                lecture.setLectureFilePath("/file/" + fileName);
            } catch (IllegalStateException | IOException e) {
                System.out.println("실패");
                e.printStackTrace();
            }
        }

        lectureRepository.save(lecture);
        return "redirect:/writerpage/lecturelist";
    }

    @DeleteMapping("lecture/delete")
    public void lectureDelete(@RequestBody Lecture lecture) {
        Optional<Lecture> deleteLecture = lectureRepository.findById(lecture.getLectureCode());

        if(deleteLecture.isPresent()) {
            System.out.println(deleteLecture);
            lectureRepository.delete(lecture);
        }
    }

    @PutMapping("/lecture/update")
    public void writerProfileUpdate(@RequestBody Lecture lecture) {
        Optional<Lecture> searchLecture = lectureRepository.findById(lecture.getLectureCode());

        if (searchLecture.isPresent()) {
            lecture.setLectureFilePath(searchLecture.get().getLectureFilePath());
            lecture.setLectureFileName(searchLecture.get().getLectureFileName());
        }
        lectureRepository.save(lecture);
    }
}
