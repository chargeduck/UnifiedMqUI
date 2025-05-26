create table mq_port_dict
(
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    mq_type      INT     not null,
    default_flag BOOLEAN not null,
    port_label   text,
    default_port integer,
    desc         text

);
