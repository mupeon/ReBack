package ReBack.core.repository;

import ReBack.core.data.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemberId(String memberId);
    Optional<Member> findByMemberCode(Long MemberCode);

    Member findByMemberName(String memberName);
}