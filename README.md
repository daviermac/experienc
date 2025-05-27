# Gerenciador de Tarefas

Projeto de um gerenciador de tarefas simples desenvolvido em Java com Spring Boot, utilizando Thymeleaf para a interface web e MySQL para persistência dos dados.

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Thymeleaf
- MySQL
- Maven

## Como clonar o repositório

Para clonar este projeto, execute o comando:

```bash
git clone https://github.com/daviermac/experienc.git
```

## Como rodar o projeto

1. Certifique-se de ter o MySQL instalado e em execução.
2. Crie um banco de dados chamado `gerenciador_tarefas` ou deixe o Spring Boot criar automaticamente.
3. Configure o usuário e senha do banco no arquivo `src/main/resources/application.properties` (padrão: usuário `root` e senha `1910`).
4. No terminal, navegue até a pasta do projeto e execute:

```bash
mvn spring-boot:run
```

5. Acesse a aplicação no navegador em: [http://localhost:8080](http://localhost:8080)

## Funcionalidades

- Listar tarefas com filtros por status e prioridade.
- Criar, editar e deletar tarefas.
- Marcar tarefas como concluídas.
- Buscar tarefas por termo.

## Autor

Davi Paixão
