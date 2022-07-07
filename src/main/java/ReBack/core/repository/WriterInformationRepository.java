package ReBack.core.repository;

import ReBack.core.data.Member;
import ReBack.core.data.WriterInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WriterInformationRepository extends JpaRepository<WriterInformation, Long>,
        QuerydslPredicateExecutor<WriterInformation> {

    Optional<WriterInformation> findByWriterInformationCode(Long writerInformationCode);
    List<WriterInformation> findByWriter(Member writer);


    @Query("select w from WriterInformation as w where w.writer.memberCode = :memberCode") //회원이 구매한 구매목록
    List<WriterInformation> findByMemberCode(@Param("memberCode")Member memberCode);

}
