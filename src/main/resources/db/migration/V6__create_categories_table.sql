CREATE TABLE categories
(
    id              NUMBER PRIMARY KEY,
    name            VARCHAR2(255) NOT NULL,
    image           VARCHAR2(255),
    description     VARCHAR2(255),
    active          NUMBER(1) DEFAULT 1 NOT NULL,

    created_at      TIMESTAMP,
    create_uid     NUMBER,
    updated_at      TIMESTAMP,
    update_uid     NUMBER,
    is_deleted      NUMBER(1) DEFAULT 0 NOT NULL,
    deleted_at      TIMESTAMP,
    deleted_uid     NUMBER,
    deleted_reason  VARCHAR2(500)
);