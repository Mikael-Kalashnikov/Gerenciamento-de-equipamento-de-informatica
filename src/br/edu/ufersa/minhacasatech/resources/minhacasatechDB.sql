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
    endereco bigint,
    telefone character varying(14)
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
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.endereco (
    id bigint NOT NULL,
    rua character varying(50) NOT NULL,
    numero integer
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- Name: endereco_id_endereco_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.endereco_id_endereco_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.endereco_id_endereco_seq OWNER TO postgres;

--
-- Name: endereco_id_endereco_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.endereco_id_endereco_seq OWNED BY public.endereco.id;


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
    id_usuario bigint,
    salario numeric(6,2)
);


ALTER TABLE public.funcionario OWNER TO postgres;

--
-- Name: funcionario_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.funcionario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.funcionario_id_seq OWNER TO postgres;

--
-- Name: funcionario_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.funcionario_id_seq OWNED BY public.funcionario.id;


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
-- Name: responsavel; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.responsavel (
    id bigint NOT NULL,
    id_usuario bigint
);


ALTER TABLE public.responsavel OWNER TO postgres;

--
-- Name: responsavel_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.responsavel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.responsavel_id_seq OWNER TO postgres;

--
-- Name: responsavel_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.responsavel_id_seq OWNED BY public.responsavel.id;


--
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    nome character varying(50),
    login character varying(50),
    senha character varying(50),
    telefone character varying(50),
    data_cadastro date DEFAULT CURRENT_DATE,
    tipo character varying(15),
    endereco bigint
);


ALTER TABLE public.usuario OWNER TO postgres;

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

ALTER SEQUENCE public.usuario_id_seq OWNED BY public.usuario.id;


--
-- Name: venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.venda (
    id bigint NOT NULL,
    cliente bigint,
    equipamento bigint,
    data_venda date,
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
-- Name: view_funcionarios; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.view_funcionarios AS
 SELECT u.id,
    u.nome,
    u.login,
    u.senha,
    u.telefone,
    u.data_cadastro,
    u.tipo
   FROM (public.usuario u
     JOIN public.funcionario f ON ((u.id = f.id_usuario)));


ALTER TABLE public.view_funcionarios OWNER TO postgres;

--
-- Name: view_responsaveis; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.view_responsaveis AS
 SELECT u.id,
    u.nome,
    u.login,
    u.senha,
    u.telefone,
    u.data_cadastro,
    u.tipo
   FROM (public.usuario u
     JOIN public.responsavel r ON ((u.id = r.id_usuario)));


ALTER TABLE public.view_responsaveis OWNER TO postgres;

--
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_cliente_seq'::regclass);


--
-- Name: endereco id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco ALTER COLUMN id SET DEFAULT nextval('public.endereco_id_endereco_seq'::regclass);


--
-- Name: equipamento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipamento ALTER COLUMN id SET DEFAULT nextval('public.equipamento_id_equipamento_seq'::regclass);


--
-- Name: funcionario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario ALTER COLUMN id SET DEFAULT nextval('public.funcionario_id_seq'::regclass);


--
-- Name: local id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.local ALTER COLUMN id SET DEFAULT nextval('public.local_id_local_seq'::regclass);


--
-- Name: responsavel id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.responsavel ALTER COLUMN id SET DEFAULT nextval('public.responsavel_id_seq'::regclass);


--
-- Name: usuario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id SET DEFAULT nextval('public.usuario_id_seq'::regclass);


--
-- Name: venda id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda ALTER COLUMN id SET DEFAULT nextval('public.venda_id_venda_seq'::regclass);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id, nome, cpf, endereco, telefone) FROM stdin;
1	Chiquinho	123.456.789-10	7	\N
2	Doquinha	123.456.789-10	7	\N
3	Zeca Urubu	123.456.789-10	7	\N
4	Num sei	123.456.789-10	7	\N
\.


--
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.endereco (id, rua, numero) FROM stdin;
1	Rua Engracada	1
3	Rua Capixaba	120
7	Rua Serelepe	69
\.


--
-- Data for Name: equipamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.equipamento (id, nome, numserie, quantidade, local, responsavel, preco) FROM stdin;
\.


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.funcionario (id, id_usuario, salario) FROM stdin;
\.


--
-- Data for Name: local; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.local (id, nome, nome_compartimento, data_cadastro) FROM stdin;
4	A casa	Sala	2023-10-10
5	Outra casa	Sala	2023-10-10
1	Casa do Adonso	Sala	2023-10-10
3	Casa	Quarto	2023-10-10
2	Casa do Breno	Quarto	2023-10-10
10	Mais uma casa	Garagem	2023-10-10
17	Mais uma outra casa	Depósito	2023-10-10
18	Mais uma outra casa2	Depósito	2023-10-10
16	Outro	Depósito	2023-10-10
19	ladwiwjaildj	adwlijawildj	2023-10-13
20	Casa do Adonso	Quarto	2023-10-13
21	umkk	kkk	2023-10-13
22	outra casa	quarto	2023-10-13
23	kasldj	dlaiwdj	2023-10-13
24	zeruelaessejavafxviu	ali	2023-10-13
25	teste	tstea	2023-10-13
26	testetete	aldwijawidj	2023-10-13
27	Fulano de tal	12	2023-10-13
\.


--
-- Data for Name: responsavel; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.responsavel (id, id_usuario) FROM stdin;
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id, nome, login, senha, telefone, data_cadastro, tipo, endereco) FROM stdin;
9	Afonso	afon	afo123	(84)99999-9999	2023-10-13	Responsavel	3
\.


--
-- Data for Name: venda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.venda (id, cliente, equipamento, data_venda, valor_total) FROM stdin;
\.


--
-- Name: cliente_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_cliente_seq', 4, true);


--
-- Name: endereco_id_endereco_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.endereco_id_endereco_seq', 7, true);


--
-- Name: equipamento_id_equipamento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.equipamento_id_equipamento_seq', 1, false);


--
-- Name: funcionario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.funcionario_id_seq', 1, true);


--
-- Name: local_id_local_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.local_id_local_seq', 27, true);


--
-- Name: responsavel_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.responsavel_id_seq', 2, true);


--
-- Name: usuario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.usuario_id_seq', 9, true);


--
-- Name: venda_id_venda_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.venda_id_venda_seq', 1, false);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- Name: equipamento equipamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipamento
    ADD CONSTRAINT equipamento_pkey PRIMARY KEY (id);


--
-- Name: funcionario funcionario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (id);


--
-- Name: local local_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.local
    ADD CONSTRAINT local_pkey PRIMARY KEY (id);


--
-- Name: responsavel responsavel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.responsavel
    ADD CONSTRAINT responsavel_pkey PRIMARY KEY (id);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: venda venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_pkey PRIMARY KEY (id);


--
-- Name: cliente cliente_endereco_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_endereco_fkey FOREIGN KEY (endereco) REFERENCES public.endereco(id);


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
-- Name: funcionario funcionario_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: responsavel responsavel_id_usuario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.responsavel
    ADD CONSTRAINT responsavel_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: usuario usuario_endereco_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_endereco_fkey FOREIGN KEY (endereco) REFERENCES public.endereco(id);


--
-- PostgreSQL database dump complete
--

