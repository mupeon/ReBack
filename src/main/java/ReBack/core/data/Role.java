package ReBack.core.data;

import lombok.Getter;

@Getter
public enum Role {

    ROLE_COMPANY("업체"), ROLE_AUTHOR("작가"), ROLE_MEMBER("일반사용자"), ROLE_ADMIN("운영자");

    private String description;

    Role(String description) {
        this.description = description;
    }
}
