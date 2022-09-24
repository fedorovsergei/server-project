CREATE TABLE WEATHER_HISTORY
(
    ID            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TIME          TIMESTAMP NOT NULL UNIQUE,
    VALUE_NOW     VARCHAR   NOT NULL,
    VALUE_FEELING VARCHAR
);