# API de Gerenciamento de Livros

API REST simples para gerenciamento de livros, implementada com Spring Boot. Permite realizar operações de CRUD (criação, leitura, atualização e exclusão) de livros, com persistência em banco de dados H2 e testes básicos para validação das rotas(Teste NTConsult)

## Funcionalidades da API

A API oferece as seguintes funcionalidades:

- **Cadastro de Livros**: Criação de novos livros no sistema.
- **Listagem de Livros**: Consulta a todos os livros cadastrados.
- **Atualização de Livros**: Modificação de livros já cadastrados.
- **Exclusão de Livros**: Exclusão de livros cadastrados.

## Tecnologias Usadas

- **Back-End**:
  - Java 17
  - Spring Boot 3.4.4
  - Spring Data JPA
  - Spring Validation
  - Swagger (para documentação da API)
  
- **Banco de Dados**:
  - H2 Database (em memória)

## Endpoints da API

### **POST /livro**
Cria um novo livro no sistema.

- **Descrição**: Envia um livro no corpo da requisição e armazena no banco de dados.
- **Request Body**:
  ```json
  {
    "idLivro": 1,
    "titulo": "O Senhor dos Anéis",
    "autor": "J.R.R. Tolkien",
    "anoPublicacao": 1954
  }

## Respostas

### **POST /livro**
Cria um novo livro no sistema.

- **Resposta**:
  - **Código 201 (Created)**: Caso o livro seja criado com sucesso.
  - **Código 500 (Internal Server Error)**: Caso ocorra um erro ao salvar o livro.

### **GET /livro**
Retorna uma lista com todos os livros cadastrados no sistema.

- **Descrição**: Recupera todos os livros armazenados no banco de dados.
- **Resposta**:
  - **Código 200 (OK)**: Lista de livros em formato JSON.
  - **Código 500 (Internal Server Error)**: Caso ocorra um erro ao recuperar os livros.
    
- **Request Body**:
  ```json
  [
     {
        "idLivro": 2,
        "titulo": "Harry Potter e a Pedra Filosofal",
        "autor": "J.K. Rowling",
        "anoPublicacao": 1997
     },
     {
        "idLivro": 3,
        "titulo": "As crônicas de Nárnia - O leão, a feiticeira e o guarda-roupa",
        "autor": "C. S. Lewis",
        "anoPublicacao": 1950
     }
  ]
  

### **PUT /livro/{id}**
Atualiza um livro existente no sistema com base no ID fornecido.

- **Descrição**: Modifica os dados de um livro já cadastrado no sistema.
- **Parâmetros**:
  - `id`: ID do livro que será atualizado.
- **Request Body**:
  ```json
  {
    "idLivro": 1,
    "titulo": "O Hobbit",
    "autor": "J.R.R. Tolkien",
    "anoPublicacao": 1937
  }
### **DELETE /livro/{id}**
Exclui um livro do sistema com base no ID fornecido.

- **Descrição**: Remove um livro do banco de dados.

- **Parâmetros**:
  - `id`: ID do livro que será deletado.

- **Resposta**:
  - **Código 200 (OK)**: Caso o livro seja excluído com sucesso.
  - **Código 404 (Not Found)**: Caso o livro não seja encontrado no sistema.
  - **Código 500 (Internal Server Error)**: Caso ocorra um erro ao tentar deletar o livro.
 
## Como Rodar o Projeto

### Requisitos

- **Java 17** ou superior
- **Maven 3.x** ou superior

### Passos para Instalar

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/book-management-api.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd book-management-api
    ```

3. Instale as dependências do back-end utilizando o Maven:
    ```bash
    mvn clean install
    ```

4. Execute o back-end:
    ```bash
    mvn spring-boot:run
    ```

   A API estará rodando em `http://localhost:8080`.

### Documentação da API

Para facilitar o uso e a compreensão da API, a documentação interativa da API foi gerada utilizando Swagger. Após rodar a aplicação, você pode acessar a documentação da API em:

- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`

### Como Usar

1. Acesse a documentação interativa da API em `http://localhost:8080/swagger-ui/index.html`.
2. Utilize os endpoints documentados para interagir com a API, realizar cadastros e consultar os livros.

### Testes

  A aplicação inclui testes automatizados para garantir que as funcionalidades da API estão funcionando corretamente. Você pode executar os testes com o seguinte comando:
  
  ```bash
  mvn test
  ```
### Contato

[Gildemar Diniz](https://www.linkedin.com/in/gildemardiniz) | diniz.g.dev@gmail.com
