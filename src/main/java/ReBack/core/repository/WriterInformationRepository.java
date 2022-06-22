package ReBack.core.repository;

import ReBack.core.data.Member;
import ReBack.core.data.WriterInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WriterInformationRepository extends JpaRepository<WriterInformation, Long>,
        QuerydslPredicateExecutor<WriterInformation> {

    List<WriterInformation> findByWriter(Member writer);

//    List<WriterInformation> findAllByWriterLecturePlaceContainingAndAvailableStartTimeIsAfterAndAvailableFinishTimeBefore(String place, LocalDateTime start, LocalDateTime end);
}
