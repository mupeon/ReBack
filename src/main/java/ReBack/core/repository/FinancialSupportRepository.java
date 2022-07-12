package ReBack.core.repository;

import ReBack.core.data.FinancialSupport;
import ReBack.core.data.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;
import java.util.List;

public interface FinancialSupportRepository extends JpaRepository<FinancialSupport, Long> {
//    Optional<FinancialSupport> findByBirth(Integer birth);

    @Query("select f from FinancialSupport as f where f.memberCode = :memberCode") //회원이 구매한 구매목록
    List<FinancialSupport> findByMemberCode(@Param("memberCode")Member memberCode);

    @Query("select f from FinancialSupport f where f.financialType like '%정기후원'")
    List<FinancialSupport> findByType1();

    @Query("select f from FinancialSupport f where f.financialType like '%일시후원'")
    List<FinancialSupport> findByType2();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers between '2004-01-01' and '2013-12-31'")
    List<FinancialSupport> findByAge1();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers between '1994-01-01' and '2003-12-31'")
    List<FinancialSupport> findByAge2();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers between '1984-01-01' and '1993-12-31'")
    List<FinancialSupport> findByAge3();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers between '1974-01-01' and '1983-12-31'")
    List<FinancialSupport> findByAge4();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers between '1964-01-01' and '1973-12-31'")
    List<FinancialSupport> findByAge5();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers between '1954-01-01' and '1963-12-31'")
    List<FinancialSupport> findByAge6();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers between '1944-01-01' and '1953-12-31'")
    List<FinancialSupport> findByAge7();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers between '1934-01-01' and '1943-12-31' ")//10대
    List<FinancialSupport> findByAge8();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode")//All
    List<FinancialSupport> findByAgeAll();

    @Query("select f from FinancialSupport f where f.financialDate like '%-01-%'")
    List<FinancialSupport> findByMonth1();

    @Query("select f from FinancialSupport f where f.financialDate like '%-02-%'")
    List<FinancialSupport> findByMonth2();

    @Query("select f from FinancialSupport f where f.financialDate like '%-03-%'")
    List<FinancialSupport> findByMonth3();

    @Query("select f from FinancialSupport f where f.financialDate like '%-04-%'")
    List<FinancialSupport> findByMonth4();

    @Query("select f from FinancialSupport f where f.financialDate like '%-05-%'")
    List<FinancialSupport> findByMonth5();

    @Query("select f from FinancialSupport f where f.financialDate like '%-06-%'")
    List<FinancialSupport> findByMonth6();

    @Query("select f from FinancialSupport f where f.financialDate like '%-07-%'")
    List<FinancialSupport> findByMonth7();

    @Query("select f from FinancialSupport f where f.financialDate like '%-08-%'")
    List<FinancialSupport> findByMonth8();

    @Query("select f from FinancialSupport f where f.financialDate like '%-09-%'")
    List<FinancialSupport> findByMonth9();

    @Query("select f from FinancialSupport f where f.financialDate like '%-10-%'")
    List<FinancialSupport> findByMonth10();

    @Query("select f from FinancialSupport f where f.financialDate like '%-11-%'")
    List<FinancialSupport> findByMonth11();

    @Query("select f from FinancialSupport f where f.financialDate like '%-12-%'")
    List<FinancialSupport> findByMonth12();

    @Query("select f from FinancialSupport f where f.financialDate = f.financialDate")
    List<FinancialSupport> findByMonthAll();

}