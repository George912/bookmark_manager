package ru.bellintegrator.db.model;

/**
 * Интерфейс для реализации и поддержки иерархических данных
 */
public interface IHierarchyElement {
    IHierarchyElement getParent();

    Short getLevel();

    void setLevel(Short level);

    IHierarchyElement getTop();

    void setTop(IHierarchyElement top);

    Long getParentId();

    void setParentId(Long parentId);
}
