package ReBack.core.service;

import ReBack.core.data.Consulting;
import ReBack.core.data.Member;
import ReBack.core.dto.ConsultingDTO;
import ReBack.core.dto.PageRequestDTO;
import ReBack.core.dto.PageResultDTO;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

public interface ConsultingService {
//    Long register(ConsultingDTO dto);

    PageResultDTO<ConsultingDTO, Consulting> getList(PageRequestDTO pageRequestDTO); // 목록 처리

    default Consulting dtoToEntity(ConsultingDTO dto) {
        Consulting consulting = Consulting.builder()
                .consultingCode(dto.getConsultingCode())
                .consultingName(dto.getConsultingName())
                .fileName(dto.getFileName())
                .filePath(dto.getFilePath())
                .startingTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .consultingPlace(dto.getConsultingPlace())
                .member(dto.getMemberCode().toMemberDTO())
//                .writer(member)
                .build();
        return consulting;
    }

    default ConsultingDTO entityToDTO(Consulting consulting) {
        ConsultingDTO consultingDTO = ConsultingDTO.builder()
                .consultingCode(consulting.getConsultingCode())
                .consultingName(consulting.getConsultingName())
                .fileName(consulting.getFileName())
                .filePath(consulting.getFilePath())
                .startingTime(consulting.getStartingTime())
                .endTime(consulting.getEndTime())
                .consultingPlace(consulting.getConsultingPlace())
//                .memberCode(consulting.getMember().toMemberDTO())
                .regDate(consulting.getRegDate())
                .modDate(consulting.getModDate())
                .build();
        return consultingDTO;
    }

    Consulting save(ConsultingDTO consultingDTO);

//    void deleteConsulting(Long consultingCode);

//    ConsultingDTO read(Long consultingDTO);

//    void remove(Long consultingCode);

//    void modify(ConsultingDTO dto);

}
