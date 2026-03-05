CREATE SCHEMA IF NOT EXISTS wedding;

CREATE TABLE wedding.registration_code
(
    code            CHAR(10) NOT NULL,
    activated       BOOLEAN  NOT NULL DEFAULT FALSE,
    event_token     UUID     NOT NULL,
    activation_date TIMESTAMP,
    CONSTRAINT idx_registration_code_primary PRIMARY KEY (code)
);

CREATE TABLE wedding.guest
(
    registration_token UUID         NOT NULL,
    first_name         VARCHAR(100) NOT NULL,
    last_name          VARCHAR(100) NOT NULL,
    terms              BOOLEAN      NOT NULL,
    marketing          BOOLEAN      NOT NULL,
    updated            TIMESTAMP    NOT NULL,
    CONSTRAINT idx_guest_primary PRIMARY KEY (registration_token)
);

CREATE TABLE wedding.event
(
    id            UUID         NOT NULL,
    date          TIMESTAMP    NOT NULL,
    venue_name    VARCHAR(255) NOT NULL,
    venue_address VARCHAR(255) NOT NULL,
    schedule_text VARCHAR(255) NOT NULL,
    dress_code    VARCHAR(50)  NOT NULL,
    extra_info    VARCHAR(500),
    CONSTRAINT idx_event_primary PRIMARY KEY (id)
);

CREATE TABLE wedding.contact_details
(
    registration_token UUID         NOT NULL,
    phone              VARCHAR(16)  NOT NULL,
    phone_confirmed    BOOLEAN      NOT NULL DEFAULT false,
    email              VARCHAR(254) NOT NULL,
    email_confirmed    BOOLEAN      NOT NULL DEFAULT false,
    CONSTRAINT idx_contact_details_primary PRIMARY KEY (registration_token)
);

CREATE TABLE wedding.contact_confirmation
(
    registration_token UUID        NOT NULL,
    link_sent          TIMESTAMP   NOT NULL,
    contact_type       VARCHAR(10) NOT NULL,
    CONSTRAINT idx_contact_confirmation_primary PRIMARY KEY (registration_token)
);