SET SCHEMA BOOKMARK_MANAGER_SCHEMA;

CREATE SEQUENCE IF NOT EXISTS CATEGORIES_SEQ START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS BOOKMARKS_SEQ START WITH 1 INCREMENT BY 1;
-- todo: drop if not use
CREATE SEQUENCE IF NOT EXISTS USERS_SEQ START WITH 1 INCREMENT BY 1;