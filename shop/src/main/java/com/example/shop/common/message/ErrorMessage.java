package com.example.shop.common.message;

public class ErrorMessage {

    // Member 관련 에러 메시지
    public static final String MEMBER_NOT_FOUND = "회원을 찾을 수 없습니다.";
    public static final String MEMBET_ALREADY_EXISTS = "이미 존재하는 로그인 아이디입니다.";

    // Member DTO
    public static final String LOGIN_ID_NOT_NULL = "로그인 아이디는 필수입니다.";
    public static final String LOGIN_ID_SIZE = "로그인 아이디는 4자 이상 20자 이하입니다.";

    public static final String PASSWORD_NOT_NULL = "비밀번호는 필수입니다.";
    public static final String PASSWORD_SIZE = "비밀번호는 8자 이상 20자 이하입니다.";

    public static final String PHONE_NUMBER_NOT_NULL = "전화번호는 필수입니다.";
    public static final String PHONE_NUMBER_PATTERN = "전화번호 형식은 010-xxxx-xxxx입니다.";

    public static final String ADDRESS_NOT_NULL = "주소는 필수입니다.";
    public static final String ADDRESS_SIZE = "주소는 1자 이상 255자 이하입니다.";


    // Product 관련 에러 메시지
    public static final String PRODUCT_NOT_FOUND = "상품을 찾을 수 없습니다.";
    public static final String PRODUCT_ALREADY_EXISTS = "이미 존재하는 상품 아이디입니다.";

    // Product DTO
    public static final String NAME_NOT_NULL = "이름은 필수입니다.";
    public static final String NAME_SIZE = "이름은 1자 이상 50자 이하입니다.";

    public static final String PRICE_NOT_NULL = "가격은 필수입니다.";


    // Order 관련 에러 메시지
    public static final String ORDER_NOT_FOUND = "주문을 찾을 수 없습니다.";

    // Order DTO
    public static final String TIME_NOT_NULL = "시간은 필수입니다.";

}
