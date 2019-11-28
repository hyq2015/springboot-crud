package springbootcrud.interceptor;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import springbootcrud.common.HttpStatus;
import springbootcrud.common.JwtTokenUtil;
import springbootcrud.common.TokenException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws TokenException {
        // 地址过滤
//        String uri = request.getRequestURI();
//        System.out.println(uri);
//        if (uri.contains("/login") || uri.contains("/register")){
//            return true ;
//        }
        // Token 验证

        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(JwtTokenUtil.TOKEN_HEADER);
        }
        if(StringUtils.isEmpty(token) || !token.startsWith(JwtTokenUtil.TOKEN_PREFIX)){
            throw new TokenException(HttpStatus.NOT_FOUND.getCode(), HttpStatus.NOT_FOUND.getMessage());
        }
        token = token.replace(JwtTokenUtil.TOKEN_PREFIX, "");

        try{
            boolean flag = JwtTokenUtil.isExpiration(token);
            if (!flag) {
                throw new TokenException(HttpStatus.UNAUTHORIZED.getCode(), HttpStatus.UNAUTHORIZED.getMessage());
            }
        } catch (ExpiredJwtException e) {
            throw new TokenException(HttpStatus.UNAUTHORIZED.getCode(), HttpStatus.UNAUTHORIZED.getMessage());

        }

        //设置 identityId 用户身份ID
//        request.setAttribute("identityId", claims.getSubject());
//        System.out.println("current user ====== " + claims.getSubject());
        return true;
    }
}
