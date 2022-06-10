package ReBack.core.repository;

import ReBack.core.data.Member;
import ReBack.core.data.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {
    @Query("select r from Orders as o , Refund as r , Product as p " +
            "where p.memberCode = :memberCode and " +
            "o.productCode.productCode = p.productCode and " +
            "o.ordersCode = r.ordersCode.ordersCode") //관리자
    List<Refund> findByMemberCode(@Param("memberCode") Member memberCode);

    @Query("select r from Orders as o , Refund as r , Product as p " +
            "where o.memberCode = :memberCode and " +
            "o.productCode.productCode = p.productCode and " +
            "o.ordersCode = r.ordersCode.ordersCode") //회원
    List<Refund> find2ByMemberCode(@Param("memberCode") Member memberCode);

    @Query("select r from Orders as o ,Refund as r " +
            "where o.memberCode = :memberCode and " +
            "r.ordersCode.ordersCode = o.ordersCode")
    List<Refund> find3ByMemberCode(@Param("memberCode")Member memberCode);


}