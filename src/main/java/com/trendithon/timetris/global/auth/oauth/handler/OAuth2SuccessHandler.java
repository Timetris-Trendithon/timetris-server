package com.trendithon.timetris.global.auth.oauth.handler;

import com.trendithon.timetris.domain.member.domain.Role;
import com.trendithon.timetris.global.auth.jwt.TokenProvider;
import com.trendithon.timetris.global.auth.oauth.dto.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@RequiredArgsConstructor
@Component
@Slf4j
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            String userName = oAuth2User.getAttribute("name");

            if (oAuth2User.getRole() == Role.GUEST) {
                String accessToken = tokenProvider.createAccessToken(oAuth2User.getEmail());
                response.addHeader(tokenProvider.getAccessHeader(), "Bearer " + accessToken);
                tokenProvider.sendAccessAndRefreshToken(response, accessToken, null);

            } else {
                loginSuccess(response, oAuth2User);
            }

            request.getSession().setAttribute("name", userName);
            response.sendRedirect("/main");

        } catch (Exception e) {
            throw e;
        }

    }

    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        String accessToken = tokenProvider.createAccessToken(oAuth2User.getEmail());
        String refreshToken = tokenProvider.createRefreshToken();

        response.setHeader(tokenProvider.getAccessHeader(), "Bearer " + accessToken);
        response.addHeader(tokenProvider.getRefreshHeader(), "Bearer " + refreshToken);

        tokenProvider.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        tokenProvider.updateRefreshToken(oAuth2User.getEmail(), refreshToken);

    }
}
