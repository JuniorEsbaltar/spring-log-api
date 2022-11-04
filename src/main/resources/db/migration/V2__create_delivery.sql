CREATE TABLE delivery (
    id SERIAL NOT NULL PRIMARY KEY,
    client_id SERIAL not null,
    fee DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    date_order TIMESTAMP NOT NULL,
    date_finished TIMESTAMP,

    recipient_name VARCHAR(60) NOT NULL,
    recipient_street VARCHAR(255) NOT NULL,
    recipient_number VARCHAR(30) NOT NULL,
    recipient_complement VARCHAR(60) NOT NULL,
    recipient_neighborhood VARCHAR(30) NOT NULL
);

alter table delivery add constraint fk_delivery_client
foreign key (client_id) references client (id);