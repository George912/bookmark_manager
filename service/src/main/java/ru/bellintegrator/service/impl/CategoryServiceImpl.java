package ru.bellintegrator.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.bellintegrator.core.exception.DAOException;
import ru.bellintegrator.core.exception.ServiceException;
import ru.bellintegrator.db.dao.GenericDAO;
import ru.bellintegrator.core.domain.Category;
import ru.bellintegrator.service.CategoryService;

import java.util.List;

/**
 * Created by YANesterov on 24.01.2018.
 * Реализация CategoryService
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);
    private GenericDAO<Category> dao;

    public CategoryServiceImpl() {
        LOGGER.info("CategoryService instance created");
    }

    @Autowired
    @Qualifier("categoryDao")
    public void setDao(GenericDAO<Category> dao) {
        this.dao = dao;
    }

    @Override
    public void add(Category category) throws ServiceException {
        LOGGER.debug("Call add method: category = " + category);

        try {
            dao.create(resolveHierarchyData(category));
        } catch (DAOException e) {
            LOGGER.error("Exception while adding category: ", e);
            throw new ServiceException("Exception while adding category: ", e);
        }
    }

    @Override
    public void update(Category category) throws ServiceException {
        LOGGER.debug("Call update method: category = " + category);

        try {
            dao.update(resolveHierarchyData(category));
        } catch (DAOException e) {
            LOGGER.error("Exception while updating category: ", e);
            throw new ServiceException("Exception while updating category: ", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        LOGGER.debug("Call delete method: id = " + id);

        try {
            dao.delete(id);
        } catch (DAOException e) {
            LOGGER.error("Exception while removing category: ", e);
            throw new ServiceException("Exception while removing category: ", e);
        }
    }

    @Override
    public List<Category> list() throws ServiceException {
        LOGGER.debug("Call list method");
        try {
            return dao.getAll();

        } catch (DAOException e) {
            LOGGER.error("Exception while retrieving category list: ", e);
            throw new ServiceException("Exception while retrieving category list: ", e);
        }
    }

    @Override
    public Category findById(Long id) throws ServiceException {
        LOGGER.debug("Call findById method: id = " + id);

        try {
            return dao.getById(id);

        } catch (DAOException e) {
            LOGGER.error("Exception while retrieving category by id: ", e);
            throw new ServiceException("Exception while retrieving category by id: ", e);
        }
    }

    /**
     * Разрешает данные иерархии категории:
     * parent, top, level
     *
     * @param category категория
     * @return категория с разрешёнными данными иерархии
     * @throws ServiceException
     */
    private Category resolveHierarchyData(Category category) throws ServiceException {
        LOGGER.debug("call resolveHierarchyData(" + category + ")");

        Category parent = null;
        if (category.getParentId() != null) {
            try {
                parent = dao.getById(category.getParentId());
            } catch (DAOException e) {
                LOGGER.debug("exception while resolve hierarchy data(parentId, level, topId)");
                throw new ServiceException("exception while resolve hierarchy data(parentId, level, topId)", e);
            }
        }
        if (parent != null) {
            category.setParent(parent);

            if (category.getId() == parent.getId()) {
                category.setTop(category);
                category.setLevel((short) 0);
            } else {
                category.setTop(parent.getTop());
                category.setLevel((short) (parent.getLevel() + 1));
            }
        }
        return category;
    }
}