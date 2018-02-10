package ru.bellintegrator.db.dao.impl.orm.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.db.dao.GenericDAO;
import ru.bellintegrator.db.exception.DAOException;
import ru.bellintegrator.db.model.Bookmark;

import javax.annotation.Resource;
import java.util.List;

/**
 * Hibernate реализация GenericDAO для модели Bookmark.
 */
@Transactional
@Repository("bookmarkDao")
public class BookmarkDaoImpl implements GenericDAO<Bookmark> {
    private static final Logger LOGGER = Logger.getLogger(BookmarkDaoImpl.class);
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public int create(Bookmark bookmark) throws DAOException {
        LOGGER.debug("Call create method: bookmark = " + bookmark);
        Session session;
        int bookmarkId;

        try {
            session = sessionFactory.getCurrentSession();
            bookmarkId = (int) session.save(bookmark);

        } catch (HibernateException e) {
            LOGGER.error("Exception while creating bookmark: ", e);
            throw new DAOException("Exception while creating bookmark: ", e);
        }
        return bookmarkId;
    }

    @Override
    public void delete(Bookmark bookmark) throws DAOException {
        LOGGER.debug("Call delete method: bookmark = " + bookmark);
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
            Bookmark persistBookmark = (Bookmark) session.get(Bookmark.class, bookmark.getId());
            session.delete(persistBookmark);

        } catch (HibernateException e) {
            LOGGER.error("Exception while removing bookmark: ", e);
            throw new DAOException("Exception while removing bookmark: ", e);
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
//            persistBookmark.setCategory(bookmark.getCategory());
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
            bookmarks = session.createQuery("FROM Bookmark").list();

        } catch (HibernateException e) {
            LOGGER.error("Exception while receiving bookmark list: ", e);
            throw new DAOException("Exception while receiving bookmark list: ", e);
        }

        return bookmarks;
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
