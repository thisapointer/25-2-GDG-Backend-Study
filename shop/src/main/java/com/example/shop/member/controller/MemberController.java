package com.example.shop.member.controller;

import com.example.shop.member.dto.MemberCreateRequest;
import com.example.shop.member.dto.MemberUpdateRequest;
import com.example.shop.member.entity.Member;
import com.example.shop.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name = "회원 관리", description = "회원 CRUD API")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    @Operation(summary = "회원 생성", description = "새로운 회원을 등록합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패 또는 중복된 로그인 아이디)")
    public ResponseEntity<Void> createMember(@RequestBody @Valid MemberCreateRequest request) {
        Long memberId = memberService.createMember(request);
        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }

    @GetMapping
    @Operation(summary = "모든 회원 조회", description = "모든 회원을 조회합니다.")
    public ResponseEntity<List<Member>> getAllMember() {
        // Service 계층에서 회원 목록을 가져온다
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "회원 조회", description = "회원을 조회합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패)")
    public ResponseEntity<Member> getMember(@PathVariable Long memberId) {
        Member member = memberService.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }

    @PatchMapping("/{memberId}")
    @Operation(summary = "회원 업데이트", description = "회원 정보를 수정합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패)")
    public ResponseEntity<Void> updateMember(
            @PathVariable Long memberId,
            @RequestBody @Valid MemberUpdateRequest request) {
        memberService.updateMember(memberId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{memberId}")
    @Operation(summary = "회원 삭제", description = "회원을 삭제합니다.")
    @ApiResponse(responseCode = "400", description = "잘못된 요청 (유효성 검사 실패)")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
