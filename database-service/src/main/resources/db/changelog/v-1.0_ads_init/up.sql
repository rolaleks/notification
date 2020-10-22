CREATE SCHEMA geo;

GO

CREATE SCHEMA advertisement;

GO

CREATE TABLE geo.countries
(
    id         serial,
    name       varchar(128) NOT NULL,
    created_at timestamptz  NOT NULL DEFAULT now(),
    updated_at timestamptz  NOT NULL DEFAULT now(),
    CONSTRAINT country_pkey PRIMARY KEY (id)
);

GO

CREATE TABLE geo.regions
(
    id         serial,
    name       varchar(64) NOT NULL,
    country_id int         NOT NULL,
    created_at timestamptz NOT NULL DEFAULT now(),
    updated_at timestamptz NOT NULL DEFAULT now(),
    CONSTRAINT region_pkey PRIMARY KEY (id),
    CONSTRAINT region_country_id_fkey FOREIGN KEY (country_id) REFERENCES geo.countries (id)
);

GO

CREATE TABLE geo.cities
(
    id         serial,
    name       varchar(128) NOT NULL,
    country_id int8         NOT NULL,
    region_id  int8         NULL,
    created_at timestamptz  NOT NULL DEFAULT now(),
    updated_at timestamptz  NOT NULL DEFAULT now(),
    CONSTRAINT city_pkey PRIMARY KEY (id),
    CONSTRAINT city_country_id_fkey FOREIGN KEY (country_id) REFERENCES geo.countries (id),
    CONSTRAINT city_region_id_fkey FOREIGN KEY (region_id) REFERENCES geo.regions (id)
);

GO

CREATE TABLE geo.districts
(
    id         serial,
    name       varchar(128) NOT NULL,
    city_id    int8         NOT NULL,
    created_at timestamptz  NOT NULL DEFAULT now(),
    updated_at timestamptz  NOT NULL DEFAULT now(),
    CONSTRAINT district_pkey PRIMARY KEY (id),
    CONSTRAINT district_city_id_fkey FOREIGN KEY (city_id) REFERENCES geo.cities (id)
);

GO

CREATE TABLE geo.streets
(
    id          serial,
    name        varchar(128) NOT NULL,
    district_id int8         NULL,
    city_id     int8         NOT NULL,
    created_at  timestamptz  NOT NULL DEFAULT now(),
    updated_at  timestamptz  NOT NULL DEFAULT now(),
    CONSTRAINT street_pkey PRIMARY KEY (id),
    CONSTRAINT street_district_id_fkey FOREIGN KEY (district_id) REFERENCES geo.districts (id),
    CONSTRAINT street_city_id_fkey FOREIGN KEY (city_id) REFERENCES geo.cities (id)
);

GO

CREATE TABLE geo.addresses
(
    id           serial,
    house        varchar(32) NULL,
    floors_count smallint    NULL,
    street_id    int8        NOT NULL,
    created_at   timestamptz NOT NULL DEFAULT now(),
    updated_at   timestamptz NOT NULL DEFAULT now(),
    CONSTRAINT address_pkey PRIMARY KEY (id),
    CONSTRAINT address_street_id_fkey FOREIGN KEY (street_id) REFERENCES geo.streets (id)
);


GO

CREATE TABLE advertisement.apartments
(
    id           serial,
    type         varchar(32)    NULL,
    title        varchar(512)   NULL,
    description  text           NULL,
    price        decimal(12, 2) NULL,
    rooms        varchar(10)    NULL,
    area         float          NULL,
    area_kitchen float          NULL,
    area_living  float          NULL,
    floor        smallint       NULL,
    link         varchar(512)   NOT NULL,
    address_id   int8           NOT NULL,
    is_active    boolean        NOT NULL DEFAULT true,
    created_at   timestamptz    NOT NULL DEFAULT now(),
    updated_at   timestamptz    NOT NULL DEFAULT now(),
    CONSTRAINT address_pkey PRIMARY KEY (id),
    CONSTRAINT apartment_address_id_fkey FOREIGN KEY (address_id) REFERENCES geo.addresses (id)
);

