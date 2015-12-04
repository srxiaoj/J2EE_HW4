/**
 * HW4.
 * @author Haorui WU
 * @andrewId haoruiw
 */
package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
    private FavoriteDAO favoriteDAO;
    private UserDAO userDAO;

    public Model(ServletConfig config) throws ServletException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL = config.getInitParameter("jdbcURL");

            ConnectionPool cp = new ConnectionPool(jdbcDriver, jdbcURL);

            userDAO = new UserDAO(cp, "haoruiw_user");
            favoriteDAO = new FavoriteDAO(cp, "haoruiw_favorite");
        } catch (DAOException e) {
            throw new ServletException(e);
        }
    }

    public FavoriteDAO getFavoriteDAO() {
        return favoriteDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
