package ru.bellintegrator.bookmark_manager.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Закладка.
 * Таблица: bookmarks
 */
@Entity
@Table(name = "BOOKMARKS")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;

    //TODO:image field, modify constructor, relations

    @Column(name = "CREATE_DATE")
    private Timestamp createDate;

    @Column(name = "CATEGORY_ID")
    private Category category;

    public Bookmark() {
        this.createDate = new Timestamp(System.currentTimeMillis());
    }

    public Bookmark(String name, String url) {
        this();
        this.name = name;
        this.url = url;
    }

    public Bookmark(String name, String url, String description, Category category) {
        this(name, url);
        this.description = description;
        this.category = category;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookmark bookmark = (Bookmark) o;

        if (!id.equals(bookmark.id)) return false;
        if (name != null ? !name.equals(bookmark.name) : bookmark.name != null) return false;
        if (url != null ? !url.equals(bookmark.url) : bookmark.url != null) return false;
        if (description != null ? !description.equals(bookmark.description) : bookmark.description != null)
            return false;
        if (!createDate.equals(bookmark.createDate)) return false;
        return category != null ? category.equals(bookmark.category) : bookmark.category == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + createDate.hashCode();
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", category=" + category +
                '}';
    }
}
