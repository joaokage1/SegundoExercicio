--Sequencia para criar o patrimonio id
CREATE SEQUENCE public.patrimonio_ntombo_seq;

ALTER SEQUENCE public.patrimonio_ntombo_seq
    OWNER TO admin;

--Sequencia para criar a marca id
CREATE SEQUENCE public.marca_marca_id_seq;

ALTER SEQUENCE public.marca_marca_id_seq
    OWNER TO admin;

--Tabela marca
CREATE TABLE IF NOT EXISTS public.marca
(
    marca_id bigint NOT NULL,
    nome text NOT NULL,
    PRIMARY KEY (marca_id)
);

ALTER TABLE public.marca
    OWNER to admin;

--Tabela patrimonio
CREATE TABLE IF NOT EXISTS public.patrimonio
(
    n_tombo bigint NOT NULL,
    nome text NOT NULL,
    descricao text,
    marca_id bigint NOT NULL,
    PRIMARY KEY (n_tombo),
    CONSTRAINT fk_marca_id FOREIGN KEY (marca_id)
        REFERENCES public.marca (marca_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

ALTER TABLE public.patrimonio
    OWNER to admin;