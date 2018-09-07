SET SCHEMA SECURITY;

CREATE CACHED TABLE IF NOT EXISTS USERS(
  USERNAME VARCHAR(50) NOT NULL PRIMARY KEY,
  PASSWORD VARCHAR(60) NOT NULL,
  ENABLED BOOLEAN NOT NULL
);

CREATE CACHED TABLE IF NOT EXISTS AUTHORITIES(
  USERNAME VARCHAR(50) NOT NULL,
  AUTHORITY VARCHAR(50) NOT NULL
);