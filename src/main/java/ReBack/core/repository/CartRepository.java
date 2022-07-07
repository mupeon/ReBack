package ReBack.core.repository;

import ReBack.core.data.Cart;
import ReBack.core.data.Member;
import ReBack.core.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
public interface CartRepository extends JpaRepository<Cart, Long> {
//    String findByMemberCode(Member memberCode);

    @Query(
            "select c from Cart c where c.memberCode =:memberCode and c.ordersState = 0")
    List<Cart> findByMemberCode(@Param("memberCode") Member memberCode);

    @Query(
            "select c from Cart c where c.memberCode =:memberCode " +
                    "and c.productCode =:productCode")
    Optional<Cart> findByProduct(@Param("productCode") Product productCode,
                                 @Param("memberCode") Member memberCode);

    @Query("select sum(p.productPrice*c.cartCount) from Cart c,Product p where c.memberCode =:memberCode " +
            "and c.productCode.productCode = p.productCode and c.ordersState = 0")
    Optional<Cart> findByMemberCodeAll(@Param("memberCode") Member memberCode);

    @Query(
            "select c from Cart c where c.memberCode =:memberCode and c.checkState =0")
    List<Cart> findByCart(@Param("memberCode") Member memberCode);

    @Query("select sum(p.productPrice*c.cartCount) from Cart c,Product p where c.memberCode =:memberCode " +
            "and c.productCode.productCode = p.productCode and c.checkState =0")//and c.payState=0
    Optional<Cart> findByCartPrice(@Param("memberCode") Member memberCode);

    @Modifying
    @Transactional
    @Query("update  Cart c set c.checkState = null")
    Integer  updateCheckState();

    @Modifying
    @Transactional
    @Query("update  Cart c set c.ordersState = 1 where c.memberCode.memberCode =:memberCode and c.productCode.productCode =:productCode")
    Integer  findByCartOrders(@Param("memberCode") Long memberCode,@Param("productCode") Long productCode);

}
