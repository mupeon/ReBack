package ReBack.core.controller.request;


import ReBack.core.data.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {

    private String memberId;

    private String password;

    private String memberName;

    private Role role;
}
