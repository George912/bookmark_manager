package ru.bellintegrator.service;

import ru.bellintegrator.core.domain.Category;
import ru.bellintegrator.core.domain.wrappers.CategoryWrapper;
import ru.bellintegrator.core.exception.ServiceException;

import java.util.List;

/**
 * Created by YANesterov on 24.01.2018.
 * Сервис для работы с моделью Category.
 */
public interface CategoryService extends BaseService<Category> {
    /**
     * Получает подкатегории текущей категории.
     *
     * @param id    ид текущей категории
     * @param topId ид категории верхнего уровня текущей категории
     * @return коллекция подкатегорий
     */
    List<CategoryWrapper> retrieveSubCategories(Long id, Long topId) throws ServiceException;
}