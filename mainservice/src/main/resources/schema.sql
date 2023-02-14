CREATE TABLE IF NOT EXISTS users
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name              VARCHAR(320) NOT NULL,
    email             VARCHAR(100) NOT NULL UNIQUE,
    CONSTRAINT   pk_user              PRIMARY KEY (id),
    CONSTRAINT   name_is_blank        CHECK (name NOT LIKE ' ' AND name NOT LIKE ''),
    CONSTRAINT   email_is_not_correct CHECK (email LIKE '%@%')
);

/*CREATE TABLE IF NOT EXISTS events
(
    id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    annotation        VARCHAR(320) NOT NULL,
    category_id       BIGINT NOT NULL,
    initiator_id      BIGINT NOT NULL,
    description       VARCHAR(320) NOT NULL,
    eventDate         TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    paid              BOOLEAN NOT NULL,
    participantLimit  BIGINT NOT NULL,
    lat               FLOAT NOT NULL,
    lon               FLOAT NOT NULL,
    publishedOn       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    requestModeration BOOLEAN NOT NULL,
    state             VARCHAR(320) NOT NULL,
    title             VARCHAR(320) NOT NULL,
    views             BIGINT NOT NULL
);*/
