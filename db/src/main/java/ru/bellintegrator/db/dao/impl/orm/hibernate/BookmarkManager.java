package ru.bellintegrator.db.dao.impl.orm.hibernate;

        import ru.bellintegrator.db.exception.DAOException;
        import ru.bellintegrator.model.Bookmark;

        import java.util.List;

/**
 * Включает дополнительные методы для работы с закладками.
 * <ol>
 * <li>getBookmarkListByCategoryId</li>
 * <li></li>
 * <li></li>
 * <li></li>
 * </ol>
 */
public interface BookmarkManager {
    /**
     * Возвращает список закладок определённой категории
     *
     * @param categoryId идентификатор категории
     * @return список закладок
     * @throws DAOException
     */
    List<Bookmark> getBookmarkListByCategoryId(Long categoryId) throws DAOException;

    /**
     * Удаляет все закладки категории с идентификатором categoryId
     *
     * @param categoryId иденткатор категории
     * @return количество удалённых закладок
     * @throws DAOException
     */
    int deleteAll(Long categoryId) throws DAOException;
}