CREATE TABLE occurrence (
    id SERIAL NOT NULL PRIMARY KEY,
    delivery_id SERIAL NOT NULL,
    description TEXT NOT NULL,
    date_register TIMESTAMP NOT NULL
);

ALTER TABLE occurrence ADD CONSTRAINT fk_occurrence_delivery
FOREIGN KEY (delivery_id) REFERENCES delivery (id);