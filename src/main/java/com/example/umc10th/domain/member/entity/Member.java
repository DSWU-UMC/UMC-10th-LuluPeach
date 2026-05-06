package com.example.umc10th.domain.member.entity;


import com.example.umc10th.domain.member.enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 5)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "phone_num", length = 15)
    private String phoneNum;

    @Column(name = "profile_url", nullable = false, columnDefinition = "TEXT")
    private String profileUrl;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Member(
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            Integer point,
            String email,
            String password,
            String phoneNum,
            String profileUrl
    ) {
        this.name = name;
        this.gender = gender;
        this.birth = birth;
        this.address = address;
        this.point = point;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.profileUrl = profileUrl;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateInfo(String name, String address, String phoneNum, String profileUrl) {
        if (name != null) {
            this.name = name;
        }
        if (address != null) {
            this.address = address;
        }
        if (phoneNum != null) {
            this.phoneNum = phoneNum;
        }
        if (profileUrl != null) {
            this.profileUrl = profileUrl;
        }
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isPasswordMatched(String password) {
        return this.password.equals(password);
    }
}
