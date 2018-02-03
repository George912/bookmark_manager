package ru.bellintegrator.db.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.bellintegrator.db.dao.GenericDAO;
import ru.bellintegrator.db.exception.DAOException;
import ru.bellintegrator.db.exception.ServiceException;
import ru.bellintegrator.db.model.Bookmark;
import ru.bellintegrator.db.service.BookmarkService;

import java.util.List;

/**
 * Created by YANesterov on 24.01.2018.
 * Реализация BookmarkService.
 */
public class BookmarkServiceImpl implements BookmarkService {
    private static final Logger LOGGER = Logger.getLogger(BookmarkServiceImpl.class);
    private GenericDAO<Bookmark> dao;

    public BookmarkServiceImpl() {
        LOGGER.info("BookmarkService instance created");
    }

    @Autowired
    @Qualifier("bookmarkDao")
    public void setDao(GenericDAO<Bookmark> dao) {
        this.dao = dao;
    }

    @Override
    public void add(Bookmark bookmark) throws ServiceException {
        LOGGER.debug("Call add method: bookmark = " + bookmark);
        try {
            dao.create(bookmark);

        } catch (DAOException e) {
            LOGGER.error("Exception while adding bookmark: ", e);
            throw new ServiceException("Exception while adding bookmark: ", e);
        }
    }

    @Override
    public void update(Bookmark bookmark) throws ServiceException {
        LOGGER.debug("Call update method: bookmark = " + bookmark);
        try {
            dao.update(bookmark);

        } catch (DAOException e) {
            LOGGER.error("Exception while updating bookmark: ", e);
            throw new ServiceException("Exception while updating bookmark: ", e);
        }
    }

    @Override
    public void delete(Bookmark bookmark) throws ServiceException {
        LOGGER.debug("Call delete method: bookmark = " + bookmark);
        try {
            dao.delete(bookmark);

        } catch (DAOException e) {
            LOGGER.error("Exception while removing bookmark: ", e);
            throw new ServiceException("Exception while removing bookmark: ", e);
        }
    }

    @Override
    public List<Bookmark> list() throws ServiceException {
        LOGGER.debug("Call list method");
        try {
            return dao.getAll();

        } catch (DAOException e) {
            LOGGER.error("Exception while retrieving bookmark list: ", e);
            throw new ServiceException("Exception while retrieving bookmark list: ", e);
        }
    }

    @Override
    public Bookmark findById(int id) throws ServiceException {
        LOGGER.debug("Call findById method: id = " + id);

        try {
            return dao.getById(id);

        } catch (DAOException e) {
            LOGGER.error("Exception while retrieving bookmark by id: ", e);
            throw new ServiceException("Exception while retrieving bookmark by id: ", e);
        }
    }
}
