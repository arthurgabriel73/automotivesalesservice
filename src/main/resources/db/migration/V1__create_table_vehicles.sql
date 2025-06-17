CREATE TABLE vehicles
(
    vehicle_id UUID PRIMARY KEY,
    data       JSONB       NOT NULL,
    price      BIGINT      NOT NULL,
    status     VARCHAR(50) NOT NULL
);
