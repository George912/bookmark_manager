package ru.bellintegrator.core.domain.wrappers;

/**
 * Класс-обёртка для получения из БД подкатегорий категории.
 */
public class CategoryWrapper {
    private Long id;
    private String name;

    public CategoryWrapper() {
    }

    public CategoryWrapper(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryWrapper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}