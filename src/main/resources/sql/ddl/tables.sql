SET SCHEMA BOOKMARK_MANAGER_SCHEMA;

CREATE CACHED TABLE IF NOT EXISTS CATEGORIES
(
  ID          INTEGER     NOT NULL,
  NAME        VARCHAR(50) NOT NULL,
  DESCRIPTION VARCHAR(300),
  ICON        BLOB,
  CREATE_DATE TIMESTAMP   NOT NULL,
  PARENT_ID   INTEGER     NOT NULL,
  PRIMARY KEY (ID)
);

CREATE CACHED TABLE IF NOT EXISTS BOOKMARKS
(
  ID          INTEGER      NOT NULL,
  NAME        VARCHAR(50)  NOT NULL,
  URL         VARCHAR(255) NOT NULL,
  DESCRIPTION VARCHAR(300),
  ICON        BLOB,
  CREATE_DATE TIMESTAMP    NOT NULL,
  CATEGORY_ID INTEGER      NOT NULL,
  PRIMARY KEY (ID)
);

