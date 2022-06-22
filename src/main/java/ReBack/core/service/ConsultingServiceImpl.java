package ReBack.core.service;

import ReBack.core.data.*;
import ReBack.core.dto.*;
import ReBack.core.repository.ConsultingRepository;
import ReBack.core.repository.MemberRepository;
import ReBack.core.repository.WriterProfileRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class ConsultingServiceImpl implements ConsultingService {
    private final ConsultingRepository repository;

    private final MemberRepository memberRepository;

    private final WriterProfileRepository writerProfileRespository;
//    private final WriterInformationRepository writerInformationRepository;

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
    @Override
    public ConsultingDTO read(Long consultingCode) {
        Optional<Consulting> result = repository.findById(consultingCode);
        System.out.println("result.get() = " + result.get());
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

    @Override
    public void remove(Long consultingCode) {
        repository.deleteById(consultingCode);
    }

    @Override
    public void modify(ConsultingDTO dto) {
        Optional<Consulting> result = repository.findById(dto.getConsultingCode());
        if (result.isPresent()) {
            Consulting entity = result.get();
            entity.changeConsultingName(dto.getConsultingName());
            entity.changeConsultingInfo(dto.getConsultingInfo());
            System.out.println("dto.getConsultingName() = " + dto.getConsultingName());
            repository.save(entity);
        }
    }

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


    private BooleanBuilder getConsultingSearch(PageRequestDTO requestDTO) {
        //Querydsl 처리
        String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QConsulting qConsulting = QConsulting.consulting;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qConsulting.consultingCode.gt(0L); // consultingCode>0 조건만 생성
        booleanBuilder.and(expression);
        if(type==null || type.trim().length() == 0) {
            // 검색 조건이 없는 경우
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

    @Override
    public PageResultDTO<ConsultingDTO, Consulting> getConsultingList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("consultingCode").descending());
        BooleanBuilder booleanBuilder = getConsultingSearch(requestDTO);
        Page<Consulting> result = repository.findAll(booleanBuilder, pageable); // querydsl 사용
        Function<Consulting, ConsultingDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public List<WriterProfileDTO> getWriterProfile(ConsultingMatchingDTO consultingMatchingDTO) {

        String[] place = consultingMatchingDTO.getConsultingPlace().split(" ");
        String date = consultingMatchingDTO.getConsultingDate();
        String start = consultingMatchingDTO.getConsultingTimeString();
        String end = consultingMatchingDTO.getEndTimeString();


        // 포맷터로 일자+시작시간, 일자+종료시간 만들고 매개변수 넣기
//        String staringDateString = date+"T"+start;
//        String endDateString = date+"T"+end;
        LocalDateTime startingDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        LocalDateTime endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//        LocalDateTime startingDate = LocalDateTime.parse(staringDateString, formatter);
//        LocalDateTime endDate = LocalDateTime.parse(endDateString, formatter);
        System.out.println("endDate = " + endDate);
        System.out.println("startingDate = " + startingDate);

        List<WriterInformation> writerProfile = writerProfileRespository.getWriterProfile(place[0],startingDate, endDate);
//        List<WriterInformation> writerProfile = writerInformationRepository.findAllByWriterLecturePlaceContainingAndAvailableStartTimeIsAfterAndAvailableFinishTimeBefore(place, startingDate, endDate);
        List<WriterProfileDTO> writerProfileDTOs = new ArrayList<>();

        writerProfile.stream().map(writerInformation -> writerInformation.toWriterProfileDTO()).forEach(writerProfileDTOs::add);
        System.out.println("writerProfile = " + writerProfile);
        System.out.println("writerProfileDTOs = " + writerProfileDTOs);
        return writerProfileDTOs;
    }

    // localdatetime 형식으로 변환하는 메서드
    private LocalDateTime StringToLocalDateTime(String LocalDateTimeStr) {
        LocalDateTime dateTime = LocalDateTime.from(
                Instant.from(
                        DateTimeFormatter.ISO_DATE_TIME.parse(LocalDateTimeStr)
                ).atZone(ZoneId.of("Asia/Seoul"))
        );
        return dateTime;
    }

    public PayDTO getPayList(String start, String end, String place, String name) {
        System.out.println("start = " + start);
        System.out.println("end = " + end);
//        LocalDateTime startingDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm.ss"));
//        LocalDateTime endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm.ss"));
        LocalDateTime startingDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);

        int time = (endDate.getHour() - startingDate.getHour());
        int pay = time * 1000;
        String fPlace = place;
        String memberName = name;
        LocalDateTime fStart = startingDate;
        LocalDateTime fEnd = endDate;

        PayDTO result = PayDTO.builder()
                .start(fStart)
                .end(fEnd)
                .place(fPlace)
                .memberName(memberName)
                .time(time)
                .pay(pay)
                .build();
        return result;
    }

    @Override
    public List<Consulting> getOwnList(PageRequestDTO requestDTO, Principal principal) {
        String name = principal.getName();
        Member member = memberRepository.findByMemberName(name);
        List<Consulting> allByMember = repository.findAllByMember(member);
        return allByMember;
    }
}
