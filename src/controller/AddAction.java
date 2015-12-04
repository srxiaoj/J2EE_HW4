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

public class AddAction extends Action {
    private FormBeanFactory<FavoriteForm> favoriteFormFactory = FormBeanFactory
            .getInstance(FavoriteForm.class);

    private FavoriteDAO favoriteDAO;

    public AddAction(Model model) {
        favoriteDAO = model.getFavoriteDAO();
    }

    public String getName() {
        return "add.do";
    }

    public String perform(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        String action = request.getParameter("action");
        
        request.setAttribute("errors", errors);

        try {
            // Fetch the items now, so that in case there is no form or there
            // are errors
            // We can just dispatch to the JSP to show the item list (and any
            // errors)
            request.setAttribute("favorites", favoriteDAO.getUserFavorites(user.getUserId()));

            FavoriteForm form = favoriteFormFactory.create(request);
            request.setAttribute("form", form);
            
            String favoriteIdStr = request.getParameter("favoriteId");
            if (favoriteIdStr != null) {
                int favoriteId = Integer.parseInt(favoriteIdStr);
                System.out.println("favoriteId is: " + favoriteId);
                favoriteDAO.incrementClick(favoriteId);
//                return "favorite.jsp"; // if return jsp page, the page will not refresh
//                                       // and the favoriteId is shown on URL
                return "add.do";
            }
            
            if (!form.isPresent()) {
                return "favorite.jsp";
            }
            if (action.equals("Log out")) {
                session.setAttribute("user", null); //remove session also
                return "login.do";
            }
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                return "favorite.jsp";
            }

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
            request.setAttribute("favorites", favoriteDAO.getUserFavorites(user.getUserId()));
            return "favorite.jsp";

        } catch (RollbackException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        } catch (FormBeanException e) {
            errors.add(e.getMessage());
            return "error.jsp";
        }
    }
}
