CREATE TABLE roles
(
    role_id     bigserial             NOT NULL,
    role_name   character varying(10) NOT NULL,
    description character varying(50) NOT NULL,
    CONSTRAINT roles_pk_ PRIMARY KEY (role_id),
    CONSTRAINT uniq_roles_role_name UNIQUE (role_name)
)