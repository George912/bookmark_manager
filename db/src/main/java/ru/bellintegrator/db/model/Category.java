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

    public void setParent(Category parent) {
        this.parent = parent;
    }
}
