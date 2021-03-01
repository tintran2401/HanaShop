/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts;

import com.opensymphony.xwork2.ActionContext;
import dao.UserDAO;
import entity.TblUser;
import java.util.Map;
import service.UserService;

/**
 *
 * @author TiTi
 */
public class LoginAction {

    private String username;
    private String password;
    private String message;
    private String email;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public LoginAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;

        Map session = ActionContext.getContext().getSession();
        UserService userService = new UserService(session);
        TblUser user = userService.login(username, password);
        UserDAO userDao = new UserDAO();
        TblUser userMail = userDao.getUserByEmail(email);
        if (userMail != null) {
            user = userService.login(userMail.getUsername(), userMail.getPassword());
            message = "";
            userService.setCurrentUser(user);
            url = SUCCESS;
        }
        if (user == null) {
            message = "Invalid username or password";
        } else {
            message = "";
            userService.setCurrentUser(user);
            url = SUCCESS;
        }
        return url;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
