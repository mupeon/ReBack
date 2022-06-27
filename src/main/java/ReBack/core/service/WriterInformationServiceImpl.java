package ReBack.core.service;

import ReBack.core.data.*;
import ReBack.core.dto.PageRequestDTO;
import ReBack.core.dto.PageResultDTO;
import ReBack.core.dto.WriterInformationDTO;
import ReBack.core.repository.WriterInformationRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class WriterInformationServiceImpl implements WriterInformationService {

    private final WriterInformationRepository repository;



    @Override
    public PageResultDTO<WriterInformationDTO, WriterInformation> getList(PageRequestDTO requestDTO) {
        System.out.println("WriterInformation serviceImpl동작");
        Pageable pageable = requestDTO.getPageable(Sort.by("writerInformationCode").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<WriterInformation> result = repository.findAll(booleanBuilder, pageable); // querydsl 사용
        Function<WriterInformation, WriterInformationDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public WriterInformation dtoToEntity(WriterInformationDTO dto) {
        return WriterInformationService.super.dtoToEntity(dto);
    }

    @Override
    public WriterInformation save(WriterInformationDTO writerInformationDTO) {
        WriterInformation writerInformation = writerInformationDTO.toWriterInformation();
        WriterInformation saveWriterInformation = repository.save(writerInformation);
        System.out.println("saveWriterInformation = " + saveWriterInformation);
        return saveWriterInformation;
    }

    @Override
    public WriterInformationDTO read(Long writerInformationCode) {
        Optional<WriterInformation> result = repository.findById(writerInformationCode);
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

    // 매칭 작가 프로필이므로 수정, 삭제 필요 없음. 조회만 하면됨.

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        // Querydsl 처리
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QWriterInformation qWriterInformation = QWriterInformation.writerInformation;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qWriterInformation.writerInformationCode.gt(0L);
        booleanBuilder.and(expression);
        if (type == null || type.trim().length() == 0) { // 검색 조건이 없는 경우
            return booleanBuilder;
        }

        // 검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type.contains("n")) {
            conditionBuilder.or(qWriterInformation.writerLecturePlace.contains(keyword));
        }
//        if(type.contains("i")) {
//            conditionBuilder.or(qConsulting.designInfo.contains(keyword));
//        }

        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }


    private BooleanBuilder getWriterInformationSearch(PageRequestDTO requestDTO) {
        // Querydsl 처리
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QWriterInformation qWriterInformation = QWriterInformation.writerInformation;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qWriterInformation.writerInformationCode.gt(0L); // designCode>0 조건만 생성
        booleanBuilder.and(expression);
        if(type==null || type.trim().length()==0) { // 검색 조건이 없는 경우
            return booleanBuilder;
        }

        // 검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("p")) {
            conditionBuilder.or(qWriterInformation.writerLecturePlace.contains(keyword));
        }

        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;


    }

    @Override
    public PageResultDTO<WriterInformationDTO, WriterInformation> getWriterInformationList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("writerInformationCode").descending());
        BooleanBuilder booleanBuilder = getWriterInformationSearch(requestDTO);
        Page<WriterInformation> result = repository.findAll(booleanBuilder, pageable); // querydsl 사용
        Function<WriterInformation, WriterInformationDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }
}
