package ru.bellintegrator.db.dao;

import ru.bellintegrator.core.domain.wrappers.CategoryWrapper;
import ru.bellintegrator.core.exception.DAOException;

import java.util.List;

/**
 * Включает дополнительные методы для работы с категориями.
 * <ol>
 * <li>retrieveSubCategories</li>
 * </ol>
 */
public interface CategoryManager {
    /**
     * Получает подкатегории текущей категории.
     *
     * @param id    ид текущей категории
     * @param topId ид категории верхнего уровня текущей категории
     * @return коллекция подкатегорий
     */
    List<CategoryWrapper> retrieveSubCategories(Long id, Long topId) throws DAOException;
}