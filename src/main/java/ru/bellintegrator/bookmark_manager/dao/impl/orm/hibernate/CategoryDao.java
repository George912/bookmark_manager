package ru.bellintegrator.bookmark_manager.dao.impl.orm.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.bookmark_manager.dao.GenericDAO;
import ru.bellintegrator.bookmark_manager.exception.DAOException;
import ru.bellintegrator.bookmark_manager.model.Category;

import java.util.List;

/**
 * Hibernate реализация GenericDAO для модели Category.
 */
@Repository("categoryDao")
public class CategoryDao extends AbstractConnectable implements GenericDAO<Category> {
    private static final Logger LOGGER = Logger.getLogger(CategoryDao.class);

    @Override
    public int create(Category category) throws DAOException {
        LOGGER.debug("Call create method: category = " + category);
        Transaction transaction = null;
        int categoryId;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            categoryId = (int) session.save(category);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Exception while creating category: ", e);
            throw new DAOException("Exception while creating category: ", e);
        }

        return categoryId;
    }

    @Override
    public void delete(Category category) throws DAOException {
        LOGGER.debug("Call delete method: category = " + category);
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Category persistCategory = (Category) session.get(Category.class, category.getId());

            session.delete(persistCategory);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            LOGGER.error("Exception while removing category: ", e);
            throw new DAOException("Exception while removing category: ", e);
        }
    }

    @Override
    public void update(Category category) throws DAOException {
        LOGGER.debug("Call update method: category = " + category);
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Category persistCategory = (Category) session.get(Category.class, category.getId());
            persistCategory.setCreateDate(category.getCreateDate());
            persistCategory.setDescription(category.getDescription());
            persistCategory.setName(category.getName());
            persistCategory.setParent(category.getParent());

            session.update(persistCategory);

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            LOGGER.error("Exception while updating category: ", e);
            throw new DAOException("Exception while updating category: ", e);
        }
    }

    @Override
    public List<Category> getAll() throws DAOException {
        LOGGER.debug("Call getAll method");
        List<Category> categories;
        Transaction transaction = null;

        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            categories = session.createQuery("FROM Category").list();

            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            LOGGER.error("Exception while receiving category list: ", e);
            throw new DAOException("Exception while receiving category list: ", e);
        }

        return categories;
    }

    @Override
    public Category getById(int id) throws DAOException {
        LOGGER.debug("Call getById method: id = " + id);
        try (Session session = getSessionFactory().openSession()) {
            IdentifierLoadAccess<Category> categoryIdentifierLoadAccess = session.byId(Category.class);
            return (Category) categoryIdentifierLoadAccess.load(id);

        } catch (HibernateException e) {
            LOGGER.error("Exception while receiving category by id: ", e);
            throw new DAOException("Exception while receiving category by id: ", e);
        }
    }
}
