CREATE SCHEMA geo;

GO

CREATE SCHEMA advertisement;

GO

CREATE TABLE geo.countries
(
    id         int8,
    name       varchar(128) NOT NULL,
    CONSTRAINT country_pkey PRIMARY KEY (id)
);

GO

CREATE TABLE geo.regions
(
    id         int8,
    name       varchar(64) NOT NULL,
    country_id int8         NOT NULL,
    CONSTRAINT region_pkey PRIMARY KEY (id),
    CONSTRAINT region_country_id_fkey FOREIGN KEY (country_id) REFERENCES geo.countries (id)
);

GO

CREATE TABLE geo.cities
(
    id         int8,
    name       varchar(128) NOT NULL,
    country_id int8         NOT NULL,
    region_id  int8         NULL,
    CONSTRAINT city_pkey PRIMARY KEY (id),
    CONSTRAINT city_country_id_fkey FOREIGN KEY (country_id) REFERENCES geo.countries (id),
    CONSTRAINT city_region_id_fkey FOREIGN KEY (region_id) REFERENCES geo.regions (id)
);

GO

CREATE TABLE geo.districts
(
    id         int8,
    name       varchar(128) NOT NULL,
    city_id    int8         NOT NULL,
    CONSTRAINT district_pkey PRIMARY KEY (id),
    CONSTRAINT district_city_id_fkey FOREIGN KEY (city_id) REFERENCES geo.cities (id)
);

GO

CREATE TABLE geo.streets
(
    id          int8,
    name        varchar(128) NOT NULL,
    district_id int8         NULL,
    city_id int8         NULL,
    CONSTRAINT street_pkey PRIMARY KEY (id),
    CONSTRAINT street_district_id_fkey FOREIGN KEY (district_id) REFERENCES geo.districts (id)
);

GO

CREATE TABLE geo.addresses
(
    id           int8,
    house        varchar(32) NULL,
    floors_count int2    NULL,
    street_id    int8        NOT NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id),
    CONSTRAINT address_street_id_fkey FOREIGN KEY (street_id) REFERENCES geo.streets (id)
);


GO

CREATE TABLE advertisement.apartments
(
    id           int8,
    type         varchar(32)    NULL,
    title        varchar(512)   NULL,
    description  text           NULL,
    price        numeric(12, 2) NULL,
    rooms        varchar(10)    NULL,
    area         float4          NULL,
    area_kitchen float4          NULL,
    area_living  float4          NULL,
    floor        int4       NULL,
    link         text   NOT NULL,
    address_id   int8           NOT NULL,
    is_active    boolean        NOT NULL DEFAULT true,
    created_at   timestamp    NOT NULL DEFAULT now(),
    updated_at   timestamp    NOT NULL DEFAULT now(),
    CONSTRAINT address_pkey PRIMARY KEY (id),
    CONSTRAINT apartment_address_id_fkey FOREIGN KEY (address_id) REFERENCES geo.addresses (id)
);

