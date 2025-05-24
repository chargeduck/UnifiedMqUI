CREATE TABLE connect_config
(
    id          INTEGER PRIMARY KEY AUTOINCREMENT,
    host        TEXT,
    port        TEXT,
    extra_port        TEXT,
    mq_type     INTEGER,
    group_type  BOOLEAN,
    group_url   TEXT,
    title       TEXT,
    username    TEXT,
    password    TEXT,
    version     TEXT,
    extra       TEXT,
    broker_name TEXT,
    broker_id   TEXT,
    up_time     TEXT,
    active_flag integer default 0 not null ,
    create_time timestamp
);