package ru.bellintegrator.advice;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Применяется для вычисления и установки
 * полей parent_id, level, top_id категории.
 * Вызывается перед выполнением CategoryDaoImpl.update()
 */
public class HierarchyAdvice implements MethodBeforeAdvice {
    private static final Logger LOGGER = Logger.getLogger(HierarchyAdvice.class);

    public void before(Method method, Object[] objects, Object o) throws Throwable {
        LOGGER.debug("call before(" + method.getName() + ", " + objects + ", " + o + ")");


    }
}
