package ru.bellintegrator.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bellintegrator.core.exception.DAOException;

import javax.annotation.Resource;

public abstract class Cleaner {
    private static final Logger LOGGER = Logger.getLogger(Cleaner.class);
    //other value - delete all subcategories
    protected static final long DELETE_ALL_CATEGORIES = -1;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Truncate the target table.
     *
     * @throws DAOException
     */
    public int truncate(Class cls) throws DAOException {
        LOGGER.debug("call truncate(cls = " + cls.getCanonicalName() + ")");
        Session session = sessionFactory.getCurrentSession();
        int rowsAffected = 0;
        String hql = "delete from " + cls.getSimpleName();
        Query q = session.createQuery(hql);
        try {
            rowsAffected = q.executeUpdate();
        } catch (HibernateException e) {
            throw new DAOException("Unable to truncate the target table " + cls.getSimpleName(), e);
        }
        return rowsAffected;
    }
}
