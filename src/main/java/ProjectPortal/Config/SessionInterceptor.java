package ProjectPortal.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class SessionInterceptor implements HandlerInterceptor {

    /**
     * Pre-hhandle method to check session validity before request processing
     * @param request The HTTP request
     * @param response The HTTP response
     * @param handler The handler for the request
     * @return boolean proceed with request handling or not
     * @throws Exception If redirection fails
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        // Skip session check for login resources
        String path = request.getRequestURI();
        if (path.equals("/login") || path.equals("/css/main.css") ||
                path.equals("/css/project-overview.css") || path.equals("/css/formbox.css")) {
            return true;
        }

        // Redirect to login if no valid session. Spring default max session time is 30m
        if (session.getAttribute("userId") == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}