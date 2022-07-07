package ReBack.core.repository;

import ReBack.core.data.ClothingSponsor;
import ReBack.core.data.FinancialSupport;
import ReBack.core.data.Member;
import ReBack.core.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothingSponsorRepository extends JpaRepository<ClothingSponsor, Long> {
    @Query("select c from ClothingSponsor as c where c.memberCode = :memberCode") //회원이 구매한 구매목록
    List<ClothingSponsor> findByMemberCode(@Param("memberCode")Member memberCode);

    @Query("select c from ClothingSponsor c where c.pickupArea like '대구%'")
    List<ClothingSponsor> findByArea1();

    @Query("select c from ClothingSponsor c where c.pickupArea like '서울%'")
    List<ClothingSponsor> findByArea2();

    @Query("select c from ClothingSponsor c where c.pickupArea like '부산%'")
    List<ClothingSponsor> findByArea3();

    @Query("select c from ClothingSponsor c where c.pickupArea like '대전%'")
    List<ClothingSponsor> findByArea4();

    @Query("select c from ClothingSponsor c where c.pickupDate='22/01/%'")
    List<ClothingSponsor> findByDate1();

//    @Query("select ch from ClothingSponsor ch where ch.amount =:amount and ch.pickupDate=:pickupDate and pickupDate between DATE_SUB(NOW(), INTERVAL 7 DAY) and NOW() order by:pickupDate")
//    List<Product> selectCate2(@Param("categoryCode") Long categoryCode, @Param("memberCode1") Long memberCode1);

}