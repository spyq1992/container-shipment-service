CREATE SEQUENCE temperature_range_id_seq;
CREATE SEQUENCE execution_plan_id_seq;
CREATE SEQUENCE execution_plan_action_id_seq;
CREATE SEQUENCE shipment_id_seq;

CREATE SEQUENCE temperature_range_seq;
CREATE SEQUENCE execution_plan_seq;
CREATE SEQUENCE execution_plan_action_seq;
CREATE SEQUENCE shipment_seq;



CREATE TABLE IF NOT EXISTS temperature_range (
                                                 id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('temperature_range_id_seq'),
                                                 min INT NOT NULL DEFAULT 0,
                                                 max INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS plan_template (
                                             id BIGINT NOT NULL PRIMARY KEY ,
                                             name VARCHAR(255) DEFAULT NULL,
                                             fk_temperature_range_id BIGINT DEFAULT NULL,
                                             CONSTRAINT fk_temperature_range_id FOREIGN KEY (fk_temperature_range_id) REFERENCES temperature_range (id)
);

CREATE TABLE IF NOT EXISTS action (
                                      id BIGINT NOT NULL PRIMARY KEY ,
                                      name VARCHAR(255) DEFAULT NULL,
                                      template_id BIGINT
);

CREATE TABLE IF NOT EXISTS execution_plan (
                                              id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('execution_plan_id_seq'),
                                              origin VARCHAR(255) DEFAULT NULL,
                                              destination VARCHAR(255) DEFAULT NULL,
                                              customer_id VARCHAR(255) DEFAULT NULL,
                                              transport_type VARCHAR(255) DEFAULT NULL,
                                              temperature_id BIGINT DEFAULT NULL,
                                              fragile BOOLEAN DEFAULT FALSE,
                                              notify_customer BOOLEAN DEFAULT FALSE,
                                              template_id BIGINT DEFAULT NULL,
                                              CONSTRAINT fk_template_id FOREIGN KEY (template_id) REFERENCES plan_template (id),
                                              CONSTRAINT fk_temperature_id FOREIGN KEY (temperature_id) REFERENCES temperature_range (id)
);

CREATE TABLE IF NOT EXISTS shipment (
                                              id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('shipment_id_seq'),
                                              origin VARCHAR(255) DEFAULT NULL,
                                              destination VARCHAR(255) DEFAULT NULL,
                                              customer_id VARCHAR(255) DEFAULT NULL,
                                              transport_type VARCHAR(255) DEFAULT NULL,
                                              temperature_id BIGINT DEFAULT NULL,
                                              created_date date,
                                              fragile BOOLEAN DEFAULT FALSE,
                                              notify_customer BOOLEAN DEFAULT FALSE,
                                              CONSTRAINT fk_temperature_id FOREIGN KEY (temperature_id) REFERENCES temperature_range (id)
);

CREATE TABLE IF NOT EXISTS execution_plan_action (
                                                     id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('execution_plan_action_id_seq'),
                                                     action_name VARCHAR(255) DEFAULT NULL,
                                                     is_executed BOOLEAN DEFAULT FALSE,
                                                     is_notify BOOLEAN DEFAULT FALSE,
                                                     execution_plan_id BIGINT DEFAULT NULL

);

