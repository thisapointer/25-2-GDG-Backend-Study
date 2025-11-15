//package com.example.shop;
//
//import com.example.shop.member.repository.JpaMemberRepository;
//import com.example.shop.member.repository.MemberRepository;
//import com.example.shop.member.service.MemberService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppConfig {
//
//    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
//
//    @Bean
//    public MemberRepository memberRepository() {
//        return new JpaMemberRepository();
//    }
//}
