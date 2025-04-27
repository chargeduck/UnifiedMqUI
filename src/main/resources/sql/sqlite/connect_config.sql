CREATE TABLE connect_config
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    host        TEXT,
    port        TEXT,
    mq_type     INTEGER,
    group_type  BOOLEAN,
    group_url   TEXT,
    title       TEXT,
    username    TEXT,
    password    TEXT,
    version     TEXT,
    extra       TEXT,
    create_time TIMESTAMP
);