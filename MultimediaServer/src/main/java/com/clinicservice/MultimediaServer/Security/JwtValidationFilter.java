package com.clinicservice.MultimediaServer.Security;
import org.springframework.stereotype.Component;

@Component
public class JwtValidationFilter {

    // private final AuthenticationManager authenticationManager;

    // public JwtValidationFilter(AuthenticationManager authenticationManager) {
    //     this.authenticationManager = authenticationManager;
    // }

    // private String jwtSecret = "4261656C64756E67anhsw2321312533211";
    // Key keys = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

    // @SuppressWarnings("deprecation")
    // @Override
    // protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //     String header = request.getHeader(HEADER_AUTHORIZATION);
    //     if (header == null || !header.startsWith(PREFIX_TOKEN)) {
    //         filterChain.doFilter(request, response);
    //         return;
    //     }

    //     String token = header.replace(PREFIX_TOKEN, "");

    //     try {
    //          //Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
    //          Claims claims =  Jwts.parser().setSigningKey(keys).build().parseSignedClaims(token).getPayload();

    //         String username = claims.getSubject();
    //         Object authoritiesClaims = claims.get("authorities");

    //         // Collection<SimpleGrantedAuthority> authorities = Arrays.asList(
    //         //         new ObjectMapper()
    //         //                 .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
    //         //                 .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));

    //         Collection<SimpleGrantedAuthority> authorities;
    //         ObjectMapper objectMapper = new ObjectMapper(); // Opcional: Descomenta la siguiente línea si es necesario
    //         // objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    //         authorities = Arrays.asList(objectMapper.readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));


    //         UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
    //         SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authenticationToken));
    //         filterChain.doFilter(request, response);
    //     } catch (JwtException  e) {
    //         String errorMessage = e.getMessage();
    //         Map<String, String> body = new HashMap<>();
    //         body.put("error", errorMessage);
    //         body.put("message", "El token es inválido");

    //         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    //         //response.getWriter().write("Token inválido");
    //         response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    //     }catch (Exception e) {
    //         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    //         response.getWriter().write("Token inválido");
    //     }
    //}
}
