package ru.bellintegrator.db.dao;

import ru.bellintegrator.core.exception.DAOException;

/**
 * Включает дополнительные методы для работы с категориями.
 * <ol>
 * <li>getBookmarkListByCategoryId</li>
 * <li>deleteAll</li>
 * </ol>
 */
public interface CategoryManager {
    /**
     * Удаляет все закладки подкатегории категории с идентификатором categoryId
     *
     * @param categoryId иденткатор категории
     * @return количество удалённых подкатегорий
     * @throws DAOException
     */
    int deleteAll(Long categoryId) throws DAOException;
}