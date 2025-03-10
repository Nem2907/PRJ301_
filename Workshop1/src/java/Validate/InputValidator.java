package Validate;

import dao.StartUpProjectDAO;
import dto.StartupProjectsDTO;
import dto.UserDTO;
import java.util.regex.Pattern;

public class InputValidator {

    // Regular expressions for validation
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._-]{3,20}$";
    private static final String ROLE_PATTERN = "^(Admin|User|Moderator)$";
    private static final String STATUS_PATTERN = "^(Pending|Approved|Rejected)$";
    StartUpProjectDAO projectDAO = new StartUpProjectDAO();
    
    public static boolean validateUser(UserDTO user) {
        return validateUsername(user.getUsername()) &&
               validateRole(user.getRole());
    }

    public static boolean validateProject(StartupProjectsDTO project) {
        return project.getProject_id() > 0 &&
               !project.getProject_name().trim().isEmpty() &&
               !project.getDescription().trim().isEmpty() &&
               validateStatus(project.getStatus()) &&
               project.getEstimated_launch() != null;
    }

    private static boolean validateUsername(String username) {
        return username != null && Pattern.matches(USERNAME_PATTERN, username);
    }
    
    private static boolean validateRole(String role) {
        return role != null && Pattern.matches(ROLE_PATTERN, role);
    }

    private static boolean validateStatus(String status) {
        return status != null && Pattern.matches(STATUS_PATTERN, status);
    }
    public boolean validateProjectID(int project_id){
        return projectDAO.isProjectIdExists(project_id);
    }
}
