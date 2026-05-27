package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.*;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberFoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    @Transactional
    public MemberResDTO.SignupResultDTO signup(MemberReqDTO.SignupDTO request) {
        if (memberRepository.existsByEmailAndDeletedAtIsNull(request.getEmail())) {
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        Member member = memberRepository.save(MemberConverter.toMember(request));
        saveMemberFoods(member, request.getFoodIds());

        return MemberConverter.toSignupResultDTO(member);
    }

    // 내 포인트 조회
//    @Transactional(readOnly = true)
//    public MemberResDTO.PointDTO getMyPoint(AuthMember memberId) {
//        Member member = getActiveMember(memberId);
//
//        return MemberConverter.toPointDTO(member);
//    }
    @Transactional(readOnly = true)
    public MemberResDTO.PointDTO getMyPoint(AuthMember authMember) {

        Member member = memberRepository.findById(authMember.getMemberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toPointDTO(member);
    }

    // 내 정보 수정
    @Transactional
    public MemberResDTO.UpdateResultDTO updateMyInfo(AuthMember authMember, MemberReqDTO.UpdateDTO request) {
        Member member = getActiveMember(authMember.getMemberId());

        member.updateInfo(
                request.getName(),
                request.getAddress(),
                request.getPhoneNum(),
                request.getProfileUrl()
        );

        if (request.getFoodIds() != null) {
            memberFoodRepository.deleteAllByMember(member);
            saveMemberFoods(member, request.getFoodIds());
        }

        return MemberConverter.toUpdateResultDTO(member);
    }

    @Transactional
    public MemberResDTO.DeleteResultDTO deleteMember(MemberReqDTO.DeleteDTO request) {
        Member member = getActiveMember(request.getMemberId());

        if (!member.isPasswordMatched(request.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        member.delete();

        return MemberConverter.toDeleteResultDTO(member);
    }

    public MemberResDTO.LogoutResultDTO logout() {
        return MemberConverter.toLogoutResultDTO();
    }

    private Member getActiveMember(Long memberId) {
        return memberRepository.findByIdAndDeletedAtIsNull(memberId)
                .orElseThrow(() ->
                        new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
                );
    }

    private void saveMemberFoods(Member member, List<Long> foodIds) {
        if (foodIds == null || foodIds.isEmpty()) {
            return;
        }

        Set<Long> distinctFoodIds = new LinkedHashSet<>(foodIds);
        List<Food> foods = foodRepository.findAllById(distinctFoodIds);

        if (foods.size() != distinctFoodIds.size()) {
            throw new MemberException(MemberErrorCode.INVALID_FOOD_PREFERENCE);
        }

        List<MemberFood> memberFoods = foods.stream()
                .map(food -> new MemberFood(member, food))
                .toList();

        memberFoodRepository.saveAll(memberFoods);
    }


    // 회원가입
    public MemberSignupResponse signup(MemberSignupRequest request) {

        // 이메일 중복 검사
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member member = new Member(
                request.getName(),
                request.getGender(),
                request.getBirth(),
                request.getAddress(),
                0, // 초기 포인트
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNum(),
                request.getProfileUrl()
        );

        Member savedMember = memberRepository.save(member);

        // 음식 저장
        if (request.getFoodIds() != null && !request.getFoodIds().isEmpty()) {

            List<Food> foods = foodRepository.findAllById(request.getFoodIds());

            // 존재하지 않는 음식 ID 검증
            if (foods.size() != request.getFoodIds().size()) {
                throw new MemberException(MemberErrorCode.INVALID_FOOD_PREFERENCE);
            }

            List<MemberFood> memberFoods = foods.stream()
                    .map(food -> new MemberFood(savedMember, food))
                    .toList();

            memberFoodRepository.saveAll(memberFoods);
        }

        return new MemberSignupResponse(
                savedMember.getId(),
                savedMember.getName(),
                savedMember.getEmail()
        );
    }

}
