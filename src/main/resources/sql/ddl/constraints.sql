SET SCHEMA BOOKMARK_MANAGER_SCHEMA;

-- CONSTRAINTS FOR TABLE 'CATEGORIES'
ALTER TABLE CATEGORIES ADD CONSTRAINT CONSTRAINT_6A FOREIGN KEY(PARENT_ID) INDEX CONSTRAINT_INDEX_6 REFERENCES CATEGORIES(ID) NOCHECK;

-- CONSTRAINTS FOR TABLE 'BOOKMARKS'
ALTER TABLE BOOKMARKS ADD CONSTRAINT CONSTRAINT_1F FOREIGN KEY(CATEGORY_ID) INDEX CONSTRAINT_INDEX_1 REFERENCES CATEGORIES(ID) NOCHECK;
