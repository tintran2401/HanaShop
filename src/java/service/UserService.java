/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import entity.TblUser;
import java.util.Map;

/**
 *
 * @author TiTi
 */
public class UserService {

    private Map session;
    
    public UserService() {
    }

    public UserService(Map session) {
        this.session = session;
    }

    public TblUser login(String username, String password) {
        UserDAO userDAO = new UserDAO();
        TblUser user = userDAO.getUserByUsername(username);

        if (user == null) {
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    public void logout() {
        this.setCurrentUser(null);
    }
    
    public void setCurrentUser(TblUser user) {
        if (session == null) {
            return;
        }
        session.put("USER", user);
    }
    
    public TblUser getCurrentUser() {
        if (session == null) {
            return null;
        }
        return (TblUser) session.get("USER");
    }
}
