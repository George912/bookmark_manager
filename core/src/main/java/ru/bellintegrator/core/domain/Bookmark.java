package ru.bellintegrator.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Закладка.
 * Таблица: bookmarks
 */
@Entity
@Table(schema = "bookmark_manager_schema", name = "BOOKMARKS")
public class Bookmark implements Serializable {
    private static final long serialVersionUID = 6293892023058834767L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
            , generator = "BOOKMARKS_SEQ"
    )
    @SequenceGenerator(
            name = "BOOKMARKS_SEQ"
            , sequenceName = "BOOKMARKS_SEQ"
            , allocationSize = 1
            , schema = "BOOKMARK_MANAGER_SCHEMA"
    )
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "URL")
    private String url;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "ICON")
    private byte[] icon;

    @Column(name = "CREATE_DATE")
    private Timestamp createDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Transient
    private Long categoryId;

    public Bookmark() {
        this.createDate = new Timestamp(System.currentTimeMillis());
    }

    public Bookmark(String name, String url) {
        this();
        this.name = name;
        this.url = url;
    }

    public Bookmark(String name, String url, String description) {
        this(name, url);
        this.description = description;
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

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    //todo: normal toString, equals and hashCode
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Bookmark{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", icon=");
        if (icon == null) sb.append("null");
        else {
            sb.append('[');
            for (int i = 0; i < icon.length; ++i)
                sb.append(i == 0 ? "" : ", ").append(icon[i]);
            sb.append(']');
        }
        sb.append(", createDate=").append(createDate);
//        sb.append(", category=").append(category.getId());
        sb.append(", version=").append(version);
        sb.append(", categoryId=").append(categoryId);
        sb.append('}');
        return sb.toString();
    }
}
