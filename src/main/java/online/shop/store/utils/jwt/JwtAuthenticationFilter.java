package online.shop.store.utils.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtUtil jwtUtilities;
    private final AdminDetailService customerUserDetailsService;

    @Override
    protected void doFilterInternal( HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        String token = jwtUtilities.getToken(request) ;

        if (token!=null && jwtUtilities.validateToken(token))
        {
            String email = jwtUtilities.extractUsername(token);

            UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
            if (userDetails != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername() ,null , userDetails.getAuthorities());
                       
                        log.info("authenticated user with email :{}", email);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("authentication:",authentication);
            }
        }
        filterChain.doFilter(request,response);
    }
}
