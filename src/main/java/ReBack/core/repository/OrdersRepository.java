package ReBack.core.repository;

import ReBack.core.data.Member;
import ReBack.core.data.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Query("select o from Orders as o where o.memberCode = :memberCode") //회원이 구매한 구매목록
    List<Orders> find2ByMemberCode(@Param("memberCode")Member memberCode);

    Orders findByOrdersCode(Long ordersCode);

    @Query("select  o  from Orders o, Refund  r , Member  m, Product p " +
            "where o.ordersCode = :ordersCode and " +
            "r.ordersCode.ordersCode = o.ordersCode and  "+
            "o.memberCode.memberCode = m.memberCode and " +
            "o.productCode.productCode = p.productCode")
    Optional<Orders> find2ByOrdersCode(@Param("ordersCode") Long ordersCode);

}