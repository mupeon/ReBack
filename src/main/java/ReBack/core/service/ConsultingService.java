package ReBack.core.service;

import ReBack.core.data.Consulting;
import ReBack.core.data.Design;
import ReBack.core.data.Member;
import ReBack.core.dto.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

public interface ConsultingService {
//    Long register(ConsultingDTO dto);

    PageResultDTO<ConsultingDTO, Consulting> getList(PageRequestDTO pageRequestDTO); // 목록 처리
    PageResultDTO<ConsultingDTO, Consulting> getConsultingList(PageRequestDTO pageRequestDTO); // 목록 처리

    default Consulting dtoToEntity(ConsultingDTO dto) {
        Consulting consulting = Consulting.builder()
                .consultingCode(dto.getConsultingCode())
                .consultingName(dto.getConsultingName())
                .fileName(dto.getFileName())
                .filePath(dto.getFilePath())
                .startingTime(LocalDateTime.now())
                .endTime(LocalDateTime.now())
                .consultingInfo(dto.getConsultingInfo())
                .consultingPlace(dto.getConsultingPlace())
                .category(dto.getCategoryCode().toCategoryDTO())
                .material(dto.getMaterialCode().toMaterialDTO())
                .member(dto.getMemberCode().toWriterDTO())
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
                .consultingInfo(consulting.getConsultingInfo())
//                .memberCode(consulting.getMember().toMemberDTO())
                .regDate(consulting.getRegDate())
                .modDate(consulting.getModDate())
                .categoryCode(consulting.getCategory().toCategoryDTO())
                .materialCode(consulting.getMaterial().toMaterial())
                .build();
        return consultingDTO;
    }

    Consulting save(ConsultingDTO consultingDTO);

//    void deleteConsulting(Long consultingCode);

    ConsultingDTO read(Long consultingDTO);

    void remove(Long consultingCode);

    void modify(ConsultingDTO dto);

    List<WriterProfileDTO> getWriterProfile(ConsultingMatchingDTO consultingMatchingDTO);

    PayDTO getPayList(String start, String end, String place, String name);

    public List<Consulting> getOwnList(PageRequestDTO requestDTO, Principal principal);

}
