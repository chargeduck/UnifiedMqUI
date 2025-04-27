create table connect_config (
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    host        VARCHAR(255),
    port varchar(5),
    mq_type int,
    group_type boolean,
    group_url varchar(255),
    title    VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    version varchar(50),
    extra varchar(10240),
    create_time TIMESTAMP
)