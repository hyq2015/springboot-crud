package springbootcrud.interceptor;

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

        return true;
    }
}
