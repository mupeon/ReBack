package ReBack.core.controller;

import ReBack.core.data.Lecture;
import ReBack.core.data.WriterInformation;
import ReBack.core.repository.LectureRepository;
import ReBack.core.repository.WriterInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class WriterProfileApiController {

    @Autowired
    WriterInformationRepository writerInformationRepository;

    @Autowired
    LectureRepository lectureRepository;

    @PutMapping("/update")
    public void writerProfileUpdate1(@RequestBody WriterInformation writerInformation) {

        writerInformationRepository.save(writerInformation);
    }

    @DeleteMapping("/delete")
    public void productDelete1(@RequestBody WriterInformation writerInformation) {
        Optional<WriterInformation> deleteWriterInformation = writerInformationRepository.findById(writerInformation.getWriterInformationCode());

        if (deleteWriterInformation.isPresent()) {
            writerInformationRepository.delete(writerInformation);
        }

    }

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
}
