package com.attendance.controller.member;

import com.attendance.dto.member.request.MemberSaveRequest;
import com.attendance.dto.member.response.MemberListResponse;
import com.attendance.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members/save")
    public void save(@RequestBody MemberSaveRequest request) {
        memberService.save(request);
    }

    @GetMapping("/members")
    public List<MemberListResponse> members() {
        return memberService.members();
    }
}
