-- Table: public.chat

-- DROP TABLE IF EXISTS public.chat;

CREATE TABLE IF NOT EXISTS public.chat
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    CONSTRAINT chat_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.chat
    OWNER to postgres;

-- Table: public.chat_user

-- DROP TABLE IF EXISTS public.chat_user;

CREATE TABLE IF NOT EXISTS public.chat_user
(
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT chat_user_pkey PRIMARY KEY (user_id, user_name),
    CONSTRAINT user_id_unique UNIQUE (user_id),
    CONSTRAINT user_name_unique UNIQUE (user_name)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.chat_user
    OWNER to postgres;

-- Table: public.message

-- DROP TABLE IF EXISTS public.message;

CREATE TABLE IF NOT EXISTS public.message
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    chat_id integer,
    text character varying COLLATE pg_catalog."default" NOT NULL,
    is_broadcast boolean NOT NULL,
    sender_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT message_pkey PRIMARY KEY (id),
    CONSTRAINT message_chat_id_fkey FOREIGN KEY (chat_id)
    REFERENCES public.chat (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID,
    CONSTRAINT message_sender_id_fkey FOREIGN KEY (sender_id)
    REFERENCES public.chat_user (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.message
    OWNER to postgres;

-- Table: public.users_in_chat

-- DROP TABLE IF EXISTS public.users_in_chat;

CREATE TABLE IF NOT EXISTS public.users_in_chat
(
    chat_id integer NOT NULL,
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_in_chat_pkey PRIMARY KEY (chat_id, user_id),
    CONSTRAINT users_in_chat_chat_id_fkey FOREIGN KEY (chat_id)
    REFERENCES public.chat (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT users_in_chat_user_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.chat_user (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users_in_chat
    OWNER to postgres;