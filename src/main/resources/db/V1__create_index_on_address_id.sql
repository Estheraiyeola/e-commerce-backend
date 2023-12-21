-- src/main/resources/db/migration/V1__create_index_on_address_id.sql

-- Create an index on the id column of the Address table
CREATE INDEX idx_address_id ON address (id);
