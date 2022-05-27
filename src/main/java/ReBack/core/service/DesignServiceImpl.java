package ReBack.core.service;

import ReBack.core.data.Design;
import ReBack.core.data.QDesign;
import ReBack.core.dto.DesignDTO;
import ReBack.core.dto.PageRequestDTO;
import ReBack.core.dto.PageResultDTO;
import ReBack.core.repository.CategoryRepository;
import ReBack.core.repository.DesignRepository;
import ReBack.core.repository.MaterialRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor // 의존성 자동 주입
public class DesignServiceImpl implements DesignService {

    @Autowired
    private final DesignRepository repository;

//    @Override
//    public Long register(DesignDTO dto) {
//        log.info("DTO------------------------------------");
//        log.info(dto);
//        Design entity = dtoToEntity(dto);
//        log.info(entity);
//        repository.save(entity);
//        return entity.getDesignCode();
//    }

    @Override
    public PageResultDTO<DesignDTO, Design> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("designCode").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<Design> result = repository.findAll(booleanBuilder, pageable); // querydsl 사용
        Function<Design, DesignDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Design dtoToEntity(DesignDTO dto) {

        return DesignService.super.dtoToEntity(dto);
    }
    // 굳이 없어도 되는 메서드 책 다시 확인하기

    @Override
    public Design save(DesignDTO designDTO) {
        Design design = designDTO.toDesign();
        Design saveDesign = repository.save(design);
        System.out.println("saveDesign : " + saveDesign);
        return saveDesign;
    }

    @Override
    public void deleteDesign(Long designCode) {
        repository.deleteById(designCode);
    }

    @Override
    public DesignDTO read(Long designCode) {
        Optional<Design> result = repository.findById(designCode);
        System.out.println("result = " + result.get());
        return result.isPresent()? entityToDto(result.get()):null;
    }

    @Override
    public void remove(Long designCode) {
        repository.deleteById(designCode);
    }

    @Override
    public void modify(DesignDTO dto) {
//        System.out.println("dto.getDesignInfo() = " + dto.getDesignInfo());
//        System.out.println("dto.getDesignName() = " + dto.getDesignName());
        // 업데이트 하는 항목 : '제목', '내용'
        Optional<Design> result = repository.findById(dto.getDesignCode());
        if(result.isPresent()) {
            Design entity = result.get();
            entity.changeDesignName(dto.getDesignName());
            entity.changeDesignInfo(dto.getDesignInfo());
            System.out.println("dto.getDesignInfo() = " + dto.getDesignInfo());
            System.out.println("dto.getDesignName() = " + dto.getDesignName());
            repository.save(entity);
        }
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        // Querydsl 처리
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QDesign qDesign = QDesign.design;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qDesign.designCode.gt(0L); // designCode>0 조건만 생성
        booleanBuilder.and(expression);
        if(type==null || type.trim().length()==0) { // 검색 조건이 없는 경우
            return booleanBuilder;
        }

        // 검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("n")) {
            conditionBuilder.or(qDesign.designName.contains(keyword));
        }
        if(type.contains("i")) {
            conditionBuilder.or(qDesign.designInfo.contains(keyword));
        }

        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;


    }
}
