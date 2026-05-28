package com.example.umc10th.global.security.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.dto.KakaoDTO;
import com.example.umc10th.global.security.dto.OAuthDTO;
import com.example.umc10th.global.security.entity.OAuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Service
@RequiredArgsConstructor
public class CustomOAuthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        SocialType providerId;
        String socialUid;

        try {
            providerId = SocialType.valueOf(
                    userRequest.getClientRegistration()
                            .getRegistrationId()
                            .toUpperCase()
            );
            Object socialIdObj = oAuth2User.getAttributes().get("id");
            socialUid = socialIdObj.toString();
        } catch (IllegalArgumentException e) {
            throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        Map<String, Object> kakaoAccount =
                (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");

        Map<String, Object> profile =
                (Map<String, Object>) kakaoAccount.get("profile");

        OAuthDTO dto;

        switch (providerId) {
            case KAKAO -> {
                String email = String.valueOf(kakaoAccount.get("email"));
                String name = String.valueOf(profile.get("nickname"));
                dto = new KakaoDTO(socialUid, email, name);
            }
            default -> throw new MemberException(MemberErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        Member member = memberRepository.findBySocialTypeAndSocialUid(providerId, socialUid)
                .orElseGet(() -> {
                    //Member newMember = MemberConverter.toMember((MemberReqDTO.SignupDTO) dto);
                    Member newMember = MemberConverter.toMember(dto);
                    return memberRepository.save(newMember);
                });

        return new OAuthMember(member, oAuth2User.getAttributes());
    }
}
