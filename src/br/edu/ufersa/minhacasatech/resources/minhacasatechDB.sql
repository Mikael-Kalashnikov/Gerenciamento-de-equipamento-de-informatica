--
-- PostgreSQL database dump
--

-- Dumped from database version 14.9
-- Dumped by pg_dump version 14.9

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id bigint NOT NULL,
    nome character varying(50) NOT NULL,
    cpf character varying(14),
    endereco character varying(50),
    telefone character varying(14),
    data_cadastro date DEFAULT CURRENT_DATE
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_cliente_seq OWNER TO postgres;

--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_cliente_seq OWNED BY public.cliente.id;


--
-- Name: equipamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipamento (
    id bigint NOT NULL,
    nome character varying(50) NOT NULL,
    numserie character(15),
    quantidade integer,
    local bigint,
    responsavel bigint,
    preco double precision
);


ALTER TABLE public.equipamento OWNER TO postgres;

--
-- Name: equipamento_id_equipamento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.equipamento_id_equipamento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.equipamento_id_equipamento_seq OWNER TO postgres;

--
-- Name: equipamento_id_equipamento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.equipamento_id_equipamento_seq OWNED BY public.equipamento.id;


--
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionario (
    id bigint NOT NULL,
    nome character varying(50),
    login character varying(50),
    senha character varying(50),
    telefone character varying(50),
    data_cadastro date DEFAULT CURRENT_DATE,
    is_responsavel boolean DEFAULT false,
    endereco character varying(50),
    cpf character varying(14)
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- Name: local; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.local (
    id bigint NOT NULL,
    nome character varying(50) NOT NULL,
    nome_compartimento character varying(50) NOT NULL,
    data_cadastro date DEFAULT CURRENT_DATE
);


ALTER TABLE public.local OWNER TO postgres;

--
-- Name: local_id_local_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.local_id_local_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.local_id_local_seq OWNER TO postgres;

--
-- Name: local_id_local_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.local_id_local_seq OWNED BY public.local.id;


--
-- Name: usuario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_id_seq OWNER TO postgres;

--
-- Name: usuario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.funcionario.id;


--
-- Name: venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.venda (
    id bigint NOT NULL,
    cliente bigint,
    equipamento bigint,
    data_venda date DEFAULT CURRENT_DATE,
    valor_total double precision
);


ALTER TABLE public.venda OWNER TO postgres;

--
-- Name: venda_id_venda_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.venda_id_venda_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.venda_id_venda_seq OWNER TO postgres;

--
-- Name: venda_id_venda_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.venda_id_venda_seq OWNED BY public.venda.id;


--
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- Name: equipamento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipamento ALTER COLUMN id SET DEFAULT nextval('public.equipamento_id_equipamento_seq'::regclass);


--
-- Name: funcionario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- Name: local id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.local ALTER COLUMN id SET DEFAULT nextval('public.local_id_local_seq'::regclass);


--
-- Name: venda id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda ALTER COLUMN id SET DEFAULT nextval('public.venda_id_venda_seq'::regclass);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id, nome, cpf, endereco, telefone, data_cadastro) FROM stdin;
6	Afonso	555.555.555-55	Rua do Afonso, 12	(84)99820-1293	2023-10-16
5	Carlin do Gás	123.123.123-12	Rua Engraçada, 12	(84)99829-1293	2023-10-16
2	Doquinha	234.567.897-10	Rua Engraçada, 1	(84)99875-1293	2023-10-16
3	Zeca Urubu	584.291.293-04	Rua Engraçada, 1	(84)88238-4921	2023-10-16
1	Chiquinho	123.456.789-10	Rua Engraçada, 1	(84)88728-1249	2023-10-16
\.


--
-- Data for Name: equipamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.equipamento (id, nome, numserie, quantidade, local, responsavel, preco) FROM stdin;
1	Mouse	DLAIWJ0183-D12 	10	5	\N	34.9
7	Teclado	ALDJIADJEJ-123 	5	2	9	87.5
\.


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.funcionario (id, nome, login, senha, telefone, data_cadastro, is_responsavel, endereco, cpf) FROM stdin;
9	Afonso	afon	afo123	(84)99999-9999	2023-10-13	t	Rua das Goiabeiras, 120	123.456.789-10
11	Funcionario Legal	func	func123	(84)99999-9999	2023-10-14	f	Rua das Goiabeiras, 120	234.567.891-01
14	Mikael	mikael	mikael123	(84)99999-9999	2023-10-15	t	Rua das Goiabeiras, 120	345.678.910-11
15	Lucas	lucas	luca123	(84)99999-9999	2023-10-15	t	Rua das Goiabeiras, 120	456.789.101-11
16	José da Silva	josesin	jose123	(84)97482-1283	2023-10-15	f	Rua das Goiabeiras, 120	555.555.555-55
17	adwji	lijaw	dliawj	(12)34567-8901	2023-10-15	f	Rua das Goiabeiras, 120	123.123.123-12
18	Nome	ldiawj	lij	(84)12839-1298	2023-10-15	f	Rua das Goiabeiras, 120	123.124.125-12
20	Jose	jose	jose	(84)99876-5432	2023-10-16	f	Rua das Goiabeiras, 120	123.123.123-12
\.


--
-- Data for Name: local; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.local (id, nome, nome_compartimento, data_cadastro) FROM stdin;
5	Outra casa	Sala	2023-10-10
3	Casa	Quarto	2023-10-10
10	Mais uma casa	Garagem	2023-10-10
17	Mais uma outra casa	Depósito	2023-10-10
18	Mais uma outra casa2	Depósito	2023-10-10
16	Outro	Depósito	2023-10-10
4	Casa do Mikael	Sala	2023-10-10
2	Casa do Lucas	Quarto	2023-10-10
20	Casa do Afonso	Quarto	2023-10-13
36	Casa do Kanalense	Porão	2023-10-15
1	Casa do Afonso	Sala	2023-10-10
37	Casa do José	Sala	2023-10-15
38	Local	um local	2023-10-15
39	Um Local Temporário	Temp	2023-10-16
40	Outro Local Temporário	Temp2	2023-10-16
\.


--
-- Data for Name: venda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.venda (id, cliente, equipamento, data_venda, valor_total) FROM stdin;
2	1	1	2023-10-15	50
4	1	7	2023-10-15	60
\.


--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 6, true);


--
-- Name: equipamento_id_equipamento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.equipamento_id_equipamento_seq', 7, true);


--
-- Name: local_id_local_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.local_id_local_seq', 40, true);


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 21, true);


--
-- Name: venda_id_venda_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.venda_id_venda_seq', 4, true);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: equipamento equipamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipamento
    ADD CONSTRAINT equipamento_pkey PRIMARY KEY (id);


--
-- Name: local local_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.local
    ADD CONSTRAINT local_pkey PRIMARY KEY (id);


--
-- Name: funcionario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: venda venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_pkey PRIMARY KEY (id);


--
-- Name: venda fk_cliente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT fk_cliente FOREIGN KEY (cliente) REFERENCES public.cliente(id);


--
-- Name: venda fk_equipamento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT fk_equipamento FOREIGN KEY (equipamento) REFERENCES public.equipamento(id);


--
-- Name: equipamento fk_local; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipamento
    ADD CONSTRAINT fk_local FOREIGN KEY (local) REFERENCES public.local(id);


--
-- Name: equipamento fk_responsavel; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipamento
    ADD CONSTRAINT fk_responsavel FOREIGN KEY (responsavel) REFERENCES public.funcionario(id);


--
-- PostgreSQL database dump complete
--

