package ReBack.core.service;

import ReBack.core.data.Consulting;
import ReBack.core.data.QConsulting;
import ReBack.core.data.QDesign;
import ReBack.core.data.WriterInformation;
import ReBack.core.dto.*;
import ReBack.core.repository.MemberRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public interface WriterInformationService {
    PageResultDTO<WriterInformationDTO, WriterInformation> getList(PageRequestDTO pageRequestDTO); // 목록 처리

    PageResultDTO<WriterInformationDTO, WriterInformation> getWriterInformationList(PageRequestDTO pageRequestDTO); // 목록 처리

    default WriterInformation dtoToEntity(WriterInformationDTO dto) {
        WriterInformation writerInformation = WriterInformation.builder()
                .writerInformationCode(dto.getWriterInformationCode())
                .writerLecturePlace(dto.getWriterLecturePlace())
                .availableStartTime(dto.getAvailableStartTime())
                .availableFinishTime(dto.getAvailableFinishTime())
                .availableDay(dto.getAvailableDay())
                .writer(dto.getWriter().toWriterDTO())
                .category(dto.getCategoryCode().toCategoryDTO())
                .material(dto.getMaterialCode().toMaterialDTO())
                .build();
        return writerInformation;
    }

    default WriterInformationDTO entityToDTO(WriterInformation writerInformation) {
        WriterInformationDTO writerInformationDTO = WriterInformationDTO.builder()
                .writerInformationCode(writerInformation.getWriterInformationCode())
                .writerLecturePlace(writerInformation.getWriterLecturePlace())
                .availableStartTime(writerInformation.getAvailableStartTime())
                .availableFinishTime(writerInformation.getAvailableFinishTime())
                .availableDay(writerInformation.getAvailableDay())
                .writer(writerInformation.getWriter().toWriterDTO())
                .categoryCode(writerInformation.getCategory().toCategoryDTO())
                .materialCode(writerInformation.getMaterial().toMaterial())
                .build();
        return writerInformationDTO;
    }

    WriterInformation save(WriterInformationDTO writerInformationDTO);

//    void deleteConsulting(Long consultingCode);

    WriterInformationDTO read(Long writerInformationCode);
//
//    void remove(Long writerInformationCode);
//
//    void modify(WriterInformationDTO dto);

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        // Querydsl 처리
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QConsulting qConsulting = QConsulting.consulting;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qConsulting.consultingCode.gt(0L); // designCode>0 조건만 생성
        booleanBuilder.and(expression);
        if(type==null || type.trim().length()==0) { // 검색 조건이 없는 경우
            return booleanBuilder;
        }

        // 검색 조건을 작성하기
//        BooleanBuilder conditionBuilder = new BooleanBuilder();
//        if(type.contains("p")) {
//            conditionBuilder.or(qConsulting.consulingPlace.contains(keyword));
//        }


        // 모든 조건 통합
//        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;


    }

//    private ConsultingOrderDTO getOrder(Long writerInformationCode){
//
//        return ConsultingOrderDTO;
//    }
}
