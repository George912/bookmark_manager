SET SCHEMA BOOKMARK_MANAGER_SCHEMA;

INSERT INTO BOOKMARKS (ID, NAME, URL, DESCRIPTION, ICON, CREATE_DATE, CATEGORY_ID)
VALUES(NEXTVAL('BOOKMARKS_SEQ'), 'BOOKMARK1', 'TEST', 'BOOKMARK1', NULL, CURRENT_TIMESTAMP(), 37);
INSERT INTO BOOKMARKS (ID, NAME, URL, DESCRIPTION, ICON, CREATE_DATE, CATEGORY_ID)
VALUES(NEXTVAL('BOOKMARKS_SEQ'), 'BOOKMARK2', 'TEST', 'BOOKMARK2', NULL, CURRENT_TIMESTAMP(), 37);
INSERT INTO BOOKMARKS (ID, NAME, URL, DESCRIPTION, ICON, CREATE_DATE, CATEGORY_ID)
VALUES(NEXTVAL('BOOKMARKS_SEQ'), 'BOOKMARK3', 'TEST', 'BOOKMARK3', NULL, CURRENT_TIMESTAMP(), 40);
INSERT INTO BOOKMARKS (ID, NAME, URL, DESCRIPTION, ICON, CREATE_DATE, CATEGORY_ID)
VALUES(NEXTVAL('BOOKMARKS_SEQ'), 'BOOKMARK4', 'TEST', 'BOOKMARK4', NULL, CURRENT_TIMESTAMP(), 40);