package ru.bellintegrator.db.service;

import ru.bellintegrator.db.exception.ServiceException;

import java.util.List;

/**
 * Created by YANesterov on 24.01.2018.
 * Интерфейс, содержащий базовые операции с экземплярами классов-моделей системы.
 *
 * @param <T>
 */
public interface BaseService<T> {

    /**
     * Добавляет сущность.
     *
     * @param t
     * @throws ServiceException
     */
    void add(T t) throws ServiceException;

    /**
     * Обновдяет сущность.
     *
     * @param t
     * @throws ServiceException
     */
    void update(T t) throws ServiceException;

    /**
     * Удаляет сущность.
     *
     * @param t
     * @throws ServiceException
     */
    void delete(T t) throws ServiceException;

    /**
     * Получает список сущностей, пользователя с идентификатором ownerId.
     *
     * @return
     * @throws ServiceException
     */
    List<T> list() throws ServiceException;

    /**
     * Осуществляет поиск сущности по идентификатору id,
     * владельцем которой является пользователь с идентификатором ownerId.
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    T findById(int id) throws ServiceException;
}
