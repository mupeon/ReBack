package ReBack.core.repository;

import ReBack.core.data.FinancialSupport;
import ReBack.core.data.Member;
import ReBack.core.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findByMemberCode(Long MemberCode);
    Optional<Member> findByMemberEmail(String memberEmail);
    Optional<Member> findByMemberPhoneNumber(String memberPhoneNumber);
    Optional<Member> findByMemberJoinDate(LocalDate memberJoinDate);
    Member findByMemberName(String memberName);


    @Query("select m from Member m where m.summers like '2013%' or m.summers like '2012%' or m.summers like '2011%' or m.summers like '2010%' or m.summers like '2009%' " +
            "or m.summers like '2008%' or m.summers like '2007%' or m.summers like '2006%' or m.summers like '2005%' or m.summers like '2004%'")//10대
    List<Member> findBySummers1();

    @Query("select m from Member m where m.summers like '2003%' or m.summers like '2002%' or m.summers like '2001%' or m.summers like '2000%' or m.summers like '1999%' " +
            "or m.summers like '1998%' or m.summers like '1997%' or m.summers like '1996%' or m.summers like '1995%' or m.summers like '1994%'")//20대
    List<Member> findBySummers2();

    @Query("select m from Member m where m.summers like '1993%' or m.summers like '1992%' or m.summers like '1991%' or m.summers like '1990%' or m.summers like '1989%' " +
            "or m.summers like '1988%' or m.summers like '1987%' or m.summers like '1986%' or m.summers like '1985%' or m.summers like '1984%'")//30대
    List<Member> findBySummers3();

    @Query("select m from Member m where m.summers like '1983%' or m.summers like '1982%' or m.summers like '1981%' or m.summers like '1980%' or m.summers like '1979%' " +
            "or m.summers like '1978%' or m.summers like '1977%' or m.summers like '1976%' or m.summers like '1975%' or m.summers like '1974%'")//40대
    List<Member> findBySummers4();

    @Query("select m from Member m where m.summers like '1973%' or m.summers like '1972%' or m.summers like '1971%' or m.summers like '1970%' or m.summers like '1969%' " +
            "or m.summers like '1968%' or m.summers like '1967%' or m.summers like '1966%' or m.summers like '1965%' or m.summers like '1964%'")//50대
    List<Member> findBySummers5();

    @Query("select m from Member m where m.summers like '1963%' or m.summers like '1962%' or m.summers like '1961%' or m.summers like '1960%' or m.summers like '1959%' " +
            "or m.summers like '1958%' or m.summers like '1957%' or m.summers like '1956%' or m.summers like '1955%' or m.summers like '1954%'")//60대
    List<Member> findBySummers6();

    @Query("select m from Member m where m.summers like '1953%' or m.summers like '1952%' or m.summers like '%51%' or m.summers like '1950%' or m.summers like '1949%' " +
            "or m.summers like '1948%' or m.summers like '1947%' or m.summers like '1946%' or m.summers like '1945%' or m.summers like '1944%'")//70대
    List<Member> findBySummers7();

    @Query("select m from Member m where m.summers like '1943%' or m.summers like '1942%' or m.summers like '1941%' or m.summers like '1940%' or m.summers like '1939%' " +
            "or m.summers like '1938%' or m.summers like '1937%' or m.summers like '1936%' or m.summers like '1935%' or m.summers like '1934%'")
    List<Member> findBySummers8();


    @Query("select m from Member m where m.memberAddress like '%서울%'")//
    List<Member> findByAdd1();
    @Query("select m from Member m where m.memberAddress like '%부산%'")//
    List<Member> findByAdd2();
    @Query("select m from Member m where m.memberAddress like '%대구%'")//
    List<Member> findByAdd3();
    @Query("select m from Member m where m.memberAddress like '%인천%'")//
    List<Member> findByAdd4();
    @Query("select m from Member m where m.memberAddress like '%광주%'")//
    List<Member> findByAdd5();
    @Query("select m from Member m where m.memberAddress like '%대전%'")//
    List<Member> findByAdd6();
    @Query("select m from Member m where m.memberAddress like '%울산%'")//
    List<Member> findByAdd7();
    @Query("select m from Member m where m.memberAddress like '%강원%'")//
    List<Member> findByAdd8();
    @Query("select m from Member m where m.memberAddress like '%경기%'")//
    List<Member> findByAdd9();
    @Query("select m from Member m where m.memberAddress like '%경남%'")//
    List<Member> findByAdd10();
    @Query("select m from Member m where m.memberAddress like '%경북%'")//
    List<Member> findByAdd11();
    @Query("select m from Member m where m.memberAddress like '%전남%'")//
    List<Member> findByAdd12();
    @Query("select m from Member m where m.memberAddress like '%전북%'")//
    List<Member> findByAdd13();
    @Query("select m from Member m where m.memberAddress like '%제주%'")//
    List<Member> findByAdd14();
    @Query("select m from Member m where m.memberAddress like '%충남%'")//
    List<Member> findByAdd15();
    @Query("select m from Member m where m.memberAddress like '%충북%'")//
    List<Member> findByAdd16();


    @Query("select m from Member m where m.role='ROLE_COMPANY'")
    List<Member> findByRole1();
    @Query("select m from Member m where m.role='ROLE_AUTHOR'")
    List<Member> findByRole2();
    @Query("select m from Member m where m.role='ROLE_MEMBER'")
    List<Member> findByRole3();



//    @Query("select m from Member m where m.memberJoinDate like '22/01%'")
//    List<Member> findByMemberJoinDate1();
//    @Query("select m from Member m where m.memberJoinDate like '22/02%'")
//    List<Member> findByMemberJoinDate2();
//    @Query("select m from Member m where m.memberJoinDate like '22/03%'")
//    List<Member> findByMemberJoinDate3();
//    @Query("select m from Member m where m.memberJoinDate like '22/04%'")
//    List<Member> findByMemberJoinDate4();
//    @Query("select m from Member m where m.memberJoinDate like '22/05%'")
//    List<Member> findByMemberJoinDate5();
//    @Query("select m from Member m where m.memberJoinDate like '22/06%'")
//    List<Member> findByMemberJoinDate6();
//    @Query("select m from Member m where m.memberJoinDate like '22/07%'")
//    List<Member> findByMemberJoinDate7();
//    @Query("select m from Member m where m.memberJoinDate like '22/08%'")
//    List<Member> findByMemberJoinDate8();
//    @Query("select m from Member m where m.memberJoinDate like '22/09%'")
//    List<Member> findByMemberJoinDate9();
//    @Query("select m from Member m where m.memberJoinDate like '22/10%'")
//    List<Member> findByMemberJoinDate10();
//    @Query("select m from Member m where m.memberJoinDate like '22/11%'")
//    List<Member> findByMemberJoinDate11();
//    @Query("select m from Member m where m.memberJoinDate like '22/12%'")
//    List<Member> findByMemberJoinDate12();

//    // 후원 쪽 Query
//    @Query("select  f from Member m, FinancialSupport f where f.memberCode.memberCode = m.memberCode and f.memberCode.summers like '2013%' or f.memberCode.summers like '2012%' or f.memberCode.summers like '2011%' or f.memberCode.summers like '2010%' or f.memberCode.summers like '2009%' " +
//            "or f.memberCode.summers like '2008%' or f.memberCode.summers like '2007%' or f.memberCode.summers like '2006%' or f.memberCode.summers like '2005%' or f.memberCode.summers like '2004%'")//10대
//    List<Member> findByAge1();
//    @Query("select m from Member m where m.summers like '1963%' or m.summers like '1962%' or m.summers like '1961%' or m.summers like '1960%' or m.summers like '1959%' " +
//            "or m.summers like '1958%' or m.summers like '1957%' or m.summers like '1956%' or m.summers like '1955%' or m.summers like '1954%'")//60대
//    List<Member> findBySummers6();
}