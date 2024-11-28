package com.sparta.currency_user.dto.user;

import com.sparta.currency_user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotBlank
    @Size(min = 1, max = 10, message = "이름은 1 ~ 10 글자로 생성해야합니다.")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\\.[A-Za-z0-9\\-]+$", message = "올바르지 않은 이메일 형식입니다.")
    private String email;

    public User toEntity() {
        return new User(
                this.name,
                this.email
        );
    }
}
