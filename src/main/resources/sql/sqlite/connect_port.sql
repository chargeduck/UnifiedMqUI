CREATE TABLE connect_port
(
    id           INTEGER PRIMARY KEY AUTOINCREMENT,
    port_dict_id INTEGER not null,
    connect_id   INTEGER not null,
    input_port   INTEGER
);