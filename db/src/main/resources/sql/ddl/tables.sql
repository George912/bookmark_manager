SET SCHEMA BOOKMARK_MANAGER_SCHEMA;

CREATE CACHED TABLE IF NOT EXISTS CATEGORIES
(
ID BIGINT NOT NULL,
NAME VARCHAR (50) NOT NULL,
DESCRIPTION VARCHAR (300),
CREATE_DATE TIMESTAMP NOT NULL,
PARENT_ID INTEGER NULL,
LEVEL SMALLINT NOT NULL DEFAULT 0,
TOP_ID INTEGER NOT NULL,
VERSION INTEGER NOT NULL,
PRIMARY KEY (ID),
);

CREATE CACHED TABLE IF NOT EXISTS BOOKMARKS
(
  ID BIGINT NOT NULL,
  NAME VARCHAR (50) NOT NULL,
  URL VARCHAR (255) NOT NULL,
  DESCRIPTION VARCHAR (300),
  ICON        BLOB,
  CREATE_DATE TIMESTAMP NOT NULL,
  CATEGORY_ID INTEGER NOT NULL,
  VERSION INTEGER NOT NULL,
  PRIMARY KEY (ID)
);

