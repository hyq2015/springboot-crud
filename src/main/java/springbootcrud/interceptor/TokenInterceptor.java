package springbootcrud.interceptor;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import springbootcrud.common.JwtTokenUtil;
import springbootcrud.config.JwtConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 地址过滤
        String uri = request.getRequestURI() ;
        if (uri.contains("/login") || uri.contains("/register")){
            return true ;
        }
        // Token 验证

        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(JwtTokenUtil.TOKEN_HEADER);
        }
        if(StringUtils.isEmpty(token) || !token.startsWith(JwtTokenUtil.TOKEN_PREFIX)){
            throw new Exception(JwtTokenUtil.TOKEN_HEADER+ "不能为空");
        }
        token = token.replace(JwtTokenUtil.TOKEN_PREFIX, "");
        Claims claims = JwtTokenUtil.getTokenBody(token);
        if(claims == null || JwtTokenUtil.isExpiration(token)){
            throw new Exception(JwtTokenUtil.TOKEN_HEADER + "失效，请重新登录");
        }
        //设置 identityId 用户身份ID
        request.setAttribute("identityId", claims.getSubject());
        System.out.println("current user ====== " + claims.getSubject());
        return true;
    }
}
