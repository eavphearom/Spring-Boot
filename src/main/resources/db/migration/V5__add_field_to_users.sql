ALTER TABLE users ADD (
    create_uid NUMBER(19),
    update_uid NUMBER(19),
    is_deleted NUMBER(1) DEFAULT 0 NOT NULL,
    deleted_at TIMESTAMP,
    deleted_uid NUMBER(19),
    deleted_reason VARCHAR2(255)
);