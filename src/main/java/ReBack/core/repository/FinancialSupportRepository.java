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

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers like '2013%' or f.memberCode.summers like '2012%' or f.memberCode.summers like '2011%' or f.memberCode.summers like '2010%' or f.memberCode.summers like '2009%'" +
            "or f.memberCode.summers like '2008%' or f.memberCode.summers like '2007%' or f.memberCode.summers like '2006%' or f.memberCode.summers like '2005%' or f.memberCode.summers like '2004%'")//10대
    List<FinancialSupport> findByAge1();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers like '2003%' or f.memberCode.summers like '2002%' or f.memberCode.summers like '2001%' or f.memberCode.summers like '2000%' or f.memberCode.summers like '1999%'" +
            "or f.memberCode.summers like '1998%' or f.memberCode.summers like '1997%' or f.memberCode.summers like '1996%' or f.memberCode.summers like '1995%' or f.memberCode.summers like '1994%'")//10대
    List<FinancialSupport> findByAge2();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers like '1993%' or f.memberCode.summers like '1992%' or f.memberCode.summers like '1991%' or f.memberCode.summers like '1990%' or f.memberCode.summers like '1989%'" +
            "or f.memberCode.summers like '1988%' or f.memberCode.summers like '1987%' or f.memberCode.summers like '1986%' or f.memberCode.summers like '1985%' or f.memberCode.summers like '1984%'")//10대
    List<FinancialSupport> findByAge3();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers like '1983%' or f.memberCode.summers like '1982%' or f.memberCode.summers like '1981%' or f.memberCode.summers like '1980%' or f.memberCode.summers like '1979%'" +
            "or f.memberCode.summers like '1978%' or f.memberCode.summers like '1977%' or f.memberCode.summers like '1976%' or f.memberCode.summers like '1975%' or f.memberCode.summers like '1974%'")//10대
    List<FinancialSupport> findByAge4();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers like '1973%' or f.memberCode.summers like '1972%' or f.memberCode.summers like '1971%' or f.memberCode.summers like '1970%' or f.memberCode.summers like '1969%'" +
            "or f.memberCode.summers like '1968%' or f.memberCode.summers like '1967%' or f.memberCode.summers like '1966%' or f.memberCode.summers like '1965%' or f.memberCode.summers like '1964%'")//10대
    List<FinancialSupport> findByAge5();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers like '1963%' or f.memberCode.summers like '1962%' or f.memberCode.summers like '1961%' or f.memberCode.summers like '1960%' or f.memberCode.summers like '1959%'" +
            "or f.memberCode.summers like '1958%' or f.memberCode.summers like '1957%' or f.memberCode.summers like '1956%' or f.memberCode.summers like '1955%' or f.memberCode.summers like '1954%'")//10대
    List<FinancialSupport> findByAge6();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers like '1953%' or f.memberCode.summers like '1952%' or f.memberCode.summers like '1951%' or f.memberCode.summers like '1950%' or f.memberCode.summers like '1949%'" +
            "or f.memberCode.summers like '1948%' or f.memberCode.summers like '1947%' or f.memberCode.summers like '1946%' or f.memberCode.summers like '1945%' or f.memberCode.summers like '1944%'")//10대
    List<FinancialSupport> findByAge7();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers like '1943%' or f.memberCode.summers like '1942%' or f.memberCode.summers like '1941%' or f.memberCode.summers like '1940%' or f.memberCode.summers like '1939%'" +
            "or f.memberCode.summers like '1938%' or f.memberCode.summers like '1937%' or f.memberCode.summers like '1936%' or f.memberCode.summers like '1935%' or f.memberCode.summers like '1934%'")//10대
    List<FinancialSupport> findByAge8();


//    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers between '1934-01-01' and '1943-12-31' ")//10대
//    List<FinancialSupport> findByAge80();

    @Query("select distinct f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode")//All
    List<FinancialSupport> findByAgeAll();



}