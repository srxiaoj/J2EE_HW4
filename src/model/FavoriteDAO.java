package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databeans.FavoriteBean;

public class FavoriteDAO extends GenericDAO<FavoriteBean> {
    public FavoriteDAO(ConnectionPool cp, String tableName) throws DAOException {
        super(FavoriteBean.class, tableName, cp);
    }

    public void create(FavoriteBean bean) throws RollbackException {

        try {
            Transaction.begin();

            // Create a new ItemBean in the database with the next id number
            createAutoIncrement(bean);
            Transaction.commit();
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }

    public void delete(int favoriteId, int userId) throws RollbackException {
        try {
            Transaction.begin();
            FavoriteBean p = read(favoriteId);

            if (p == null) {
                throw new RollbackException("FavoriteId does not exist: id=" + favoriteId);
            }

            if (userId != (p.getUserId())) {
                throw new RollbackException("Favorite not owned by user with userId: " + userId);
            }

            delete(favoriteId);
            Transaction.commit();
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }
    
    public FavoriteBean[] getUserFavorites(int userId) throws RollbackException {
        try {
            Transaction.begin();
            FavoriteBean[] a = match(MatchArg.equals("userId", userId));
            Transaction.commit();
            return a;
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }

    public void incrementClick(int favoriteId) throws RollbackException {
        FavoriteBean favoriteBean = read(favoriteId);
        try {
            Transaction.begin();
            favoriteBean.setClickCount(favoriteBean.getClickCount() + 1);
            update(favoriteBean);
            Transaction.commit();
        } finally {
            if (Transaction.isActive())
                Transaction.rollback();
        }
    }
}
