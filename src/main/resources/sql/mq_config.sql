create table mq_config (
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title    VARCHAR(255),
    create_time TIMESTAMP
)