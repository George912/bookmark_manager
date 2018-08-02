package ru.bellintegrator.db.service;

import ru.bellintegrator.db.exception.ServiceException;
import ru.bellintegrator.model.Bookmark;

import java.util.List;

/**
 * Created by YANesterov on 24.01.2018.
 * Сервис работы с моделью Bookmark.
 */
public interface BookmarkService extends BaseService<Bookmark> {
    /**
     * Возвращает список закладок определённой категории
     *
     * @param categoryId идентификатор категории
     * @return список закладок
     */
    List<Bookmark> listByCategoryId(Long categoryId) throws ServiceException;

    /**
     * Удаляет все закладки категории с идентификатором categoryId
     *
     * @param categoryId иденткатор категории
     * @return количество удалённых закладок
     * @throws ServiceException
     */
    int deleteAll(Long categoryId) throws ServiceException;

}
