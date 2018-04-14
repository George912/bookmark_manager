package ru.bellintegrator.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Категория закладки.
 * Таблица: categories
 */
@Entity
@Table(schema = "bookmark_manager_schema", name = "CATEGORIES")
public class Category implements Serializable {
    private static final long serialVersionUID = -4759397049790260072L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;

    @Column(name = "CREATE_DATE")
    private Timestamp createDate;

    @OneToMany
    @JoinColumn(name = "PARENT_ID")
    private Set<Category> categories;

    @Version
    @Column(name = "VERSION")
    private int version;

    @OneToMany(mappedBy = "category")
    private Set<Bookmark> bookmarks;

    @OneToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    public Category() {
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.bookmarks = new HashSet<>();
        this.categories = new HashSet<>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public Category(String name, String description, Set<Category> categories, Set<Bookmark> bookmarks) {
        this(name);
        this.description = description;
        this.categories = categories;
        this.bookmarks = bookmarks;
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

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Set<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
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
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (getVersion() != category.getVersion()) return false;
        if (!getId().equals(category.getId())) return false;
        if (!getName().equals(category.getName())) return false;
        if (getDescription() != null ? !getDescription().equals(category.getDescription()) : category.getDescription() != null)
            return false;
        if (!getCreateDate().equals(category.getCreateDate())) return false;
        if (getCategories() != null ? !getCategories().equals(category.getCategories()) : category.getCategories() != null)
            return false;
        if (getBookmarks() != null ? !getBookmarks().equals(category.getBookmarks()) : category.getBookmarks() != null)
            return false;
        return getParent() != null ? getParent().equals(category.getParent()) : category.getParent() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getCreateDate().hashCode();
        result = 31 * result + (getCategories() != null ? getCategories().hashCode() : 0);
        result = 31 * result + getVersion();
        result = 31 * result + (getBookmarks() != null ? getBookmarks().hashCode() : 0);
        result = 31 * result + (getParent() != null ? getParent().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", categoriesSize=" + categories.size() +
                ", version=" + version +
                ", bookmarksSize=" + bookmarks.size() +
                ", parent=" + parent +
                '}';
    }
}
