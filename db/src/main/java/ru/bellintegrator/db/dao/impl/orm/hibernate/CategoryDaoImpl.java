package ru.bellintegrator.db.dao.impl.orm.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.core.domain.Category;
import ru.bellintegrator.core.exception.DAOException;
import ru.bellintegrator.db.dao.GenericDAO;

import javax.annotation.Resource;
import java.util.List;

/**
 * Hibernate реализация GenericDAO для модели Category.
 */
@Transactional
@Repository("categoryDao")
public class CategoryDaoImpl implements GenericDAO<Category> {
    private static final Logger LOGGER = Logger.getLogger(CategoryDaoImpl.class);
    private static final String ID_CATEGORY_PROPERTY = "id";
    private static final String TOP_ID_CATEGORY_PROPERTY = "top.id";
    private static final String CATEGORY_FROM_CLAUSE = "FROM Category";

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public Long create(Category category) throws DAOException {
        LOGGER.debug("Call create method: category = " + category);
        Session session;
        Long categoryId;

        try {
            session = sessionFactory.getCurrentSession();
            categoryId = (Long) session.save(category);

        } catch (HibernateException e) {
            LOGGER.error("Exception while creating category: ", e);
            throw new DAOException("Exception while creating category: ", e);
        }
        return categoryId;
    }

    @Override
    public void delete(Long id) throws DAOException {
        LOGGER.debug("Call delete method: id = " + id);

        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(session.get(Category.class, id));
        } catch (HibernateException e) {
            LOGGER.error("Exception while removing category: ", e);
            throw new DAOException("Exception while removing category: ", e);
        }
    }

    @Override
    public void update(Category category) throws DAOException {
        LOGGER.debug("Call update method: category = " + category);
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
            Category persistCategory = (Category) session.get(Category.class, category.getId());
            persistCategory.setCreateDate(category.getCreateDate());
            persistCategory.setDescription(category.getDescription());
            persistCategory.setName(category.getName());
            persistCategory.setVersion(category.getVersion());
            persistCategory.setBookmarks(category.getBookmarks());
            persistCategory.setParent(category.getParent());
            persistCategory.setTop(category.getTop());
            persistCategory.setLevel(category.getLevel());
            session.update(persistCategory);
        } catch (HibernateException e) {
            LOGGER.error("Exception while updating category: ", e);
            throw new DAOException("Exception while updating category: ", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getAll() throws DAOException {
        LOGGER.debug("Call getAll method");
        List<Category> categories;
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
            categories = session.createQuery(CATEGORY_FROM_CLAUSE).list();

        } catch (HibernateException e) {
            LOGGER.error("Exception while receiving category list: ", e);
            throw new DAOException("Exception while receiving category list: ", e);
        }
        return categories;
    }

    @Override
    @Transactional(readOnly = true)
    public Category getById(Long id) throws DAOException {
        LOGGER.debug("Call getById method: id = " + id);
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
            IdentifierLoadAccess categoryIdentifierLoadAccess = session.byId(Category.class);
            return (Category) categoryIdentifierLoadAccess.load(id);

        } catch (HibernateException e) {
            LOGGER.error("Exception while receiving category by id: ", e);
            throw new DAOException("Exception while receiving category by id: ", e);
        }
    }
}