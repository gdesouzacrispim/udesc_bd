--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2 (Debian 15.2-1.pgdg110+1)
-- Dumped by pg_dump version 15.2 (Ubuntu 15.2-1.pgdg20.04+1)

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
-- Name: trabalho; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA trabalho;


ALTER SCHEMA trabalho OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: agencia; Type: TABLE; Schema: trabalho; Owner: postgres
--

CREATE TABLE trabalho.agencia (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    endereco character varying(100) NOT NULL,
    cnpj character varying(20) NOT NULL,
    cidade integer NOT NULL
);


ALTER TABLE trabalho.agencia OWNER TO postgres;

--
-- Name: agencia_id; Type: SEQUENCE; Schema: trabalho; Owner: postgres
--

CREATE SEQUENCE trabalho.agencia_id
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trabalho.agencia_id OWNER TO postgres;

--
-- Name: agencia_id; Type: SEQUENCE OWNED BY; Schema: trabalho; Owner: postgres
--

ALTER SEQUENCE trabalho.agencia_id OWNED BY trabalho.agencia.id;


--
-- Name: cidade; Type: TABLE; Schema: trabalho; Owner: postgres
--

CREATE TABLE trabalho.cidade (
    id integer NOT NULL,
    nome character varying(30) NOT NULL,
    uf character varying(2) NOT NULL
);


ALTER TABLE trabalho.cidade OWNER TO postgres;

--
-- Name: cidade_id; Type: SEQUENCE; Schema: trabalho; Owner: postgres
--

CREATE SEQUENCE trabalho.cidade_id
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trabalho.cidade_id OWNER TO postgres;

--
-- Name: cidade_id; Type: SEQUENCE OWNED BY; Schema: trabalho; Owner: postgres
--

ALTER SEQUENCE trabalho.cidade_id OWNED BY trabalho.cidade.id;


--
-- Name: cliente; Type: TABLE; Schema: trabalho; Owner: postgres
--

CREATE TABLE trabalho.cliente (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    telefone character varying(15) NOT NULL,
    email character varying(100) NOT NULL,
    cpf character varying(15) NOT NULL,
    endereco character varying(100) NOT NULL
);


ALTER TABLE trabalho.cliente OWNER TO postgres;

--
-- Name: cliente_id; Type: SEQUENCE; Schema: trabalho; Owner: postgres
--

CREATE SEQUENCE trabalho.cliente_id
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trabalho.cliente_id OWNER TO postgres;

--
-- Name: cliente_id; Type: SEQUENCE OWNED BY; Schema: trabalho; Owner: postgres
--

ALTER SEQUENCE trabalho.cliente_id OWNED BY trabalho.cliente.id;


--
-- Name: conta; Type: TABLE; Schema: trabalho; Owner: postgres
--

CREATE TABLE trabalho.conta (
    id integer NOT NULL,
    id_cliente integer NOT NULL,
    id_agencia integer NOT NULL,
    tipo integer NOT NULL,
    numero integer NOT NULL,
    saldo numeric(1000,2),
    senha integer
);


ALTER TABLE trabalho.conta OWNER TO postgres;

--
-- Name: conta_id; Type: SEQUENCE; Schema: trabalho; Owner: postgres
--

CREATE SEQUENCE trabalho.conta_id
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trabalho.conta_id OWNER TO postgres;

--
-- Name: conta_id; Type: SEQUENCE OWNED BY; Schema: trabalho; Owner: postgres
--

ALTER SEQUENCE trabalho.conta_id OWNED BY trabalho.conta.id;


--
-- Name: movimentacao; Type: TABLE; Schema: trabalho; Owner: postgres
--

CREATE TABLE trabalho.movimentacao (
    id_movimentacao integer NOT NULL,
    id_conta_autor integer NOT NULL,
    operacao integer NOT NULL,
    id_conta_op integer,
    id_agencia_op integer NOT NULL,
    data timestamp without time zone NOT NULL,
    valor numeric(1000,2) NOT NULL,
    descricao character varying(300) NOT NULL
);


ALTER TABLE trabalho.movimentacao OWNER TO postgres;

--
-- Name: movimentacao_id; Type: SEQUENCE; Schema: trabalho; Owner: postgres
--

CREATE SEQUENCE trabalho.movimentacao_id
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trabalho.movimentacao_id OWNER TO postgres;

--
-- Name: movimentacao_id; Type: SEQUENCE OWNED BY; Schema: trabalho; Owner: postgres
--

ALTER SEQUENCE trabalho.movimentacao_id OWNED BY trabalho.movimentacao.id_movimentacao;


--
-- Name: agencia id; Type: DEFAULT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.agencia ALTER COLUMN id SET DEFAULT nextval('trabalho.agencia_id'::regclass);


--
-- Name: cidade id; Type: DEFAULT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.cidade ALTER COLUMN id SET DEFAULT nextval('trabalho.cidade_id'::regclass);


--
-- Name: cliente id; Type: DEFAULT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.cliente ALTER COLUMN id SET DEFAULT nextval('trabalho.cliente_id'::regclass);


--
-- Name: conta id; Type: DEFAULT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.conta ALTER COLUMN id SET DEFAULT nextval('trabalho.conta_id'::regclass);


--
-- Name: movimentacao id_movimentacao; Type: DEFAULT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.movimentacao ALTER COLUMN id_movimentacao SET DEFAULT nextval('trabalho.movimentacao_id'::regclass);


--
-- Data for Name: agencia; Type: TABLE DATA; Schema: trabalho; Owner: postgres
--

COPY trabalho.agencia (id, nome, endereco, cnpj, cidade) FROM stdin;
7	Agência Central	Rua A, 123	12345678900001	1
8	Agência Norte	Rua B, 456	98765432100001	2
9	Agência Sul	rua camarão, 28	54548494979799	3
11	Agencia Arrascaeta	Rua flamengo, 45	154549879894	4
12	Itau unibanco	Avenida paulista, 489	87484784564	4
13	Agencia da ostra	Rua do limoeiro, 89	545848744848487	3
\.


--
-- Data for Name: cidade; Type: TABLE DATA; Schema: trabalho; Owner: postgres
--

COPY trabalho.cidade (id, nome, uf) FROM stdin;
1	Colatina	ES
2	Joinville	SC
3	Cercal	CE
4	São Paulo	SP
\.


--
-- Data for Name: cliente; Type: TABLE DATA; Schema: trabalho; Owner: postgres
--

COPY trabalho.cliente (id, nome, telefone, email, cpf, endereco) FROM stdin;
6	João Silva	47992072150	joao@gmail.com	123.456.789-00	Rua J, 654
7	Maria Souza	47999999999	maria@gmail.com	987.654.321-00	Rua A, 640
14	Henrique Souza	11656565665	henrique@gmail.com	789.012.345-00	Rua T, 48
15	Mariana Pereira	4730308080	mariana@gmail.com	901.234.567-00	Rua J, 99
12	Ricardo Oliveira	21656565656	ricardo@gmail.com	345.678.901-00	Rua Y, 111
8	Pedro Santos	47222222222	pedro@gmail.com	456.789.123-00	Rua B, 54
18	Conta_Pagamentos	47989898989	pagamentos@gmail.com	598688997945	Rua P, 02
16	gustavo	11656565998	gustavo@gmail.com	14878965698	Rua E, 989
13	Julia Santos	21656565656	julia@gmail.com	567.890.123-00	Rua Z, 52
9	Ana Oliveira	47555555555	ana@gmail.com	654.321.987-00	Rua G, 6D
10	Lucas Pereira	47666666666	lucas@gmail.com	789.123.456-00	Rua D, 454
11	Fernanda Silva	47656565656	fernanda@gmail.com	234.567.890-00	Rua D, 454
\.


--
-- Data for Name: conta; Type: TABLE DATA; Schema: trabalho; Owner: postgres
--

COPY trabalho.conta (id, id_cliente, id_agencia, tipo, numero, saldo, senha) FROM stdin;
145	8	9	105	55666	0.00	1234
134	7	8	105	15802	0.00	1234
142	15	8	105	51698	0.00	1234
147	10	9	106	79017	0.00	1234
126	9	7	106	15829	0.00	1234
150	13	9	106	30017	0.00	1234
148	11	9	105	24262	0.00	1234
123	6	7	105	99642	0.00	1234
139	12	8	105	59859	0.00	1234
157	18	12	106	99999	0.00	9999
155	15	9	105	1010	160.00	1234
151	14	9	106	37510	14.00	1234
\.


--
-- Data for Name: movimentacao; Type: TABLE DATA; Schema: trabalho; Owner: postgres
--

COPY trabalho.movimentacao (id_movimentacao, id_conta_autor, operacao, id_conta_op, id_agencia_op, data, valor, descricao) FROM stdin;
5	155	1	\N	9	2023-04-09 00:00:00	5.00	Saque de R$ 5.0 foi realizado
6	155	1	\N	9	2023-04-09 00:00:00	75.00	Saque de R$ 75.0 foi realizado
7	155	2	155	9	2023-04-09 00:00:00	50.00	Deposito de R$ 50.0 foi realizado
8	155	1	\N	9	2023-04-09 00:00:00	35.00	Saque de R$ 35.0 foi realizado
9	155	2	155	9	2023-04-09 00:00:00	10.00	Deposito de R$ 10.0 foi realizado
10	155	1	\N	9	2023-04-18 00:00:00	1.00	Saque de R$ 1.0 foi realizado
11	155	2	155	9	2023-04-18 00:00:00	5.00	Deposito de R$ 5.0 foi realizado
12	155	1	\N	9	2023-04-18 00:00:00	3.00	Saque de R$ 3.0 foi realizado
13	155	2	155	9	2023-04-18 00:00:00	98.00	Deposito de R$ 98.0 foi realizado
14	155	1	\N	9	2023-04-18 02:45:39.827	5.00	Saque de R$ 5.0 foi realizado
15	155	4	151	9	2023-04-18 02:48:02.623	5.00	Transferência de R$ 5.0 foi realizada
16	155	4	151	9	2023-04-18 02:48:23.515	9.00	Transferência de R$ 9.0 foi realizada
\.


--
-- Name: agencia_id; Type: SEQUENCE SET; Schema: trabalho; Owner: postgres
--

SELECT pg_catalog.setval('trabalho.agencia_id', 13, true);


--
-- Name: cidade_id; Type: SEQUENCE SET; Schema: trabalho; Owner: postgres
--

SELECT pg_catalog.setval('trabalho.cidade_id', 4, true);


--
-- Name: cliente_id; Type: SEQUENCE SET; Schema: trabalho; Owner: postgres
--

SELECT pg_catalog.setval('trabalho.cliente_id', 18, true);


--
-- Name: conta_id; Type: SEQUENCE SET; Schema: trabalho; Owner: postgres
--

SELECT pg_catalog.setval('trabalho.conta_id', 157, true);


--
-- Name: movimentacao_id; Type: SEQUENCE SET; Schema: trabalho; Owner: postgres
--

SELECT pg_catalog.setval('trabalho.movimentacao_id', 16, true);


--
-- Name: agencia agencia_pkey; Type: CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.agencia
    ADD CONSTRAINT agencia_pkey PRIMARY KEY (id);


--
-- Name: cidade cidade_pkey; Type: CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id);


--
-- Name: cliente cliente_pkey; Type: CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);


--
-- Name: conta conta_pkey; Type: CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.conta
    ADD CONSTRAINT conta_pkey PRIMARY KEY (id);


--
-- Name: movimentacao movimentacao_pkey; Type: CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.movimentacao
    ADD CONSTRAINT movimentacao_pkey PRIMARY KEY (id_movimentacao);


--
-- Name: conta uk_numero; Type: CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.conta
    ADD CONSTRAINT uk_numero UNIQUE (numero);


--
-- Name: conta cont_cliente_if_fkey; Type: FK CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.conta
    ADD CONSTRAINT cont_cliente_if_fkey FOREIGN KEY (id_cliente) REFERENCES trabalho.cliente(id);


--
-- Name: conta conta_agencia_id_fkey; Type: FK CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.conta
    ADD CONSTRAINT conta_agencia_id_fkey FOREIGN KEY (id_agencia) REFERENCES trabalho.agencia(id);


--
-- Name: agencia fk_cidade; Type: FK CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.agencia
    ADD CONSTRAINT fk_cidade FOREIGN KEY (cidade) REFERENCES trabalho.cidade(id);


--
-- Name: movimentacao movimentacao_agencia_fk; Type: FK CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.movimentacao
    ADD CONSTRAINT movimentacao_agencia_fk FOREIGN KEY (id_agencia_op) REFERENCES trabalho.agencia(id);


--
-- Name: movimentacao movimentacao_conta_fk; Type: FK CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.movimentacao
    ADD CONSTRAINT movimentacao_conta_fk FOREIGN KEY (id_conta_autor) REFERENCES trabalho.conta(id);


--
-- Name: movimentacao movimentacao_conta_op_fk; Type: FK CONSTRAINT; Schema: trabalho; Owner: postgres
--

ALTER TABLE ONLY trabalho.movimentacao
    ADD CONSTRAINT movimentacao_conta_op_fk FOREIGN KEY (id_conta_op) REFERENCES trabalho.conta(id);


--
-- PostgreSQL database dump complete
--

