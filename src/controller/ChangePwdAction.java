/**
 * HW4.
 * @author Haorui WU
 * @andrewId haoruiw
 */
package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Model;
import model.UserDAO;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databeans.UserBean;
import formbeans.ChangePwdForm;

public class ChangePwdAction extends Action {
	private FormBeanFactory<ChangePwdForm> formBeanFactory = FormBeanFactory.getInstance(ChangePwdForm.class);
	
	private UserDAO userDAO;

	public ChangePwdAction(Model model) {
		userDAO = model.getUserDAO();
	}

	public String getName() { return "change-pwd.do"; }
    
    public String perform(HttpServletRequest request) {
    	// Set up error list
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);

        try {
            // Set up user list for nav bar
			request.setAttribute("userList",userDAO.getUsers());
	        
	        // Load the form parameters into a form bean
	        ChangePwdForm form = formBeanFactory.create(request);
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "change-pwd.jsp";
	        }
	
	        // Check for any validation errors
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "change-pwd.jsp";
	        }
	
			UserBean user = (UserBean) request.getSession().getAttribute("user");
	
			// Change the password
        	userDAO.setPassword(user.getEmail(),form.getNewPassword());
	
			request.setAttribute("message","Password changed for "+user.getEmail());
	        return "success.jsp";
        } catch (RollbackException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.toString());
        	return "error.jsp";
        }
    }
}
