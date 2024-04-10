package com.attendance.service.member;

import com.attendance.domain.member.Member;
import com.attendance.domain.member.MemberRole;
import com.attendance.domain.team.Team;
import com.attendance.dto.member.request.MemberSaveRequest;
import com.attendance.dto.member.response.MemberListResponse;
import com.attendance.repository.MemberRepository;
import com.attendance.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void save(MemberSaveRequest request) {
        Team team = teamRepository.findByName(request.getTeamName()).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 팀입니다.");
        });

        Member member = Member.builder()
                .name(request.getName())
                .team(team)
                .role(request.getRole())
                .birthday(request.getBirthday())
                .workStartDate(request.getWorkStartDate())
                .build();

        memberRepository.save(member);

        team.addTeamMember(member);

        if (member.getRole() == MemberRole.MANAGER) {
            team.changeTeamManager(member);
        }
    }

    @Transactional(readOnly = true)
    public List<MemberListResponse> members() {
        List<Member> members = memberRepository.findAll();

        return getMemberListResponses(members);
    }

    @Transactional(readOnly = true)
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 직원입니다.");
        });
    }


    private List<MemberListResponse> getMemberListResponses(List<Member> members) {
        List<MemberListResponse> memberListResponses = new ArrayList<>();

        for (Member member : members) {
            memberListResponses.add(new MemberListResponse(member));
        }
        return memberListResponses;
    }
}
