package springbootcrud.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtTokenUtil {

    public static String TOKEN_HEADER;
    public static String TOKEN_PREFIX;
    private static String SECRET;
    private static String ISS;
    private static long EXPIRATION;
    private static long EXPIRATION_REMEMBER;


    // 过期时间是3600秒，既是1个小时
//    private static final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天

    @Value("${config.jwt.header}")
    public void setTokenHeader(String tokenHeader) {
        TOKEN_HEADER = tokenHeader;
    }
    @Value("${config.jwt.prefix}")
    public void setTokenPrefix(String tokenPrefix) {
        TOKEN_PREFIX = tokenPrefix;
    }
    @Value("${config.jwt.secret}")
    public void setSECRET(String SECRET) {
        JwtTokenUtil.SECRET = SECRET;
    }

    @Value("${config.jwt.iss}")
    public void setISS(String ISS) {
        JwtTokenUtil.ISS = ISS;
    }
    @Value("${config.jwt.expiration}")
    public void setEXPIRATION(long EXPIRATION) {
        JwtTokenUtil.EXPIRATION = EXPIRATION;
    }
    @Value("${config.jwt.expiration_remember}")
    public void setExpirationRemember(long expirationRemember) {
        EXPIRATION_REMEMBER = expirationRemember;
    }

    // 创建token
    public static String createToken(String info, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(info)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    // 是否已过期
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    public static Claims getTokenBody(String token) throws ExpiredJwtException{
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
