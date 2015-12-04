/**
 * HW4.
 * @author Haorui WU
 * @andrewId haoruiw
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.FavoriteBean;
import databeans.UserBean;
import formbeans.FavoriteForm;
import model.FavoriteDAO;
import model.Model;
import model.UserDAO;


/*
 * Sets up the request attributes for manage.jsp.
 * This is the way to enter "Manage Your Photos"
 * from someplace else in the site.
 * 
 * Sets the "userList" request attribute in order to display
 * the list of users on the navbar.
 * 
 * Sets the "photoList" request attribute in order to display
 * the list of user's photos for management.
 * 
 * Forwards to manage.jsp.
 */
public class ManageAction extends Action {

	private FavoriteDAO favoriteDAO;
	private UserDAO  userDAO;
	private FormBeanFactory<FavoriteForm> favoriteFormFactory = FormBeanFactory
            .getInstance(FavoriteForm.class);
	
	public ManageAction(Model model) {
    	favoriteDAO = model.getFavoriteDAO();
    	userDAO  = model.getUserDAO();
	}

	public String getName() { return "manage.do"; }

	public String perform(HttpServletRequest request) {
	    List<String> errors = new ArrayList<String>();
        HttpSession session = request.getSession();
        request.setAttribute("errors",errors);
        UserBean user = (UserBean) session.getAttribute("user");
        String action = request.getParameter("action");
        
        try {
            // Fetch the items now, so that in case there is no form or there
            // are errors
            // We can just dispatch to the JSP to show the item list (and any
            // errors)
            request.setAttribute("favoriteList", favoriteDAO.getUserFavorites(user.getUserId()));
            request.setAttribute("userList",userDAO.getUsers());

            FavoriteForm form = favoriteFormFactory.create(request);
            request.setAttribute("form", form);
            
//            String favoriteIdStr = request.getParameter("favoriteId");
//            if (favoriteIdStr != null) {
//                int favoriteId = Integer.parseInt(favoriteIdStr);
//                System.out.println("favoriteId is: " + favoriteId);
//                favoriteDAO.incrementClick(favoriteId);
////                return "favorite.jsp"; // if return jsp page, the page will not refresh
////                                       // and the favoriteId is shown on URL
//                return "manage.do";
//            }
            
          String favoriteIdStr = request.getParameter("favoriteId");
          String link = "";
          if (favoriteIdStr != null) {
              int favoriteId = Integer.parseInt(favoriteIdStr);
              System.out.println("favoriteId is: " + favoriteId);
              favoriteDAO.incrementClick(favoriteId);
              link = favoriteDAO.read(favoriteId).getUrl();
              return link;
          }
            
            if (!form.isPresent()) {
                return "manage.jsp";
            }
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                return "manage.jsp";
            }
            System.out.println("Add new url");
            FavoriteBean bean = new FavoriteBean();
            bean.setUrl(form.getUrl());
            bean.setComment(form.getComment());
            UserBean u = (UserBean) request.getSession().getAttribute("user");
            bean.setUserId(u.getUserId());

            if (action.equals("Add Favorite")) {
                favoriteDAO.create(bean);
            }
            System.out.println("get list");
            // Fetch the items again, since we modified the list
            request.setAttribute("favoriteList", favoriteDAO.getUserFavorites(user.getUserId()));
            return "manage.jsp";

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
