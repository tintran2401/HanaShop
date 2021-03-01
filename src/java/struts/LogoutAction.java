/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struts;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import service.UserService;

/**
 *
 * @author TiTi
 */
public class LogoutAction {

    private final String SUCCESS = "success";

    public LogoutAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        
        Map session = ActionContext.getContext().getSession();
        UserService userService = new UserService(session);
        userService.logout();
        
        return url;
    }

}
