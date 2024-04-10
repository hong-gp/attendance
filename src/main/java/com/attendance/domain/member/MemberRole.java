package com.attendance.domain.member;

public enum MemberRole {
    MANAGER,
    MEMBER,
    UNKNOWN;

    public static MemberRole findMemberRole(String value) {
        for (MemberRole role : MemberRole.values()) {
            if (role.name().equals(value.toUpperCase())) {
                return role;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 직책입니다.");
    }
}
