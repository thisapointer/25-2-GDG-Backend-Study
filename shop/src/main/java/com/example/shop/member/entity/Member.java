package com.example.shop.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_login_id", length = 50)
    private String loginId;

    @Column(name = "member_pw", length = 100)
    private String password;

    @Column(name = "member_phone", length = 20)
    private String phoneNumber;

    @Column(name = "member_address", length = 255)
    private String address;

    @Column(name = "member_point")
    private int point;

    public Member(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = 0;
    }

    public void updateInfo(String password, String phoneNumber, String address) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
