package com.gongkademy.domain.member.service;

import com.gongkademy.domain.member.dto.PrincipalDetails;
import com.gongkademy.domain.member.entity.Member;
import com.gongkademy.domain.member.entity.MemberRole;
import com.gongkademy.domain.member.dto.MemberInfoDTO;
import com.gongkademy.domain.member.dto.MemberSignUpDTO;
import com.gongkademy.domain.member.dto.MemberUpdateDTO;
import com.gongkademy.domain.member.repository.MemberRepository;
import com.gongkademy.global.exception.CustomException;
import com.gongkademy.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class OAuth2MemberService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    /**
     * OAuth2UserRequest를 사용하여 사용자를 로드합니다.
     * @param userRequest OAuth2UserRequest
     * @return OAuth2User 객체
     * @throws OAuth2AuthenticationException 인증 예외
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 이메일을 통해 사용자 식별
        String email = oAuth2User.getAttribute("email");

        // 데이터베이스에서 멤버 조회
        Optional<Member> existingMember = memberRepository.findByEmail(email);

        Member member;
        // 멤버가 데이터베이스에 존재하지 않으면 새로운 멤버 생성
        if (existingMember.isEmpty()) {
            member = Member.builder()
                    .email(email)
//                    .password("") // OAuth2 로그인 사용자는 비밀번호가 필요없음
                    .nickname("") // 닉네임은 추후 업데이트 필요
                    .birthday(null) // 생일 정보도 추후 업데이트 필요
                    .university("")
                    .major("")
                    .minor("")
                    .build();
            member.addRole(MemberRole.USER);
            memberRepository.save(member);
        } else {
            member = existingMember.get();
        }

        // 기존 사용자 정보를 사용하여 OAuth2User 객체 반환
        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }


}
