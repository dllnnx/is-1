CREATE TABLE import_operation (
    id SERIAL PRIMARY KEY,
    status VARCHAR(20) NOT NULL,
    user_role VARCHAR(20) NOT NULL,
    added_count INTEGER,
    error_message TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

