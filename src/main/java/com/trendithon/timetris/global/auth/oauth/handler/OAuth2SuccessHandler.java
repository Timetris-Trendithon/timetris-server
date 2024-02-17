package com.trendithon.timetris.global.auth.oauth.handler;

import com.trendithon.timetris.domain.login.domain.User;
import com.trendithon.timetris.domain.login.repository.UserRepository;
import com.trendithon.timetris.global.auth.jwt.TokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@RequiredArgsConstructor
@Component
@Slf4j
@PropertySource("classpath:application-jwt.yml")
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // 구글 인증시 아래 로직
        String realName = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        log.info("구글 인증 시 이름 추출 [{}] || 이메일 추출 [{}]", realName, email);


        User user = userRepository.findByEmail(email).orElse(null);
        String foundAccount = user.getEmail();
        user.authorizeUser();
        log.info("소셜 로그인 인증한 계정명 [{}]", foundAccount);

        request.getSession().setAttribute("name", realName);


        String accessToken = tokenProvider.createAccessToken(foundAccount);
        response.addHeader(tokenProvider.getAccessHeader(), "Bearer " + accessToken);
        response.sendRedirect("/member/detail");

        tokenProvider.sendAccessAndRefreshToken(response, accessToken, null);
    }
}
