/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import dto.UserDTO;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hoang
 */
//class để check xem người dùng đã login hay author hay chưa
public class AuthUtils {
    private static final String ADMIN_ROLE = "AD";
    private static final String USER_ROLE = "US";
    
    public static UserDTO getUser(HttpSession session){
        if(isLoggedIn(session)){
            return null;
        }
        return (UserDTO) session.getAttribute("user");
    }
    public static boolean isLoggedIn(HttpSession session){
        return session.getAttribute("user")!= null;
    }
    public static boolean isAdmin(HttpSession session){
        if(!isLoggedIn(session)){
            return false;
        }
        UserDTO user = getUser(session);
        return user.getRoleID().equals(ADMIN_ROLE);
    }
    
     public static boolean isUser(HttpSession session){
        if(!isLoggedIn(session)){
            return false;
        }
        UserDTO user = getUser(session);
        return user.getRoleID().equals(USER_ROLE);
    }
}
