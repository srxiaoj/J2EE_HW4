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
import org.genericdao.RollbackException;

import databeans.FavoriteBean;
import databeans.UserBean;

public class Model {
    private FavoriteDAO favoriteDAO;
    private UserDAO userDAO;

    public Model(ServletConfig config) throws ServletException, RollbackException {
        try {
            String jdbcDriver = config.getInitParameter("jdbcDriverName");
            String jdbcURL = config.getInitParameter("jdbcURL");

            ConnectionPool cp = new ConnectionPool(jdbcDriver, jdbcURL);

            userDAO = new UserDAO(cp, "haoruiw_user");
            favoriteDAO = new FavoriteDAO(cp, "haoruiw_favorite");
            
            if (userDAO.getCount() == 0) {
                // create the users and favorites
                UserBean user1 = new UserBean();
                user1.setEmail("mu@gmail.com");
                user1.setFirstName("mu");
                user1.setLastName("wu");
                user1.setPassword("muwu");
                userDAO.create(user1);

                FavoriteBean fav1 = new FavoriteBean();
                fav1.setUrl("http://dealsea.com/");
                fav1.setComment("dealsea");
                fav1.setUserId(user1.getUserId());
                fav1.setClickCount(0);
                favoriteDAO.create(fav1);

                FavoriteBean fav2 = new FavoriteBean();
                fav2.setUrl("http://cn.dealmoon.com/");
                fav2.setComment("dealmoon");
                fav2.setUserId(user1.getUserId());
                fav2.setClickCount(0);
                favoriteDAO.create(fav2);

                FavoriteBean fav3 = new FavoriteBean();
                fav3.setUrl("https://www.youtube.com/");
                fav3.setComment("youtube");
                fav3.setUserId(user1.getUserId());
                fav3.setClickCount(0);
                favoriteDAO.create(fav3);

                FavoriteBean fav4 = new FavoriteBean();
                fav4.setUrl("http://www.taobao.com/");
                fav4.setComment("shopping website");
                fav4.setUserId(user1.getUserId());
                fav4.setClickCount(0);
                favoriteDAO.create(fav4);

                UserBean user2 = new UserBean();
                user2.setEmail("csu@gmail.com");
                user2.setFirstName("Glad");
                user2.setLastName("Aody");
                user2.setPassword("ag");
                userDAO.create(user2);

                FavoriteBean fav5 = new FavoriteBean();
                fav5.setUrl("http://www.nba.com");
                fav5.setComment("nba");
                fav5.setUserId(user2.getUserId());
                fav5.setClickCount(0);
                favoriteDAO.create(fav5);

                FavoriteBean fav6 = new FavoriteBean();
                fav6.setUrl("http://www.bankofamerica.com");
                fav6.setComment("boa");
                fav6.setUserId(user2.getUserId());
                fav6.setClickCount(0);
                favoriteDAO.create(fav6);

                FavoriteBean fav7 = new FavoriteBean();
                fav7.setUrl("http://www.cmu.edu");
                fav7.setComment("cmu website");
                fav7.setUserId(user2.getUserId());
                fav7.setClickCount(0);
                favoriteDAO.create(fav7);

                FavoriteBean fav8 = new FavoriteBean();
                fav8.setUrl("http://www.jeffeppinger.com");
                fav8.setComment("jeff");
                fav8.setUserId(user2.getUserId());
                fav8.setClickCount(0);
                favoriteDAO.create(fav8);

                UserBean user3 = new UserBean();
                user3.setEmail("obo@andrew.cmu.edu");
                user3.setFirstName("Uncle");
                user3.setLastName("Sam");
                user3.setPassword("666");
                userDAO.create(user3);

                FavoriteBean fav9 = new FavoriteBean();
                fav9.setUrl("www.amazon.com");
                fav9.setComment("amazon");
                fav9.setUserId(user3.getUserId());
                fav9.setClickCount(0);
                favoriteDAO.create(fav9);

                FavoriteBean fav10 = new FavoriteBean();
                fav10.setUrl("www.cnn.com");
                fav10.setComment("cnn");
                fav10.setUserId(user3.getUserId());
                fav10.setClickCount(0);
                favoriteDAO.create(fav10);

                FavoriteBean fav11 = new FavoriteBean();
                fav11.setUrl("http://www.newegg.com/");
                fav11.setComment("newegg");
                fav11.setUserId(user3.getUserId());
                fav11.setClickCount(0);
                favoriteDAO.create(fav11);

                FavoriteBean fav12 = new FavoriteBean();
                fav12.setUrl("https://www.facebook.com");
                fav12.setComment("facebook");
                fav12.setUserId(user3.getUserId());
                fav12.setClickCount(0);
                favoriteDAO.create(fav12);
            }
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
