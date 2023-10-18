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

--
-- Name: calcular_valor_unitario(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.calcular_valor_unitario() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  IF NEW.quantidade > 10 THEN
    NEW.valor_unitario = (SELECT preco FROM equipamento as eq, venda_equipamento as ve WHERE eq.id = NEW.id_equipamento) - 0.1 * (SELECT preco FROM equipamento as eq, venda_equipamento as ve WHERE eq.id = NEW.id_equipamento);
  ELSE
    NEW.valor_unitario = (SELECT preco FROM equipamento as eq, venda_equipamento as ve WHERE eq.id = NEW.id_equipamento);
  END IF;
  RETURN NEW;
END;
$$;


ALTER FUNCTION public.calcular_valor_unitario() OWNER TO postgres;

--
-- Name: calcular_valor_venda_equipamento(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.calcular_valor_venda_equipamento() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
    UPDATE Venda_Equipamento
    SET valor_unitario = Venda_Equipamento.quantidade * Equipamento.preco
    FROM Equipamento
    WHERE Venda_Equipamento.id_equipamento = Equipamento.id AND Venda_Equipamento.id_venda = NEW.id_venda;

    RETURN NEW;
END;
$$;


ALTER FUNCTION public.calcular_valor_venda_equipamento() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cliente; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cliente (
    id bigint NOT NULL,
    nome character varying(128) NOT NULL,
    cpf character varying(14) NOT NULL,
    telefone character varying(14) NOT NULL,
    data_cadastro date DEFAULT CURRENT_DATE,
    endereco character varying(256) NOT NULL
);


ALTER TABLE public.cliente OWNER TO postgres;

--
-- Name: cliente_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.cliente_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cliente_id_seq OWNER TO postgres;

--
-- Name: cliente_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.cliente_id_seq OWNED BY public.cliente.id;


--
-- Name: equipamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.equipamento (
    id bigint NOT NULL,
    nome character varying(128) NOT NULL,
    estoque integer NOT NULL,
    preco numeric(15,2) NOT NULL,
    num_serie character varying(15) NOT NULL,
    data_cadastro date DEFAULT CURRENT_DATE,
    id_local bigint NOT NULL,
    vendidos integer DEFAULT 0,
    id_responsavel bigint NOT NULL
);


ALTER TABLE public.equipamento OWNER TO postgres;

--
-- Name: equipamento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.equipamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.equipamento_id_seq OWNER TO postgres;

--
-- Name: equipamento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.equipamento_id_seq OWNED BY public.equipamento.id;


--
-- Name: funcionario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcionario (
    id bigint NOT NULL,
    is_responsavel boolean DEFAULT false,
    nome character varying(128) NOT NULL,
    cpf character varying(14) NOT NULL,
    login character varying(128) NOT NULL,
    senha character varying(128) NOT NULL,
    telefone character varying(14) NOT NULL,
    endereco character varying(256) NOT NULL,
    data_cadastro date DEFAULT CURRENT_DATE,
    total_vendas numeric(15,2) DEFAULT 0.0
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
    nome character varying(128) NOT NULL,
    compartimento character varying(128) NOT NULL,
    data_cadastro date DEFAULT CURRENT_DATE
);


ALTER TABLE public.local OWNER TO postgres;

--
-- Name: local_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.local_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.local_id_seq OWNER TO postgres;

--
-- Name: local_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.local_id_seq OWNED BY public.local.id;


--
-- Name: nomes_responsaveis; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.nomes_responsaveis AS
 SELECT f.nome
   FROM public.funcionario f
  WHERE (f.is_responsavel = true);


ALTER TABLE public.nomes_responsaveis OWNER TO postgres;

--
-- Name: venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.venda (
    id bigint NOT NULL,
    status character varying(128) DEFAULT 'Em Andamento'::character varying,
    valor_total numeric(15,2) DEFAULT 0,
    data_cadastro date DEFAULT CURRENT_DATE,
    id_cliente bigint NOT NULL,
    id_funcionario bigint NOT NULL
);


ALTER TABLE public.venda OWNER TO postgres;

--
-- Name: venda_equipamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.venda_equipamento (
    id_venda bigint NOT NULL,
    id_equipamento bigint NOT NULL,
    quantidade integer NOT NULL,
    valor_unitario numeric(15,2) DEFAULT NULL::numeric
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
-- Name: vendas_view; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.vendas_view AS
 SELECT v.id,
    v.status,
    v.valor_total,
    v.data_cadastro,
    v.id_cliente,
    v.id_funcionario,
    ve.id_equipamento,
    ve.quantidade,
    ve.valor_unitario
   FROM (public.venda v
     JOIN public.venda_equipamento ve ON ((v.id = ve.id_venda)));


ALTER TABLE public.vendas_view OWNER TO postgres;

--
-- Name: cliente id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cliente ALTER COLUMN id SET DEFAULT nextval('public.cliente_id_seq'::regclass);


--
-- Name: equipamento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipamento ALTER COLUMN id SET DEFAULT nextval('public.equipamento_id_seq'::regclass);


--
-- Name: funcionario id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcionario ALTER COLUMN id SET DEFAULT nextval('public.funcionario_id_seq'::regclass);


--
-- Name: local id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.local ALTER COLUMN id SET DEFAULT nextval('public.local_id_seq'::regclass);


--
-- Name: venda id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda ALTER COLUMN id SET DEFAULT nextval('public.venda_id_seq'::regclass);


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cliente (id, nome, cpf, telefone, data_cadastro, endereco) FROM stdin;
2	Caio Anderson	124.760.812-47	(84)99145-3210	2023-10-17	Rua Jucurutu, 1
1	João Pedro	018.447.456-21	(84)99145-3210	2023-10-17	Rua Longa, 120
3	Angélica	123.123.123-12	(84)98765-4321	2023-10-18	Rua da Angélica, 555
\.


--
-- Data for Name: equipamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.equipamento (id, nome, estoque, preco, num_serie, data_cadastro, id_local, vendidos, id_responsavel) FROM stdin;
3	Headset	22	90.00	PQRS84PQRS-4745	2023-10-17	2	0	1
4	Mouse	40	67.80	LIWDJEI12O-8SK2	2023-10-18	3	0	1
6	Cadeira Gamer	10	500.78	LADAIWJ219-2192	2023-10-18	4	0	2
10	Monitor	10	585.00	KDJWLI128L-2193	2023-10-18	2	0	1
11	Geladeira Gamer	1	1000.00	KDJAWLIJKK-9123	2023-10-18	6	0	1
12	Equipamento	12	120.00	LDJAWIL1S2-319K	2023-10-18	2	0	1
\.


--
-- Data for Name: funcionario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.funcionario (id, is_responsavel, nome, cpf, login, senha, telefone, endereco, data_cadastro, total_vendas) FROM stdin;
1	t	Kanalense	017.246.785-71	kanalense	kanalense123	(84)99132-9467	Avenida Rio Branco, 400	2023-10-17	0.00
2	t	Toinho	489.456.174-94	toinho	toinho123	(84)99176-3320	Avenida Rio Branco, 444	2023-10-17	0.00
3	f	Marcos	462.761.842-32	marcao	marcao123	(84)99174-3320	Rua dos Lobos, 0	2023-10-17	0.00
\.


--
-- Data for Name: local; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.local (id, nome, compartimento, data_cadastro) FROM stdin;
2	Casa do Kanalaense	Quarto	2023-10-17
3	Casa do Kanalense	Depósito	2023-10-18
4	Casa do Toinho	Sala	2023-10-18
5	Casa do Toinho	Quarto	2023-10-18
6	Casa do Kanalense	Despensa	2023-10-18
\.


--
-- Data for Name: venda; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.venda (id, status, valor_total, data_cadastro, id_cliente, id_funcionario) FROM stdin;
\.


--
-- Data for Name: venda_equipamento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.venda_equipamento (id_venda, id_equipamento, quantidade, valor_unitario) FROM stdin;
\.


--
-- Name: cliente_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cliente_id_seq', 3, true);


--
-- Name: equipamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.equipamento_id_seq', 12, true);


--
-- Name: funcionario_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.funcionario_id_seq', 5, true);


--
-- Name: local_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.local_id_seq', 6, true);


--
-- Name: venda_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.venda_id_seq', 11, true);


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
-- Name: venda_equipamento calcular_valor_venda_equipamento; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER calcular_valor_venda_equipamento AFTER INSERT ON public.venda_equipamento FOR EACH ROW EXECUTE FUNCTION public.calcular_valor_venda_equipamento();


--
-- Name: equipamento equipamento_id_local_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipamento
    ADD CONSTRAINT equipamento_id_local_fkey FOREIGN KEY (id_local) REFERENCES public.local(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: equipamento equipamento_id_responsavel_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.equipamento
    ADD CONSTRAINT equipamento_id_responsavel_fkey FOREIGN KEY (id_responsavel) REFERENCES public.funcionario(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: venda_equipamento venda_equipamento_id_equipamento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda_equipamento
    ADD CONSTRAINT venda_equipamento_id_equipamento_fkey FOREIGN KEY (id_equipamento) REFERENCES public.equipamento(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: venda_equipamento venda_equipamento_id_venda_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda_equipamento
    ADD CONSTRAINT venda_equipamento_id_venda_fkey FOREIGN KEY (id_venda) REFERENCES public.venda(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: venda venda_id_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: venda venda_id_funcionario_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.venda
    ADD CONSTRAINT venda_id_funcionario_fkey FOREIGN KEY (id_funcionario) REFERENCES public.funcionario(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

