-- Table: public.device

DROP TABLE IF EXISTS public.device;

CREATE TABLE IF NOT EXISTS public.device
(
    device_id uuid NOT NULL DEFAULT uuid_generate_v4(),
    device_name character varying COLLATE pg_catalog."default" NOT NULL,
    search_key character varying COLLATE pg_catalog."default" NOT NULL,
    expiry_date date,
    manufacturing_date date,
    status character varying COLLATE pg_catalog."default",
    price integer,
    buy_date date,
    warranty_month integer,
    category_id integer,
    CONSTRAINT device_pkey PRIMARY KEY (device_id),
    CONSTRAINT fk_category FOREIGN KEY (category_id)
        REFERENCES public.category (category_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.device
    OWNER to postgres;