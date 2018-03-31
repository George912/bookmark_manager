package java.ru.bellintegrator.api;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * API DTO сущности "Закладка"
 */
public interface Bookmark extends Serializable {
    Long getId();
    String getName();
    String getUrl();
    String getDescription();
    byte[] getIcon();
    Timestamp getCreateDate();
    Category getCategory();
    int getVersion();

    void setId(Long id);
    void setName(String name);
    void setUrl(String url);
    void setDescription(String description);
    void setIcon(byte[] icon);
    void setCreateDate(Timestamp createDate);
    void setCategory(Category category);
    void setVersion(int version);
}
