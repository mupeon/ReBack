package ReBack.core.repository;

import ReBack.core.data.ClothingSponsor;
import ReBack.core.data.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothingSponsorRepository extends JpaRepository<ClothingSponsor, Long> {
    @Query("select c from ClothingSponsor as c where c.memberCode = :memberCode") //회원이 구매한 구매목록
    List<ClothingSponsor> findByMemberCode(@Param("memberCode")Member memberCode);
}