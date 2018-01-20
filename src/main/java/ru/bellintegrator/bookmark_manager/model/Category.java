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
    //TODO: long
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
