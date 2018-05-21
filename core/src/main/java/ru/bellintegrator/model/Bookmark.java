package ru.bellintegrator.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Закладка.
 * Таблица: bookmarks
 */
@Entity
@Table(schema = "bookmark_manager_schema", name = "BOOKMARKS")
public class Bookmark implements Serializable {
    private static final long serialVersionUID = 6293892023058834767L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Version
    @Column(name = "VERSION")
    private int version;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookmark bookmark = (Bookmark) o;

        if (version != bookmark.version) return false;
        if (!id.equals(bookmark.id)) return false;
        if (!name.equals(bookmark.name)) return false;
        if (!url.equals(bookmark.url)) return false;
        if (description != null ? !description.equals(bookmark.description) : bookmark.description != null)
            return false;
        if (!Arrays.equals(icon, bookmark.icon)) return false;
        if (!createDate.equals(bookmark.createDate)) return false;
        return category.equals(bookmark.category);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(icon);
        result = 31 * result + createDate.hashCode();
        result = (int) (31 * result + category.getId());
        result = 31 * result + version;
        return result;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", icon=" + Arrays.toString(icon) +
                ", createDate=" + createDate +
//                ", categoryId=" + category != null && category.getId() != null ? category.getId().toString() : "empty" +
                ", version=" + version +
                '}';
    }
}
