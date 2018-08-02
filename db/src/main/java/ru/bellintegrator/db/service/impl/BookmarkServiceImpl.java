package ru.bellintegrator.db.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.bellintegrator.db.dao.GenericDAO;
import ru.bellintegrator.db.dao.impl.orm.hibernate.BookmarkDaoImpl;
import ru.bellintegrator.db.dao.impl.orm.hibernate.BookmarkManager;
import ru.bellintegrator.db.exception.DAOException;
import ru.bellintegrator.db.exception.ServiceException;
import ru.bellintegrator.db.service.BookmarkService;
import ru.bellintegrator.db.service.CategoryService;
import ru.bellintegrator.model.Bookmark;
import ru.bellintegrator.model.Category;

import java.util.List;

/**
 * Created by YANesterov on 24.01.2018.
 * Реализация BookmarkService.
 */
@Service("bookmarkService")
public class BookmarkServiceImpl implements BookmarkService {
    private static final Logger LOGGER = Logger.getLogger(BookmarkServiceImpl.class);
    private GenericDAO<Bookmark> dao;
    private CategoryService categoryService;

    public BookmarkServiceImpl() {
        LOGGER.info("BookmarkService instance created");
    }

    @Autowired
    @Qualifier("bookmarkDao")
    public void setDao(GenericDAO<Bookmark> dao) {
        this.dao = dao;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void add(Bookmark bookmark) throws ServiceException {
        LOGGER.debug("Call add method: bookmark = " + bookmark);
        try {
            dao.create(resolveCategory(bookmark));

        } catch (DAOException e) {
            LOGGER.error("Exception while adding bookmark: ", e);
            throw new ServiceException("Exception while adding bookmark: ", e);
        }
    }

    @Override
    public void update(Bookmark bookmark) throws ServiceException {
        LOGGER.debug("Call update method: bookmark = " + bookmark);
        try {
            dao.update(resolveCategory(bookmark));

        } catch (DAOException e) {
            LOGGER.error("Exception while updating bookmark: ", e);
            throw new ServiceException("Exception while updating bookmark: ", e);
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        LOGGER.debug("Call delete method: id = " + id);
        try {
            dao.delete(id);

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
    public Bookmark findById(Long id) throws ServiceException {
        LOGGER.debug("Call findById method: id = " + id);

        try {
            return dao.getById(id);

        } catch (DAOException e) {
            LOGGER.error("Exception while retrieving bookmark by id: ", e);
            throw new ServiceException("Exception while retrieving bookmark by id: ", e);
        }
    }

    /**
     * Получает персистентную категорию, id которой выбран
     * в combo box на странице редактора закладки
     * и устанавливает её закладке
     *
     * @param bookmark закладка
     * @return закладка с разрешённой категорией
     * @throws ServiceException
     */
    private Bookmark resolveCategory(Bookmark bookmark) throws ServiceException {
        LOGGER.debug("call resolveCategory(" + bookmark + ")");

        Category category = null;
        if (bookmark.getCategoryId() != null) {
            try {
                category = categoryService.findById(bookmark.getCategoryId());
            } catch (ServiceException e) {
                LOGGER.debug("exception while resolve bookmark category");
                throw new ServiceException("exception while resolve bookmark category", e);
            }
        }
        bookmark.setCategory(category);
        return bookmark;
    }

    @Override
    public List<Bookmark> listByCategoryId(Long categoryId) throws ServiceException {
        try {
            return ((BookmarkManager) dao).getBookmarkListByCategoryId(categoryId);
        } catch (DAOException e) {
            LOGGER.debug("exception while resolve bookmark list by category id");
            throw new ServiceException("exception while resolve bookmark list by category id", e);
        }
    }

    @Override
    public int deleteAll(Long categoryId) throws ServiceException {
        try {
            return ((BookmarkManager) dao).deleteAll(categoryId);
        } catch (DAOException e) {
            LOGGER.error("Exception while removing bookmarks by category id: ", e);
            throw new ServiceException("Exception while removing bookmarks by category id: ", e);
        }
    }
}
