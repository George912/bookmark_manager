package ru.bellintegrator.bookmark_manager.dao.impl.orm.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.bookmark_manager.dao.GenericDAO;
import ru.bellintegrator.bookmark_manager.exception.DAOException;
import ru.bellintegrator.bookmark_manager.model.Bookmark;

import java.util.List;

/**
 * Hibernate реализация GenericDAO для модели Bookmark.
 */
@Repository("bookmarkDao")
public class BookmarkDao extends AbstractConnectable implements GenericDAO<Bookmark> {
    private static final Logger LOGGER = Logger.getLogger(BookmarkDao.class);

    @Override
    public int create(Bookmark bookmark) throws DAOException {
        LOGGER.debug("Call create method: bookmark = " + bookmark);
        Transaction transaction = null;
        int bookmarkId;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            bookmarkId = (int) session.save(bookmark);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Exception while creating bookmark: ", e);
            throw new DAOException("Exception while creating bookmark: ", e);
        }

        return bookmarkId;
    }

    @Override
    public void delete(Bookmark bookmark) throws DAOException {
        LOGGER.debug("Call delete method: bookmark = " + bookmark);
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Bookmark persistBookmark = (Bookmark) session.get(Bookmark.class, bookmark.getId());

            session.delete(persistBookmark);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            LOGGER.error("Exception while removing bookmark: ", e);
            throw new DAOException("Exception while removing bookmark: ", e);
        }
    }

    @Override
    public void update(Bookmark bookmark) throws DAOException {
        LOGGER.debug("Call update method: bookmark = " + bookmark);
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Bookmark persistBookmark = (Bookmark) session.get(Bookmark.class, bookmark.getId());
            persistBookmark.setCreateDate(bookmark.getCreateDate());
            persistBookmark.setDescription(bookmark.getDescription());
            persistBookmark.setName(bookmark.getName());
            persistBookmark.setCategory(bookmark.getCategory());
            persistBookmark.setUrl(bookmark.getUrl());

            session.update(persistBookmark);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            LOGGER.error("Exception while updating bookmark: ", e);
            throw new DAOException("Exception while updating bookmark: ", e);
        }
    }

    @Override
    public List<Bookmark> getAll() throws DAOException {
        LOGGER.debug("Call getAll method");
        List<Bookmark> bookmarks;
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            bookmarks = session.createQuery("FROM Bookmark").list();

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            LOGGER.error("Exception while receiving bookmark list: ", e);
            throw new DAOException("Exception while receiving bookmark list: ", e);
        }

        return bookmarks;
    }

    @Override
    public Bookmark getById(int id) throws DAOException {
        LOGGER.debug("Call getById method: id = " + id);
        try (Session session = getSessionFactory().openSession()) {
            IdentifierLoadAccess<Bookmark> categoryIdentifierLoadAccess = session.byId(Bookmark.class);
            return (Bookmark) categoryIdentifierLoadAccess.load(id);

        } catch (HibernateException e) {
            LOGGER.error("Exception while receiving bookmark by id: ", e);
            throw new DAOException("Exception while receiving bookmark by id: ", e);
        }
    }
}
