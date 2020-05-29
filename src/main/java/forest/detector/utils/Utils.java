//package forest.detector.utils;
//
//import org.apache.catalina.realm.GenericPrincipal;
//import org.jetbrains.annotations.NotNull;
//import org.slf4j.Logger;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//
//public class Utils {
//
//    public static void requestInitialized(HttpServletRequest request, Logger log) {
//        log.debug("Request Path: {}", request.getRequestURI());
////        log.debug("Auth header: {}", request.getHeader("Authorization"));
////        // isUserInRole determines whether a remote user is in a specific security role. If no user has been authenticated,
////        // this method returns false. This method expects a String user role-name parameter.
////        boolean isAdmin = request.isUserInRole("admin");
////        log.debug("User has role admin: {}", isAdmin);
////        // getUserPrincipal determines the principal name of the current user and returns a java.security.Principal object.
////        // If no user has been authenticated, this method returns null. Calling the getName method on the Principal
////        // returned by getUserPrincipal returns the name of the remote user.
////        Principal userPrincipal = request.getUserPrincipal();
////        log.debug("Principal: {}", userPrincipal);
////        // a boolean indicating whether this request was made using a secure channel, such as HTTPS.
////        boolean isSecure = request.isSecure();
////        log.debug("Is Secure (HTTPS): {}", isSecure);
////        // getRemoteUser determines the user name with which the client authenticated. The getRemoteUser method returns
////        // the name of the remote user (the caller) associated by the container with the request.
////        // If no user has been authenticated, this method returns null.
////        String remoteUser = request.getRemoteUser();
////        log.debug("Remote user: {}", remoteUser);
////        String authType = request.getAuthType();
////        log.debug("AuthType: {}", authType);
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            List<String> ll = Collections.list(session.getAttributeNames());
//            log.debug("Session attributes: "+ll);
//            if (ll !=null && !ll.isEmpty()){
//                String[] roles = ((GenericPrincipal) session.getAttribute("principal")).getRoles();
//                log.debug("User has roles: {}", Arrays.asList(roles));
//            }
//        }
//    }
//
//    public static boolean hasRoles(HttpServletRequest request, @NotNull List<String> roles){
//        GenericPrincipal genericPrincipal = Objects.requireNonNullElse(
//                ((GenericPrincipal) request.getSession().getAttribute("principal")),
//                new GenericPrincipal("anonymous", "anonymous", List.of())
//        );
//        return roles.stream().anyMatch(element -> Arrays.asList(genericPrincipal.getRoles()).contains(element));
//    }
//}
