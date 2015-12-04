/**
 * HW4.
 * @author Haorui WU
 * @andrewId haoruiw
 */
package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class FavoriteForm extends FormBean {
    private String url;
    private String comment;
    private String action;

    public String getUrl()         {  return url;      }
    public String getAction()      {  return action;   }
    public String getComment()     {  return comment;  }
    
    public void setUrl(String s)     {    url = s;      }
    public void setComment(String s) {   comment = s;   }
    public void setAction(String s)  {   action = s;    }
    
    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();

        if (url == null || url.length() == 0) {
            errors.add("URL is required");
        }
        if (comment == null || comment.length() == 0) {
            errors.add("comment is required");
        }
        if (errors.size() > 0) return errors;

        if (url.matches(".*[<>\"].*")) errors.add("url may not contain angle brackets or quotes");
        if (comment.matches(".*[<>\"].*")) errors.add("comment may not contain angle brackets or quotes");

        if (!action.equals("Add Favorite")) errors.add("Invalid action");
        return errors;
    }
}
