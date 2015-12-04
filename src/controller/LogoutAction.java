/**
 * HW4.
 * @author Haorui WU
 * @andrewId haoruiw
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import model.Model;
import model.UserDAO;


/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {
    private UserDAO userDAO;
	public LogoutAction(Model model) {
	    userDAO = model.getUserDAO();
	}

	public String getName() { return "logout.do"; }

	public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        
        session.setAttribute("user",null);
        try {
            request.setAttribute("userList",userDAO.getUsers());
        } catch (RollbackException e) {
            e.printStackTrace();
        }
		request.setAttribute("message","You are now logged out");
        return "success.jsp";
    }
}
