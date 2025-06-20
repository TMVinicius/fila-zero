# 🛒 Fila Zero - Sistema de Pedidos

O **Fila Zero** é um sistema desktop Java desenvolvido como projeto final da disciplina de Programação de Soluções Computacionais. Ele simula um ambiente de compras para supermercados, farmácias e estabelecimentos do varejo, permitindo que clientes consultem produtos por código de barras, montem sua sacola e finalizem seus pedidos com pagamento incluso.

## 🔍 Funcionalidades

- Cadastro e login de clientes e estabelecimentos;
- Consulta de produtos por código de barras;
- Adição de produtos à sacola do cliente;
- Finalização de pedidos com cálculo do valor total;
- Visualização do histórico de pedidos realizados;
- Detalhamento de cada pedido: itens comprados, valor e quantidade;
- Integração com banco de dados MySQL.

## 🧱 Estrutura do Projeto

  - **`/main`**:
    - **`/App.java`**: 
  - **`/model`**: Contém as classes que representam as entidades do sistema.
    - **`Usuario.java`**
    - **`Cliente.java`**
    - **`Estabelecimento.java`**
    - **`Produto.java`**
    - **`Pedido.java`**
    - **`ItemPedido.java`**
  - **`/repo`**: Repositórios responsáveis pela persistência dos dados no banco.
    - **`ClienteRepo.java`**
    - **`EstabelecimentoRepo.java`**
    - **`ProdutoRepo.java`**
    - **`PedidoRepo.java`**
    - **`ItemPedidoRepo.java`**
  - **`/utils`**:
    - **`Conexao.java`**: Gerencia a conexão com o banco de dados MySQL.
  - **`/view`**: Interface gráfica Swing.
    - **`/cadastro`**
    - **`/cliente`**
    - **`/login`**

## 💻 Tecnologias Utilizadas

- Java 17+ ☕
- Swing para interface gráfica 🖼️
- JDBC com MySQL para persistência de dados 🛢️

## 🛠️ Requisitos para rodar

- JDK 17 ou superior;
- MySQL Server rodando e configurado;
- IDE (Ex: IntelliJ, Eclipse ou VSCode com extensão Java);
- Biblioteca Lombok (já configurada no projeto).

## 🖼️ Telas de Interface (Swing)

- Tela de Login para Cliente, Estabelecimento ou Admin;
- Tela de busca de produtos e montagem de sacola;
- Tela de finalização de pedidos;
- Tela para visualizar pedidos anteriores com detalhes.

### 📥 Como executar o projeto

1. Clone o repositório:

   ```
   git clone https://github.com/TMVinicius/fila-zero
   cd fila-zero
   ```

2. Configure o banco de dados MySQL:

- Atualize a classe Conexao.java com suas credenciais;
- Utilize o arquivo DB_Fila_Zero.sql para estruturar o banco de dados.

3. Compile e execute o projeto pela sua IDE ou terminal.


## 🚀 Futuras Melhorias

### 🔧 Refatoração e Arquitetura
- Migração para **Spring Boot** para melhor organização e injeção de dependência.
- Utilização do **Maven** como gerenciador de dependências e build.
- Criação de um pacote específico para **tratamento de exceções personalizadas** (`/exceptions`).

### 💻 Interface do Usuário
- Criação de um **front-end moderno** com **HTML, CSS, JavaScript (Vue.js)**.
- Inclusão de **botão de "Voltar"** entre as telas para melhorar a navegação.
- Adição de opção para **favoritar produtos e estabelecimentos**.

### 🔐 Segurança e Autenticação
- Implementar **hash (BCrypt)** para senhas e dados sensíveis vindos do banco de dados.
- Adicionar funcionalidade de **recuperação de senha via e-mail ou celular**.

### 📱 Integrações e Funcionalidades
- Integração com **APIs de pagamento** (Pix, Mercado Pago, Google Pay).
- Integração com **API de geolocalização** para encontrar estabelecimentos próximos.
- Sistema de **notificações de status do pedido**.

### 📊 Ferramentas para Estabelecimentos
- Desenvolvimento de um **Dashboard administrativo** com métricas de vendas e gerenciamento de pedidos.

### ☁️ Infraestrutura e DevOps
- **Containerização total** da aplicação com **Docker** (atualmente só o banco está conteinerizado).
- **Deploy na AWS** (EC2, RDS, S3) com foco em escalabilidade e alta disponibilidade.


## 📧 Contato

Para qualquer dúvida ou feedback, entre em contato comigo por [viniciustavaresbr@gmail.com](mailto:viniciustavaresbr@gmail.com).
