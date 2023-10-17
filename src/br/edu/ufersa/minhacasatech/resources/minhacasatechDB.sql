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
    serial character(15),
    quantidade integer,
    local bigint,
    responsavel bigint,
    preco double precision,
    data_cadastro date DEFAULT CURRENT_DATE,
    total_vendas integer
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
-- Name: item_venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_venda (
    id bigint NOT NULL,
    equipamento bigint NOT NULL,
    quantidade integer,
    preco_unidade numeric(6,2)
);


ALTER TABLE public.item_venda OWNER TO postgres;

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
    status character varying(16) DEFAULT 'Em Processamento'::character varying,
    data_cadastro date DEFAULT CURRENT_DATE,
    funcionario bigint,
    equipamento bigint
);


ALTER TABLE public.venda OWNER TO postgres;

--
-- Name: venda_equipamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.venda_equipamento (
    id_venda integer NOT NULL,
    id_equipamento integer NOT NULL
);


ALTER TABLE public.venda_equipamento OWNER TO postgres;

--
-- Name: venda_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.venda_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.venda_id_seq OWNER TO postgres;

--
-- Name: venda_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.venda_id_seq OWNED BY public.venda.id;


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

ALTER TABLE ONLY public.venda ALTER COLUMN id SET DEFAULT nextval('public.venda_id_seq'::regclass);


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

COPY public.equipamento (id, nome, serial, quantidade, local, responsavel, preco, data_cadastro, total_vendas) FROM stdin;
7	Teclado	ALDJIADJEJ-1234	5	2	9	87.5	2023-10-16	\N
1	Mouse	DLAIWJ0183-D129	10	5	14	34.9	2023-10-16	\N
8	teste	LIJ1E2EJ1J-1298	40	20	9	145.99	2023-10-16	\N
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
-- Data for Name: item_venda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.item_venda (id, equipamento, quantidade, preco_unidade) FROM stdin;
6	7	2	55.90
6	1	1	12.90
6	3	8	10.00
7	7	1	5.55
7	1	1	6.98
7	8	2	4.45
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
41	Mais um local	Local	2023-10-16
\.


--
-- Data for Name: venda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.venda (id, cliente, status, data_cadastro, funcionario, equipamento) FROM stdin;
9	1	Aprovada	2023-10-17	14	7
8	1	Em Processamento	2023-10-17	15	1
13	1	Em Processamento	2023-10-17	11	7
14	2	Cancelada	2023-10-17	14	7
15	1	Aprovada	2023-10-17	14	7
16	1	Aprovada	2023-10-17	14	7
\.


--
-- Data for Name: venda_equipamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.venda_equipamento (id_venda, id_equipamento) FROM stdin;
1	1
1	2
1	3
2	1
2	2
2	3
\.


--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 6, true);


--
-- Name: equipamento_id_equipamento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.equipamento_id_equipamento_seq', 8, true);


--
-- Name: local_id_local_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.local_id_local_seq', 41, true);


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 21, true);


--
-- Name: venda_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.venda_id_seq', 16, true);


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
-- Name: item_venda item_venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda
    ADD CONSTRAINT item_venda_pkey PRIMARY KEY (id, equipamento);


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
-- Name: venda_equipamento venda_equipamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda_equipamento
    ADD CONSTRAINT venda_equipamento_pkey PRIMARY KEY (id_venda, id_equipamento);


--
-- Name: venda venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_pkey PRIMARY KEY (id);


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
-- Name: venda venda_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_cliente_fkey FOREIGN KEY (cliente) REFERENCES public.cliente(id);


--
-- Name: venda venda_equipamento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_equipamento_fkey FOREIGN KEY (equipamento) REFERENCES public.equipamento(id);


--
-- Name: venda venda_funcionario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_funcionario_fkey FOREIGN KEY (funcionario) REFERENCES public.funcionario(id);


--
-- PostgreSQL database dump complete
--

