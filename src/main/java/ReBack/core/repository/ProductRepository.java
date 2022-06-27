package ReBack.core.repository;

import ReBack.core.data.Member;
import ReBack.core.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByMemberCode(Member memberCode);

    @Query("select p from Product p where p.categoryCode.categoryCode =:categoryCode")
    List<Product> selectCate(@Param("categoryCode") Long categoryCode);

    @Query("select p from Product p where p.categoryCode.categoryCode =:categoryCode and p.memberCode.memberCode=:memberCode1")
    List<Product> selectCate2(@Param("categoryCode") Long categoryCode,@Param("memberCode1") Long memberCode1);


}