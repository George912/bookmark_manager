package java.ru.bellintegrator.api;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * API DTO сущности "Категория"
 */
public interface Category extends Serializable{
    Long getId();
    String getName();
    String getDescription();
    Timestamp getCreateDate();
    int getVersion();
    Set<Bookmark> getBookmarks();
    Set<Category> getCategories();
    Category getParent();

    void setId(Long id);
    void setName(String name);
    void setDescription(String description);
    void setCreateDate(Timestamp createDate);
    void setVersion(int version);
    void setBookmarks(Set<Bookmark> bookmarks);
    void setCategories(Set<Category> categories);
    void setParent(Category parent);
}
