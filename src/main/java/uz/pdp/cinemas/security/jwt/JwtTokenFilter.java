package uz.pdp.cinemas.security.jwt;

import io.jsonwebtoken.Claims;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.cinemas.entity.User;

import java.io.IOException;
import java.util.List;

@NonNullApi
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;
    private final Cache cache;
    @Value("${admin.email}")
    private String adminEmail;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, CacheManager cacheManager) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.cache = cacheManager.getCache("users");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        if (token != null && token.startsWith(BEARER)) {
            token = token.split(" ")[1];
            if (jwtTokenProvider.isValid(token)) {
                Claims claims = jwtTokenProvider.parseAllClaims(token);
                if (cache != null) {
                    User user = cache.get(claims.getSubject(), User.class);
                    if (user != null) {
                        SimpleGrantedAuthority grantedAuthority;
                        if (user.getEmail().equals(adminEmail))
                            grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
                        else
                            grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                                user.getEmail(),
                                null,
                                List.of(grantedAuthority)
                        ));
                    }
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
