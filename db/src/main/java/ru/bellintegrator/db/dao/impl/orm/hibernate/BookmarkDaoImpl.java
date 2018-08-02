package ru.bellintegrator.db.dao.impl.orm.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.core.aop.AdviceRequired;
import ru.bellintegrator.core.exception.DAOException;
import ru.bellintegrator.db.dao.BookmarkManager;
import ru.bellintegrator.db.dao.GenericDAO;
import ru.bellintegrator.core.model.Bookmark;

import javax.annotation.Resource;
import java.util.List;

/**
 * Hibernate реализация GenericDAO для модели Bookmark.
 */
@Transactional
@Repository("bookmarkDao")
public class BookmarkDaoImpl implements GenericDAO<Bookmark>, BookmarkManager {
    private static final Logger LOGGER = Logger.getLogger(BookmarkDaoImpl.class);
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    private static String BOOKMARKS_DELETE_QUERY = "DELETE Bookmark WHERE category.id = :categoryId";
    private static String BOOKMARKS_SELECT_QUERY = "FROM Bookmark";
    private static String BOOKMARKS_BY_CATEGORY_ID_SELECT_QUERY = "FROM Bookmark where category.id = :categoryId";

    @Override
    @AdviceRequired
    public Long create(Bookmark bookmark) throws DAOException {
        LOGGER.debug("Call create method: bookmark = " + bookmark);
        Session session;
        Long bookmarkId;

        try {
            session = sessionFactory.getCurrentSession();
            bookmarkId = (Long) session.save(bookmark);
        } catch (HibernateException e) {
            LOGGER.error("Exception while creating bookmark: ", e);
            throw new DAOException("Exception while creating bookmark: ", e);
        }
        return bookmarkId;
    }

    @Override
    public void delete(Long id) throws DAOException {
        LOGGER.debug("Call delete method: id = " + id);

        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(session.get(Bookmark.class, id));
        } catch (HibernateException e) {
            LOGGER.error("Exception while removing bookmark: ", e);
            throw new DAOException("Exception while removing bookmark: ", e);
        }
    }


    public int deleteAll(Long categoryId) throws DAOException {
        LOGGER.debug("deleteAll(categoryId = " + categoryId + ")");
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(BOOKMARKS_DELETE_QUERY)
                    .setLong("categoryId", categoryId)
                    .executeUpdate();
        } catch (HibernateException e) {
            LOGGER.error("Exception while removing bookmarks: ", e);
            throw new DAOException("Exception while removing bookmarks: ", e);
        }
    }

    @Override
    public void update(Bookmark bookmark) throws DAOException {
        LOGGER.debug("Call update method: bookmark = " + bookmark);
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
            Bookmark persistBookmark = (Bookmark) session.get(Bookmark.class, bookmark.getId());
            persistBookmark.setCreateDate(bookmark.getCreateDate());
            persistBookmark.setDescription(bookmark.getDescription());
            persistBookmark.setName(bookmark.getName());
            persistBookmark.setCategory(bookmark.getCategory());
            persistBookmark.setUrl(bookmark.getUrl());

            session.update(persistBookmark);
        } catch (HibernateException e) {
            LOGGER.error("Exception while updating bookmark: ", e);
            throw new DAOException("Exception while updating bookmark: ", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bookmark> getAll() throws DAOException {
        LOGGER.debug("Call getAll method");
        List<Bookmark> bookmarks;
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            bookmarks = session.createQuery(BOOKMARKS_SELECT_QUERY).list();
        } catch (HibernateException e) {
            LOGGER.error("Exception while receiving bookmark list: ", e);
            throw new DAOException("Exception while receiving bookmark list: ", e);
        }
        return bookmarks;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bookmark> getBookmarkListByCategoryId(Long categoryId) throws DAOException {
        LOGGER.debug("Call getBookmarkListByCategoryId(categoryId=" + categoryId + ")");
        Session session;
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery(BOOKMARKS_BY_CATEGORY_ID_SELECT_QUERY)
                    .setParameter("categoryId", categoryId)
                    .list();
        } catch (HibernateException e) {
            LOGGER.error("Exception while receiving bookmark list: ", e);
            throw new DAOException("Exception while receiving bookmark list: ", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Bookmark getById(Long id) throws DAOException {
        LOGGER.debug("Call getById method: id = " + id);
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            IdentifierLoadAccess categoryIdentifierLoadAccess = session.byId(Bookmark.class);
            return (Bookmark) categoryIdentifierLoadAccess.load(id);

        } catch (HibernateException e) {
            LOGGER.error("Exception while receiving bookmark by id: ", e);
            throw new DAOException("Exception while receiving bookmark by id: ", e);
        }
    }
}
