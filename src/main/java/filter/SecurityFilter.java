package filter;

import entity.Role;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SecurityFilter implements Filter {
    private static final String ADMIN_URL = "/WEB-INF/view/admin/";
    private static final String SPECIALIST_URL = "/WEB-INF/view/specialist/";
    private static final String CLIENT_URL = "/WEB-INF/view/client/";
    private static final List<String> PUBLIC_PAGES = Arrays.asList("/", "/login", "/logout", "/register", "/access_denied");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String path = request.getRequestURI();
        final Role role = (Role) request.getSession().getAttribute("role");

        if (!isUrlPublic(path) && role!= null && !isPermittedRequest(role, path)) {
            response.sendRedirect("/WEB-INF/view/access_denied.jsp");
            return;
        }
        filterChain.doFilter(request, servletResponse);
    }

    private boolean isUrlPublic(String path) {
        String s = path.replace(".jsp", "");
        return PUBLIC_PAGES.contains(s);
    }

    private static boolean isPermittedRequest(Role role, String requestURI) {
        return isClientRequestPermitted(role, requestURI) ||
                isSpecialistRequestPermitted(role, requestURI) ||
                isAdminRequestPermitted(role, requestURI);
    }

    private static boolean isAdminRequestPermitted(Role role, String requestURI) {
        return role.name().equals("ADMIN") && requestURI.startsWith(ADMIN_URL);
    }

    private static boolean isSpecialistRequestPermitted(Role role, String requestURI) {
        return role.name().equals("SPECIALIST") && requestURI.startsWith(SPECIALIST_URL);
    }

    private static boolean isClientRequestPermitted(Role role, String requestURI) {
        return role.name().equals("CLIENT") && requestURI.startsWith(CLIENT_URL);
    }

}
