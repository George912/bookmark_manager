package ru.bellintegrator.bookmark_manager.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Категория закладки.
 * Таблица: categories
 */
@Entity
@Table(name = "CATEGORIES")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;
    //TODO:image field, modify constructor, relations

    @Column(name = "CREATE_DATE")
    private Timestamp createDate;

    @Column(name = "PARENT_ID")
    private Category parent;

    public Category() {
        this.createDate = new Timestamp(System.currentTimeMillis());
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public Category(String name, String description, Category parent) {
        this(name);
        this.description = description;
        this.parent = parent;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (!id.equals(category.id)) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (description != null ? !description.equals(category.description) : category.description != null)
            return false;
        if (!createDate.equals(category.createDate)) return false;
        return parent != null ? parent.equals(category.parent) : category.parent == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + createDate.hashCode();
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", parent=" + parent +
                '}';
    }
}
