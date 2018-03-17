package java.ru.bellintegrator.impl;

import java.ru.bellintegrator.api.Bookmark;
import java.ru.bellintegrator.api.Category;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Реализация DTO API Category
 */
public class CategoryTO implements Category{
    private Long id;
    private String name;
    private String description;
    private Timestamp createDate;
    private Set<Category> categories;
    private int version;
    private Set<Bookmark> bookmarks;
    private Category parent;

    public CategoryTO() {
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.bookmarks = new HashSet<>();
    }
    public CategoryTO(String name) {
        this();
        this.name = name;
    }
    public CategoryTO(String name, String description, Set<Category> categories, Set<Bookmark> bookmarks) {
        this(name);
        this.description = description;
        this.categories = categories;
        this.bookmarks = bookmarks;
    }

    @Override
    public Long getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public Timestamp getCreateDate() {
        return createDate;
    }
    @Override
    public int getVersion() {
        return version;
    }
    @Override
    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }
    @Override
    public Set<Category> getCategories() {
        return categories;
    }
    @Override
    public Category getParent() {
        return parent;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    @Override
    public void setVersion(int version) {
        this.version = version;
    }
    @Override
    public void setBookmarks(Set<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }
    @Override
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    @Override
    public void setParent(Category parent) {
        this.parent = parent;
    }
}
