CREATE TABLE posts (
    id UUID PRIMARY KEY,
    content VARCHAR(255),
    image_url VARCHAR(500) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    user_id UUID NOT NULL,

    CONSTRAINT fk_posts_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
