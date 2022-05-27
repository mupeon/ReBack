package ReBack.core.repository;

import ReBack.core.data.Member;
import ReBack.core.data.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
//    Optional<Orders> findByMemberCode(memberCode Long memberCode);

    Object findByMemberCode(Long memberCode);

    List<Orders> findByMemberCode(Member memberCode);


//    Optional<Orders> findByMemberCode(Long MemberCode);

//        Orders findByMemberCode(Long MemberCode);

}