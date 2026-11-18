CREATE TABLE users
(
    user_id      bigserial             NOT NULL,
    first_name   character varying(40) NOT NULL,
    last_name    character varying(40) NOT NULL,
    phone_number character varying(10) NOT NULL,
    email        character varying     NOT NULL,
    password     character varying     NOT NULL,
    credit       numeric(12, 2)        NOT NULL,
    platform     character varying(10) NOT NULL,
    created_at   timestamp             NOT NULL,
    updated_at   timestamp             NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (user_id),
    CONSTRAINT uniq_users_phone_number UNIQUE (phone_number),
    CONSTRAINT chk_users_platform CHECK (platform IN ('WEB', 'MOBILE'))
);

CREATE TABLE parkings
(
    parking_id      bigserial              NOT NULL,
    user_id         bigint                 NOT NULL,
    parking_name    character varying(120) NOT NULL,
    commission_rate character varying(10)  NOT NULL,
    hourly_rate     numeric(10, 2),
    flat_rate       numeric(10, 2),
    latitude        double precision       NOT NULL,
    longitude       double precision       NOT NULL,
    created_at      timestamp              NOT NULL,
    updated_at      timestamp              NOT NULL,
    CONSTRAINT parkings_pk PRIMARY KEY (parking_id),
    CONSTRAINT uniq_parkings_name UNIQUE (parking_name),
    CONSTRAINT uniq_parkings_user_id UNIQUE (user_id),
    CONSTRAINT chk_parkings_commission_rate CHECK (commission_rate IN ('HOURLY', 'FLAT', 'MIXED')),
    CONSTRAINT fk_parkings_user_id FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE parking_schedules
(
    parking_schedule_id bigserial             NOT NULL,
    parking_id          bigint                NOT NULL,
    weekday             character varying(10) NOT NULL,
    is_open             boolean               NOT NULL,
    opening_time        time,
    closing_time        time,
    CONSTRAINT parking_schedules_pk PRIMARY KEY (parking_schedule_id),
    CONSTRAINT fk_parking_schedules_parking_id FOREIGN KEY (parking_id)
        REFERENCES parkings (parking_id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE parking_roles
(
    parking_role_id   bigserial             NOT NULL,
    parking_id        bigint                NOT NULL,
    parking_role_name character varying(50) NOT NULL,
    commission_period character varying(10) NOT NULL,
    commission_value  numeric(10, 2)        NOT NULL,
    created_at        timestamp             NOT NULL,
    updated_at        timestamp             NOT NULL,
    CONSTRAINT parking_roles_pk PRIMARY KEY (parking_role_id),
    CONSTRAINT chk_parking_roles_commission_period CHECK (commission_period IN ('HOURLY', 'FLAT')),
    CONSTRAINT fk_parking_roles_parking_id FOREIGN KEY (parking_id)
        REFERENCES parkings (parking_id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE parking_users
(
    parking_user_id bigserial NOT NULL,
    user_id         bigint    NOT NULL,
    parking_id      bigint    NOT NULL,
    parking_role_id bigint    NOT NULL,
    created_at      timestamp NOT NULL,
    updated_at      timestamp NOT NULL,
    CONSTRAINT parking_users_pk PRIMARY KEY (parking_user_id),
    CONSTRAINT fk_parking_users_user_id FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_parking_users_parking_id FOREIGN KEY (parking_id)
        REFERENCES parkings (parking_id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_parking_users_role_id FOREIGN KEY (parking_role_id)
        REFERENCES parking_roles (parking_role_id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE parking_role_applications
(
    parking_role_application_id bigserial             NOT NULL,
    parking_id                  bigint                NOT NULL,
    user_id                     bigint                NOT NULL,
    parking_role_id             bigint                NOT NULL,
    application_status          character varying(10) NOT NULL,
    created_at                  timestamp             NOT NULL,
    updated_at                  timestamp             NOT NULL,
    CONSTRAINT parking_role_applications_pk PRIMARY KEY (parking_role_application_id),
    CONSTRAINT chk_parking_role_applications_status CHECK (application_status IN ('PENDING', 'ACCEPTED', 'DENIED')),
    CONSTRAINT fk_role_applications_user_id FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_role_applications_parking_id FOREIGN KEY (parking_id)
        REFERENCES parkings (parking_id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_role_applications_role_id FOREIGN KEY (parking_role_id)
        REFERENCES parking_roles (parking_role_id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE parking_access_readers
(
    parking_access_reader_id bigserial             NOT NULL,
    parking_id               bigint                NOT NULL,
    access_name              character varying(25) NOT NULL,
    is_entry                 boolean               NOT NULL,
    is_active                boolean               NOT NULL,
    created_at               timestamp             NOT NULL,
    updated_at               timestamp             NOT NULL,
    CONSTRAINT parking_access_readers_pk PRIMARY KEY (parking_access_reader_id),
    CONSTRAINT fk_access_readers_parking_id FOREIGN KEY (parking_id)
        REFERENCES parkings (parking_id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE parking_transactions
(
    parking_transaction_id bigserial      NOT NULL,
    parking_id             bigint,
    user_id                bigint         NOT NULL,
    transaction_value      numeric(12, 2) NOT NULL,
    registered_at          timestamp      NOT NULL,
    CONSTRAINT parking_transactions_pk PRIMARY KEY (parking_transaction_id),
    CONSTRAINT fk_transactions_parking_id FOREIGN KEY (parking_id)
        REFERENCES parkings (parking_id) ON DELETE SET NULL ON UPDATE NO ACTION,
    CONSTRAINT fk_transactions_user_id FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE user_parking_accesses_history
(
    user_parking_access_history_id bigserial             NOT NULL,
    user_id                        bigint,
    parking_transaction_id         bigint,
    parking_entry_name             character varying(25) NOT NULL,
    parking_exit_name              character varying(25),
    first_scan_time                timestamp             NOT NULL,
    last_scan_time                 timestamp,
    access_duration                integer,
    registered_at                  timestamp,
    CONSTRAINT user_parking_accesses_history_pk PRIMARY KEY (user_parking_access_history_id),
    CONSTRAINT uniq_access_history_transaction_id UNIQUE (parking_transaction_id),
    CONSTRAINT fk_access_history_user_id FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE SET NULL ON UPDATE NO ACTION,
    CONSTRAINT fk_access_history_transaction_id FOREIGN KEY (parking_transaction_id)
        REFERENCES parking_transactions (parking_transaction_id) ON DELETE SET NULL ON UPDATE NO ACTION
);

CREATE TABLE exit_qr_codes
(
    exit_qr_code_id bigserial NOT NULL,
    user_id         bigint    NOT NULL,
    parking_id      bigint    NOT NULL,
    value           character varying(15),
    created_at      timestamp,
    CONSTRAINT exit_qr_codes_pk PRIMARY KEY (exit_qr_code_id),
    CONSTRAINT uniq_exit_qr_codes_user_id UNIQUE (user_id),
    CONSTRAINT fk_exit_qr_codes_user_id FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_exit_qr_codes_parking_id FOREIGN KEY (parking_id)
        REFERENCES parkings (parking_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE active_parking_entries
(
    active_parking_entry_id  bigserial NOT NULL,
    parking_access_reader_id bigint    NOT NULL,
    user_id                  bigint    NOT NULL,
    scan_time                timestamp NOT NULL,
    CONSTRAINT active_parking_entries_pk PRIMARY KEY (active_parking_entry_id),
    CONSTRAINT uniq_active_parking_entries_user_id UNIQUE (user_id),
    CONSTRAINT fk_active_entries_reader_id FOREIGN KEY (parking_access_reader_id)
        REFERENCES parking_access_readers (parking_access_reader_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_active_entries_user_id FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE subscriptions
(
    subscription_id       bigserial             NOT NULL,
    subscription_name     character varying(15) NOT NULL,
    subscription_platform character varying(15) NOT NULL,
    duration_days         smallint              NOT NULL,
    CONSTRAINT subscriptions_pk PRIMARY KEY (subscription_id),
    CONSTRAINT chk_subscriptions_platform CHECK (subscription_platform IN ('WEB', 'MOBILE'))
);

CREATE TABLE subscription_benefits
(
    subscription_benefit_id bigserial             NOT NULL,
    subscription_id         bigint                NOT NULL,
    benefit                 character varying(80) NOT NULL,
    CONSTRAINT subscription_benefits_pk PRIMARY KEY (subscription_benefit_id),
    CONSTRAINT fk_subscription_benefits_subscription_id FOREIGN KEY (subscription_id)
        REFERENCES subscriptions (subscription_id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE user_subscriptions
(
    user_subscription_id bigserial NOT NULL,
    user_id              bigint    NOT NULL,
    subscription_id      bigint    NOT NULL,
    start_date           timestamp NOT NULL,
    end_date             timestamp NOT NULL,
    is_active            boolean   NOT NULL,
    CONSTRAINT user_subscriptions_pk PRIMARY KEY (user_subscription_id),
    CONSTRAINT fk_user_subscriptions_user_id FOREIGN KEY (user_id)
        REFERENCES users (user_id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_user_subscriptions_subscription_id FOREIGN KEY (subscription_id)
        REFERENCES subscriptions (subscription_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
