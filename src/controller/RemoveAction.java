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
import formbeans.IdForm;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;

/*
 * Removes a url.  Given an "id" parameter.
 * Checks to see that id is valid number for a url and that
 * the logged user is the owner.
 * 
 * Sets up the "userList" and "favoriteList" request attributes
 * and if successful, forwards back to to "manage.jsp".
 */
public class RemoveAction extends Action {
	private FormBeanFactory<IdForm> formBeanFactory = FormBeanFactory.getInstance(IdForm.class);

	private FavoriteDAO favoriteDAO;
	private UserDAO  userDAO;

    public RemoveAction(Model model) {
    	favoriteDAO = model.getFavoriteDAO();
    	userDAO  = model.getUserDAO();
	}

    public String getName() { return "remove.do"; }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
		try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());

	    	IdForm form = formBeanFactory.create(request);
	    	
	    	UserBean user = (UserBean) request.getSession().getAttribute("user");

			int id = form.getIdAsInt();
    		favoriteDAO.delete(id,user.getUserId());

    		// Be sure to get the photoList after the delete
    		FavoriteBean[] favoriteList = favoriteDAO.getUserFavorites(user.getUserId());
	        request.setAttribute("favoriteList",favoriteList);

	        return "manage.do";
		} catch (RollbackException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
		} catch (FormBeanException e) {
    		errors.add(e.getMessage());
    		return "error.jsp";
    	}
    }
}
