CREATE TABLE orders
(
    order_id UUID PRIMARY KEY,
    vehicle_id UUID NOT NULL,
    customer_id VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
);
