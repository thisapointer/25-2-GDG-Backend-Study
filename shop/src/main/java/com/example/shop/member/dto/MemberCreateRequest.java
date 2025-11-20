package com.example.shop.member.dto;

// loginId, password, phoneNumber, address

import com.example.shop.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberCreateRequest {

    @NotNull(message = ErrorMessage.LOGIN_ID_NOT_NULL)
    @Size(min = 4, max = 20, message = ErrorMessage.LOGIN_ID_SIZE)
    private String loginId;

    @NotNull(message = ErrorMessage.PASSWORD_NOT_NULL)
    @Size(min = 8, max = 20, message = ErrorMessage.PASSWORD_SIZE)
    private String password;

    @NotNull(message = ErrorMessage.PHONE_NUMBER_NOT_NULL)
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = ErrorMessage.PHONE_NUMBER_PATTERN)
    private String phoneNumber;

    @NotNull(message = ErrorMessage.ADDRESS_NOT_NULL)
    @Size(min = 1, max = 255, message = ErrorMessage.ADDRESS_SIZE)
    private String address;

    public MemberCreateRequest(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
