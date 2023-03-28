
CREATE TABLE genres (
    id VARCHAR(32) NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    action BOOLEAN NOT NULL DEFAULT TRUE,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    deleted_at DATETIME(6) NULL
);

CREATE TABLE genres_categories (
    genre_id VARCHAR(32) NOT NULL,
    category_id VARCHAR(32) NOT NULL,
    CONSTRAINT idx_genres_categories UNIQUE (genre_id, category_id),
    CONSTRAINT idx_genre_id FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE CASCADE,
    CONSTRAINT idx_category_id FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);