package ru.bellintegrator.db.model;

import ru.bellintegrator.db.listener.HierarchyListener;

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
@EntityListeners({HierarchyListener.class})
@Table(schema = "bookmark_manager_schema", name = "CATEGORIES")
public class Category implements Serializable, IHierarchyElement {
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

    @Version
    @Column(name = "VERSION")
    private int version;

    @Column(name = "LEVEL", nullable = false)
    private Short level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOP_ID")
    private Category top;

    @OneToMany(mappedBy = "category")
    private Set<Bookmark> bookmarks;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;

    public Category() {
        this.createDate = new Timestamp(System.currentTimeMillis());
        this.bookmarks = new HashSet<>();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public Category(String name, String description, Set<Bookmark> bookmarks) {
        this(name);
        this.description = description;
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

    @Override
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @Override
    public Short getLevel() {
        return level;
    }

    @Override
    public void setLevel(Short level) {
        this.level = level;
    }

    @Override
    public Category getTop() {
        return top;
    }

    @Override
    public void setTop(IHierarchyElement top) {
        this.top = (Category) top;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (version != category.version) return false;
        if (!id.equals(category.id)) return false;
        if (!name.equals(category.name)) return false;
        if (description != null ? !description.equals(category.description) : category.description != null)
            return false;
        if (!createDate.equals(category.createDate)) return false;
        if (!level.equals(category.level)) return false;
        if (top != null ? !top.equals(category.top) : category.top != null) return false;
        if (bookmarks != null ? !bookmarks.equals(category.bookmarks) : category.bookmarks != null) return false;
        return parent != null ? parent.equals(category.parent) : category.parent == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + createDate.hashCode();
        result = 31 * result + version;
        result = 31 * result + level.hashCode();
        result = 31 * result + (top != null ? top.hashCode() : 0);
        result = 31 * result + (bookmarks != null ? bookmarks.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Category{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", createDate=").append(createDate);
        sb.append(", version=").append(version);
        sb.append(", level=").append(level);
        sb.append(", top.id=").append(top != null ? top.id : null);
        sb.append(", bookmarks.size=").append(bookmarks.size());
        sb.append(", parent.id=").append(parent != null ? parent.id : null);
        sb.append('}');
        return sb.toString();
    }
}
