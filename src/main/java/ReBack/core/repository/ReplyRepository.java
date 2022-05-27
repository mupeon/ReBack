package ReBack.core.repository;

import ReBack.core.data.Design;
import ReBack.core.data.Member;
import ReBack.core.data.Reply;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Reply> findByDesign(Design design);

    @Modifying
    @Query("delete from Reply dr where dr.member = :member")
    void deleteByMember(@Param("member") Member member);
}
