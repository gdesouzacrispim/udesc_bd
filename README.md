# udesc_bd

Instruções:

- Realizar clone repositório

<b> Preparação backup </b>

- No SGBD de sua preferência executar os comandos do arquivo postgres_0_0_0_0-2023_04_22_18_31_05-dump.sql presente na pasta 
<a href="https://github.com/gdesouzacrispim/udesc_bd/tree/master/backp_bd"> backp_bd

- atente-se: novo schema chamado <b>trabalho</b> deve ter sido gerado com suas tabelas

<b> Preparação aplicação </b>
- Na IDE de sua preferência abrir o projeto tower_bank

- Necessário ter o maven previamente instalado <br> execute o comando: <i>mvn clean install</i> - para download dos artefatos necessários e realização do build do projeto

- Configurar classe <a href="https://github.com/gdesouzacrispim/udesc_bd/tree/master/tower_bank/src/main/java/connection">Con.java</a> com os parâmetros
de conexão de seu banco

Senha para sessão como administrador: pudim

------------------

#06/07

  - no arquivo NOVO_CREATE_NEO4J.txt há os scripts a serem rodados no neo4j para já popular com alguns nós e relações.
  - É necessário trocar autenticação na classe de Con.class caso a mesma mude - demais passos para execução são idênticos ao relatados acima. 
