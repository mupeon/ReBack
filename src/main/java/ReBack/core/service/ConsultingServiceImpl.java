package ReBack.core.service;

import ReBack.core.data.*;
import ReBack.core.dto.ConsultingDTO;
import ReBack.core.dto.DesignDTO;
import ReBack.core.dto.PageRequestDTO;
import ReBack.core.dto.PageResultDTO;
import ReBack.core.repository.ConsultingRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

import static ReBack.core.data.QConsulting.consulting;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConsultingServiceImpl implements ConsultingService {

    @Autowired
    private final ConsultingRepository repository;

//    @Override
//    public Long register(ConsultingDTO dto) {
//        log.info(dto);
//        Consulting consulting = dtoToEntity(dto);
//        repository.save(consulting);
//        return consulting.getConsultingCode();
//    }

    @Override
    public PageResultDTO<ConsultingDTO, Consulting> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("consultingCode").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<Consulting> result = repository.findAll(booleanBuilder, pageable); // querydsl 사용
        Function<Consulting, ConsultingDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Consulting dtoToEntity(ConsultingDTO dto) {
        return ConsultingService.super.dtoToEntity(dto);
    }

    @Override
    public Consulting save(ConsultingDTO consultingDTO) {
        Consulting consulting = consultingDTO.toConsultingDTO();
        Consulting saveConsulting = repository.save(consulting);
        System.out.println("saveConsulting = " + saveConsulting);
        return saveConsulting;
    }

    //    @Override
//    public void deleteConsulting(Long consultingCode) {
//        repository.deleteById(consultingCode);
//    }
//
//    @Override
//    public ConsultingDTO read(Long consultingCode) {
//        Optional<Consulting> result = repository.findById(consultingCode);
//        System.out.println("result.get() = " + result.get());
//        return result.isPresent() ? entityToDTO(result.get()) : null;
//    }
//
//    @Override
//    public void remove(Long consultingCode) {
//        repository.deleteById(consultingCode);
//    }
//
//    @Override
//    public void modify(ConsultingDTO dto) {
//        Optional<Consulting> result = repository.findById(dto.getConsultingCode());
//        if (result.isPresent()) {
//            Consulting entity = result.get();
//            entity.changeConsultingName(dto.getConsultingName());
//            System.out.println("dto.getConsultingName() = " + dto.getConsultingName());
//            repository.save(entity);
//        }
//    }
//
    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        // Querydsl 처리
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QConsulting qConsulting = QConsulting.consulting;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qConsulting.consultingCode.gt(0L);
        booleanBuilder.and(expression);
        if (type == null || type.trim().length() == 0) { // 검색 조건이 없는 경우
            return booleanBuilder;
        }

        // 검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type.contains("n")) {
            conditionBuilder.or(qConsulting.consultingName.contains(keyword));
        }
//        if(type.contains("i")) {
//            conditionBuilder.or(qConsulting.designInfo.contains(keyword));
//        }

        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

}
