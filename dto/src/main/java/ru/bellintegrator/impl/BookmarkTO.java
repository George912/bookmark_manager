package java.ru.bellintegrator.impl;

import java.ru.bellintegrator.api.Bookmark;
import java.ru.bellintegrator.api.Category;
import java.sql.Timestamp;

/**
 * Реализация DTO API Bookmark
 */
public class BookmarkTO implements Bookmark {
    private Long id;
    private String name;
    private String url;
    private String description;
    private byte[] icon;
    private Timestamp createDate;
    private Category category;
    private int version;

    public BookmarkTO() {
        this.createDate = new Timestamp(System.currentTimeMillis());
    }
    public BookmarkTO(String name, String url) {
        this();
        this.name = name;
        this.url = url;
    }
    public BookmarkTO(String name, String url, String description) {
        this(name, url);
        this.description = description;
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
    public String getUrl() {
        return url;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public byte[] getIcon() {
        return icon;
    }
    @Override
    public Timestamp getCreateDate() {
        return createDate;
    }
    @Override
    public Category getCategory() {
        return category;
    }
    @Override
    public int getVersion() {
        return version;
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
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public void setIcon(byte[] icon) {
        this.icon = icon;
    }
    @Override
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    @Override
    public void setCategory(Category category) {
        this.category = category;
    }
    @Override
    public void setVersion(int version) {
        this.version = version;
    }
}
