package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.*;
import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberFood;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.repository.FoodRepository;
import com.example.umc10th.domain.member.repository.MemberFoodRepository;
import com.example.umc10th.domain.member.repository.MemberRepository;
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
            throw new MemberException("이미 가입된 이메일입니다.");
        }

        Member member = memberRepository.save(MemberConverter.toMember(request));
        saveMemberFoods(member, request.getFoodIds());

        return MemberConverter.toSignupResultDTO(member);
    }

    @Transactional(readOnly = true)
    public MemberResDTO.PointDTO getMyPoint(Long memberId) {
        Member member = getActiveMember(memberId);

        return MemberConverter.toPointDTO(member);
    }

    @Transactional
    public MemberResDTO.UpdateResultDTO updateMyInfo(Long memberId, MemberReqDTO.UpdateDTO request) {
        Member member = getActiveMember(memberId);

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
            throw new MemberException("비밀번호가 일치하지 않습니다.");
        }

        member.delete();

        return MemberConverter.toDeleteResultDTO(member);
    }

    public MemberResDTO.LogoutResultDTO logout() {
        return MemberConverter.toLogoutResultDTO();
    }

    private Member getActiveMember(Long memberId) {
        return memberRepository.findByIdAndDeletedAtIsNull(memberId)
                .orElseThrow(() -> new MemberException("해당 사용자를 찾을 수 없습니다."));
    }

    private void saveMemberFoods(Member member, List<Long> foodIds) {
        if (foodIds == null || foodIds.isEmpty()) {
            return;
        }

        Set<Long> distinctFoodIds = new LinkedHashSet<>(foodIds);
        List<Food> foods = foodRepository.findAllById(distinctFoodIds);

        if (foods.size() != distinctFoodIds.size()) {
            throw new MemberException("존재하지 않는 음식 취향이 포함되어 있습니다.");
        }

        List<MemberFood> memberFoods = foods.stream()
                .map(food -> new MemberFood(member, food))
                .toList();

        memberFoodRepository.saveAll(memberFoods);
    }

}
