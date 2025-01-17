CREATE SEQUENCE IF NOT EXISTS towar_id_seq;
ALTER TABLE towar
    ALTER COLUMN id SET NOT NULL;
ALTER TABLE towar
    ALTER COLUMN id SET DEFAULT nextval('towar_id_seq');

ALTER SEQUENCE towar_id_seq OWNED BY towar.id;