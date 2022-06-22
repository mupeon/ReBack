package ReBack.core.repository;

import ReBack.core.data.Consulting;
import ReBack.core.data.Member;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultingRepository extends JpaRepository<Consulting, Long>,
        QuerydslPredicateExecutor<Consulting> {

    List<Consulting> findAllByMember(Member member);
//    @Query("select c from Consulting c left join c.writer w where c.consultingCode=:consultingCode")
//    Object getConsultingWithWriter(@Param("consultingCode") Long consultingCode);
//
//    @Query("SELECT c, w " +
//        " FROM Consulting c LEFT JOIN c.writer w " +
//        " WHERE c.consultingCode=:consultingCode")
//    Object getConsultingByConsultingCode(@Param("consultingCode") Long consultingCode);
//
//    Page<Consulting> findAll(BooleanBuilder booleanBuilder, Pageable pageable);
}
