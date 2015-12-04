/**
 * HW4.
 * @author Haorui WU
 * @andrewId haoruiw
 */
package model;

import java.util.Arrays;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.UserBean;

public class UserDAO extends GenericDAO<UserBean> {
    public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(UserBean.class, tableName, cp);
    }
    
    public UserBean read(String email) throws RollbackException {
        UserBean[] beans = match(MatchArg.containsIgnoreCase("email", email));
        if (beans.length == 0) {
            return null;
        }
        else {
            return beans[0];
        }
    }
    
    public UserBean[] getUsers() throws RollbackException {
        UserBean[] users = match();
        Arrays.sort(users); // We want them sorted by last and first names (as per User.compareTo());
        return users;
    }

    public void setPassword(String email, String password) throws RollbackException {
        try {
            Transaction.begin();
            UserBean dbUser = read(email);

            if (dbUser == null) {
                throw new RollbackException("User " + email + " no longer exists");
            }

            dbUser.setPassword(password);

            update(dbUser);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }
}
