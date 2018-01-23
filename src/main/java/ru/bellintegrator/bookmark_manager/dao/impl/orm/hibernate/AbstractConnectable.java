package ru.bellintegrator.bookmark_manager.dao.impl.orm.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Утильный класс, содержащий метод получения hibernate сессии.
 */
public class AbstractConnectable {
    private static final Logger log = Logger.getLogger(AbstractConnectable.class);
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    /**
     * Возвращает hibernate сессию
     *
     * @return hibernate сессия
     */
    protected SessionFactory getSessionFactory() {
        log.debug("Call getSessionFactory method");
        return factory;
    }
}
