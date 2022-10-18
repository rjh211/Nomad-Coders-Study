package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    //세션관리 클래스
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>(); //동시에 여러 트랜젝션이 접근할때 유용한 자료구조

    public static final String SESSION_COOKIE_NAME = "mySessionId";

    /*
     * 세션 생성
     * * session Id 생성
     * * 세션 저장소에 sessionId와 Value 저장
     * * sessionId로 응답 쿠키를 생성해서 클라이언트에 전달
     */
    public void createSession(Object value, HttpServletResponse response){
        //세션 Id 생성 및 값을 세션에저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        //쿠키생성
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);

    }

    //세션조회
    public Object getSession(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie==null){
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
    }

    public Cookie findCookie(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        return Arrays.stream(cookies) //배열 -> Stream으로 변경
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()//EXISTS와 같은 로직(순서고려 없이 가장빠르게 찾은 항목 return)
                .orElse(null);
    }

    //세션 만료
    public void expire(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie != null){
            sessionStore.remove(sessionCookie.getValue());
        }
    }
}
