package ru.bellintegrator.bookmark_manager.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.bellintegrator.bookmark_manager.dao.GenericDAO;
import ru.bellintegrator.bookmark_manager.exception.DAOException;
import ru.bellintegrator.bookmark_manager.exception.ServiceException;
import ru.bellintegrator.bookmark_manager.model.Category;
import ru.bellintegrator.bookmark_manager.service.CategoryService;

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
            dao.create(category);

        } catch (DAOException e) {
            LOGGER.error("Exception while adding category: ", e);
            throw new ServiceException("Exception while adding category: ", e);
        }
    }

    @Override
    public void update(Category category) throws ServiceException {
        LOGGER.debug("Call update method: category = " + category);
        try {
            dao.update(category);

        } catch (DAOException e) {
            LOGGER.error("Exception while updating category: ", e);
            throw new ServiceException("Exception while updating category: ", e);
        }
    }

    @Override
    public void delete(Category category) throws ServiceException {
        LOGGER.debug("Call delete method: category = " + category);
        try {
            dao.delete(category);

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
    public Category findById(int id) throws ServiceException {
        LOGGER.debug("Call findById method: id = " + id);

        try {
            return dao.getById(id);

        } catch (DAOException e) {
            LOGGER.error("Exception while retrieving category by id: ", e);
            throw new ServiceException("Exception while retrieving category by id: ", e);
        }
    }
}
