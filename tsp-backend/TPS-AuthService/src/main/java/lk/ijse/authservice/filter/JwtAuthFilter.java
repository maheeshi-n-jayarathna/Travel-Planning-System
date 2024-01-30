package lk.ijse.authservice.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.authservice.service.JwtService;
import lk.ijse.authservice.service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    // auth service ekt en hema req ekkma aran methnadi chck krnw
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        // req have Authorization header
        // e header eka naththn no need validation
        // mokda user save karana endpoint ek ehema public nisa
        //
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            // token ek gannawa Bearer string ain krl
            String token = authHeader.substring(7);

            // eken user name ek eliyt gnnwa
            String username = jwtService.extractUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // e username ek db eke tynwd blnwa nthithn automa menken exeption ekk trow wnw
                // api eka catch krn ne eka gatway ekedi catch wenn hdl tynw nisa ek ethnt ynawa
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

//                den db ekt username gththa usergen aya token ekk hdl ara req eke token ektth ekk sambandyk hdnn plwnd blnw
                // puluwnnm vithrai valid token ekk wenne
                // mechchra security ekk on ne hbai hoda hacker kenekgen berennna me wge loku wed karnawa
                // ijse eke kuruth ne mek karapu (:

                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        // meken continue krgn ynw awa req ek
        filterChain.doFilter(request, response);
    }
}
