package ReBack.core.repository;

import ReBack.core.data.FinancialSupport;
import ReBack.core.data.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinancialSupportRepository extends JpaRepository<FinancialSupport, Long> {
    @Query("select f from FinancialSupport as f where f.memberCode = :memberCode") //회원이 구매한 구매목록
    List<FinancialSupport> findByMemberCode(@Param("memberCode")Member memberCode);

    @Query("select f from FinancialSupport f where f.financialType like '%정기후원'")
    List<FinancialSupport> findByType1();

    @Query("select f from FinancialSupport f where f.financialType like '%일시후원'")
    List<FinancialSupport> findByType2();
}