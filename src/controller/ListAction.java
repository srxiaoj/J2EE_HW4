/**
 * HW4.
 * @author Haorui WU
 * @andrewId haoruiw
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.FavoriteBean;
import databeans.UserBean;
import formbeans.UserForm;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

/*
 * Looks up the favorite lists for a given "user".
 * 
 * If successful:
 *   (1) Sets the "userList" request attribute in order to display
 *       the list of users on the navbar.
 *   (2) Sets the "favoritelist" request attribute in order to display
 *       the list of given user's favorite list for selection.
 *   (3) Forwards to list.jsp.
 */
public class ListAction extends Action {
	private FormBeanFactory<UserForm> formBeanFactory = FormBeanFactory.getInstance(UserForm.class);

	private FavoriteDAO favoriteDAO;
	private UserDAO  userDAO;

    public ListAction(Model model) {
    	favoriteDAO = model.getFavoriteDAO();
    	userDAO  = model.getUserDAO();
	}

    public String getName() { return "list.do"; }

    public String perform(HttpServletRequest request) {
        // Set up the request attributes (the errors list and the form bean so
        // we can just return to the jsp with the form if the request isn't correct)
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());

			UserForm form = formBeanFactory.create(request);
//	    	
			String email = form.getEmail();
////			System.out.println("email is: " + email);
//			if (email == null || email.length() == 0) {
//				errors.add("User must be specified");
//				return "error.jsp";
//			}
			String favoriteIdStr = request.getParameter("favoriteId");
	          String link = "";
	          if (favoriteIdStr != null) {
	              int favoriteId = Integer.parseInt(favoriteIdStr);
	              System.out.println("favoriteId is: " + favoriteId);
	              favoriteDAO.incrementClick(favoriteId);
	              link = favoriteDAO.read(favoriteId).getUrl();
	              return link;
	          }
	          
	        // Set up user list
        	UserBean user = userDAO.read(email);
        	if (user == null) {
    			errors.add("Invalid User: " + email);
    			return "error.jsp";
    		}
        	FavoriteBean[] favoriteList = favoriteDAO.getUserFavorites(user.getUserId());
	        request.setAttribute("favoriteList",favoriteList);
	        return "list.jsp";
        } catch (RollbackException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "error.jsp";
        }
    }
}
