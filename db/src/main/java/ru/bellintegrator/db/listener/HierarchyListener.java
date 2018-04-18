package ru.bellintegrator.db.listener;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.bellintegrator.db.model.IHierarchyElement;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
//todo: don't work

/**
 * Листенер для поддержания данных модели списка смежности
 */
@Service("hierarchyListener")
public class HierarchyListener {
    private static final Logger LOGGER = Logger.getLogger(HierarchyListener.class);

    @PrePersist
    @PreUpdate
    public void setLevelAndTop(IHierarchyElement entity) {
        LOGGER.debug("call setLevelAndTop(" + entity + ")");

        final IHierarchyElement parent = entity.getParent();

        //set level
        if (parent == null) {
            entity.setLevel((short) 0);
        } else {
            entity.setLevel((short) (parent.getLevel() + 1));
        }

        //set top
        if (entity.getParentId() == null) {
            entity.setTop(entity);
        } else if (parent != null) {
            entity.setTop(parent.getTop());
        }
    }
}
