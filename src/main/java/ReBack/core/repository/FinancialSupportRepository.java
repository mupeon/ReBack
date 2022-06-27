package ReBack.core.repository;

import ReBack.core.data.FinancialSupport;
import ReBack.core.data.Member;
import ReBack.core.data.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinancialSupportRepository extends JpaRepository<FinancialSupport, Long> {
    @Query("select f from FinancialSupport as f where f.memberCode = :memberCode") //회원이 구매한 구매목록
    List<FinancialSupport> findByMemberCode(@Param("memberCode")Member memberCode);
}