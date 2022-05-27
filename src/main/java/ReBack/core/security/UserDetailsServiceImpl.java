package ReBack.core.security;

import ReBack.core.data.Member;
import ReBack.core.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByMemberId(memberId);
        if (!findMember.isPresent()) throw new UsernameNotFoundException("존재하지 않는 username 입니다.");

        log.info("loadUserByUsername member.memberId = {}", memberId);

        return new SecurityUser(findMember.get());
    }
}
