package ru.bellintegrator.bookmark_manager.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Категория закладки.
 * Таблица: categories
 */
@Entity
@Table(schema = "bookmark_manager_schema", name = "CATEGORIES")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;

    @Column(name = "CREATE_DATE")
    private Timestamp createDate;

    @OneToMany(targetEntity = Category.class, cascade = CascadeType.ALL)
    private List<Category> categories;

    @Version
    @Column(name = "VERSION")
    private int version;

    @OneToMany(targetEntity = Bookmark.class)
    private List<Bookmark> bookmarkList;

    public Category() {
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.bookmarkList = new ArrayList<>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public Category(String name, String description, List<Category> categories, List<Bookmark> bookmarkList) {
        this(name);
        this.description = description;
        this.categories = categories;
        this.bookmarkList = bookmarkList;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Bookmark> getBookmarkList() {
        return bookmarkList;
    }

    public void setBookmarkList(List<Bookmark> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", categories=" + categories +
                ", version=" + version +
                ", bookmarkList=" + bookmarkList +
                '}';
    }
}
