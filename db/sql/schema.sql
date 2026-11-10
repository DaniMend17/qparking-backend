CREATE TABLE public.users
(
    user_id      bigserial             NOT NULL,
    first_name   character varying(40) NOT NULL,
    last_name    character varying(40) NOT NULL,
    phone_number character varying(10) NOT NULL,
    email        character varying     NOT NULL,
    password     character varying     NOT NULL,
    credit       double precision      NOT NULL,
    platform     character varying(10) NOT NULL,
    created_at   timestamp             NOT NULL,
    updated_at   timestamp             NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (user_id),
    CONSTRAINT uniq_phone_number UNIQUE (phone_number),
    CONSTRAINT chk_platforms CHECK (platform IN ('WEB', 'MOBILE'))
);

CREATE TABLE public.parkings
(
    parking_id      bigserial              NOT NULL,
    user_id         bigserial              NOT NULL,
    parking_name    character varying(120) NOT NULL,
    commission_rate character varying(10)  NOT NULL,
    hourly_rate     decimal                NOT NULL,
    flat_rate       decimal                NOT NULL,
    latitude        decimal                NOT NULL,
    longitude       decimal                NOT NULL,
    created_at      timestamp              NOT NULL,
    updated_at      timestamp              NOT NULL,
    CONSTRAINT parkings_pk PRIMARY KEY (parking_id),
    CONSTRAINT uniq_parking_name UNIQUE (parking_name),
    CONSTRAINT uniq_user_id UNIQUE (user_id),
    CONSTRAINT chk_commission_rates CHECK (commission_rate IN ('HOURLY', 'FLAT', 'MIXED'))
);

CREATE TABLE public.parking_schedules
(
    parking_schedule_id bigserial             NOT NULL,
    parking_id          bigserial             NOT NULL,
    weekday             character varying(10) NOT NULL,
    is_open             boolean               NOT NULL,
    opening_time        time,
    closing_time        time,
    CONSTRAINT parking_schedules_pk PRIMARY KEY (parking_schedule_id)
);

CREATE TABLE public.parking_roles
(
    parking_role_id   bigserial             NOT NULL,
    parking_id        bigserial             NOT NULL,
    parking_role_name character varying(50) NOT NULL,
    commission_period character varying(10) NOT NULL,
    commission_value  decimal               NOT NULL,
    created_at        timestamp             NOT NULL,
    updated_at        timestamp             NOT NULL,
    CONSTRAINT parking_roles_pk PRIMARY KEY (parking_role_id),
    CONSTRAINT chk_commission_period CHECK (commission_period IN ('HOURLY', 'FLAT'))
);

CREATE TABLE public.parking_users
(
    parking_user_id bigserial NOT NULL,
    user_id         bigserial NOT NULL,
    parking_id      bigserial NOT NULL,
    parking_role_id bigserial NOT NULL,
    created_at      timestamp NOT NULL,
    updated_at      timestamp NOT NULL,
    CONSTRAINT parking_users_pk PRIMARY KEY (parking_user_id)
);

CREATE TABLE public.parking_role_applications
(
    parking_role_application_id bigserial             NOT NULL,
    parking_id                  bigserial             NOT NULL,
    user_id                     bigserial             NOT NULL,
    parking_role_id             bigserial             NOT NULL,
    application_status          character varying(10) NOT NULL,
    created_at                  timestamp             NOT NULL,
    updated_at                  timestamp             NOT NULL,
    CONSTRAINT parking_role_applications_pk PRIMARY KEY (parking_role_application_id),
    CONSTRAINT chk_application_status CHECK (application_status IN ('PENDING', 'ACCEPTED', 'DENIED'))
);

CREATE TABLE public.parking_access_readers
(
    parking_access_reader_id bigserial             NOT NULL,
    parking_id               bigserial             NOT NULL,
    access_name              character varying(25) NOT NULL,
    is_entry                 boolean               NOT NULL,
    is_active                boolean               NOT NULL,
    created_at               timestamp             NOT NULL,
    updated_at               timestamp             NOT NULL,
    CONSTRAINT parking_access_readers_pk PRIMARY KEY (parking_access_reader_id)
);

CREATE TABLE public.parking_transactions
(
    parking_transaction_id bigserial        NOT NULL,
    parking_id             bigserial,
    user_id                bigserial        NOT NULL,
    transaction_value      double precision NOT NULL,
    registered_at          timestamp        NOT NULL,
    CONSTRAINT parking_transactions_pk PRIMARY KEY (parking_transaction_id)
);

CREATE TABLE public.user_parking_accesses_history
(
    user_parking_access_history_id bigserial             NOT NULL,
    user_id                        bigserial,
    parking_transaction_id         bigserial,
    parking_entry_name             character varying(25) NOT NULL,
    parking_exit_name              character varying(25),
    first_scan_time                timestamp             NOT NULL,
    last_scan_time                 timestamp,
    access_duration                integer,
    registered_at                  timestamp,
    CONSTRAINT uniq_parking_transaction_id UNIQUE (parking_transaction_id)
);

CREATE TABLE public.exit_qr_codes
(
    exit_qr_code_id bigserial NOT NULL,
    user_id         bigserial NOT NULL,
    parking_id      bigserial NOT NULL,
    value           character varying(15),
    created_at      timestamp,
    CONSTRAINT uniq_user_id_qr_code UNIQUE (user_id),
    CONSTRAINT exit_qr_codes_pk PRIMARY KEY (exit_qr_code_id)
);

CREATE TABLE public.active_parking_entries
(
    active_parking_entry_id  bigserial NOT NULL,
    parking_access_reader_id bigserial NOT NULL,
    user_id                  bigserial NOT NULL,
    scan_time                timestamp NOT NULL,
    CONSTRAINT uniq_user_id_active_entry UNIQUE (user_id),
    CONSTRAINT active_parking_entries_pk PRIMARY KEY (active_parking_entry_id)
);

CREATE TABLE public.subscriptions
(
    subscription_id       bigserial             NOT NULL,
    subscription_name     character varying(15) NOT NULL,
    subscription_platform character varying(15) NOT NULL,
    duration_days         smallint              NOT NULL,
    CONSTRAINT subscriptions_pk PRIMARY KEY (subscription_id),
    CONSTRAINT chk_platforms CHECK (subscription_platform IN ('web', 'mobile'))
);

CREATE TABLE public.subscription_benefits
(
    subscription_benefit_id bigserial             NOT NULL,
    subscription_id         bigserial             NOT NULL,
    benefit                 character varying(80) NOT NULL,
    CONSTRAINT subscription_benefits_pk PRIMARY KEY (subscription_benefit_id)
);

CREATE TABLE public.user_subscriptions
(
    user_subscription_id bigserial NOT NULL,
    user_id              bigserial NOT NULL,
    subscription_id      bigserial NOT NULL,
    start_date           timestamp NOT NULL,
    end_date             timestamp NOT NULL,
    is_active            boolean   NOT NULL,
    CONSTRAINT user_subscriptions_pk PRIMARY KEY (user_subscription_id)
);

ALTER TABLE public.parkings
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.parking_schedules
    ADD CONSTRAINT fk_parking_id FOREIGN KEY (parking_id)
        REFERENCES public.parkings (parking_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.parking_roles
    ADD CONSTRAINT fk_parking_id FOREIGN KEY (parking_id)
        REFERENCES public.parkings (parking_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.parking_users
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.parking_users
    ADD CONSTRAINT fk_parking_id FOREIGN KEY (parking_id)
        REFERENCES public.parkings (parking_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.parking_users
    ADD CONSTRAINT fk_parking_role_id FOREIGN KEY (parking_role_id)
        REFERENCES public.parking_roles (parking_role_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.parking_role_applications
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.parking_role_applications
    ADD CONSTRAINT fk_parking_id FOREIGN KEY (parking_id)
        REFERENCES public.parkings (parking_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.parking_role_applications
    ADD CONSTRAINT fk_parking_role_id FOREIGN KEY (parking_role_id)
        REFERENCES public.parking_roles (parking_role_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.parking_access_readers
    ADD CONSTRAINT fk_parking_id FOREIGN KEY (parking_id)
        REFERENCES public.parkings (parking_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.parking_transactions
    ADD CONSTRAINT fk_parking_id FOREIGN KEY (parking_id)
        REFERENCES public.parkings (parking_id) MATCH SIMPLE
        ON DELETE SET NULL ON UPDATE NO ACTION;

ALTER TABLE public.user_parking_accesses_history
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON DELETE SET NULL ON UPDATE NO ACTION;

ALTER TABLE public.user_parking_accesses_history
    ADD CONSTRAINT fk_parking_transaction_id FOREIGN KEY (parking_transaction_id)
        REFERENCES public.parking_transactions (parking_transaction_id) MATCH SIMPLE
        ON DELETE SET NULL ON UPDATE NO ACTION;

ALTER TABLE public.exit_qr_codes
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE public.exit_qr_codes
    ADD CONSTRAINT fk_parking_id FOREIGN KEY (parking_id)
        REFERENCES public.parkings (parking_id) MATCH SIMPLE
        ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE public.active_parking_entries
    ADD CONSTRAINT fk_parking_access_reader_id FOREIGN KEY (parking_access_reader_id)
        REFERENCES public.parking_access_readers (parking_access_reader_id) MATCH SIMPLE
        ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE public.active_parking_entries
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE public.subscription_benefits
    ADD CONSTRAINT fk_subscription_id FOREIGN KEY (subscription_id)
        REFERENCES public.subscriptions (subscription_id) MATCH SIMPLE
        ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE public.user_subscriptions
    ADD CONSTRAINT fk_subscription_id FOREIGN KEY (subscription_id)
        REFERENCES public.subscriptions (subscription_id) MATCH SIMPLE
        ON DELETE NO ACTION ON UPDATE NO ACTION;

