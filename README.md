# DSCommerce
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/ohyescris/fifthproject/blob/main/LICENSE) 

# Sobre o projeto

DSCommerce é uma aplicação backend construída durante as aulas do módulo **Java Spring Professional**, curso organizado pela [DevSuperior](https://devsuperior.com "Site da DevSuperior").

A aplicação consiste em um sistema de entrada e saída de pedidos, produtos, clientes e categorias, como se trata de um projeto de aprendizado, os dados são importados por um seed
e depois são armazenados no banco de dados, este seed pode ser visualizado pelo Hibernate. Para visualização dos dados é possível utilizar o Postman. Para a saída dos dados, serão
apresentados apenas DTOs, estes os mesmos construindos utilizando ORM e respeitando o padrão REST.

## Autenticação

A aplicação conta com um sistema de verificação de usuários/clientes por token no padrão Oauth2. Tokens são gerados utilizando JWT, a autenticação pede usuário e senha para geração deste
mesmo token e por um período de tempo o mesmo é válido para o usuário acessar a aplicação.

![OAUTH2](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-oauth2.jpg)

## Usuário logado

![USER-ME](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-user-me.jpg)

Neste endpoint recebemos como saída o usuário logado atualmente e suas informações de identificação.

## Pedidos

Na seção de pedidos, podemos tanto buscar quanto inserir novos pedidos. Vale ressaltar que um usuário com a role/função de admin pode buscar não somente seus próprios pedidos, mas também
os pedidos referentes a outros clientes.

![GET-ORDER-ALEX-OK](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-order-by-id-alex-user-alex-client-allowed.jpg) 
![GET-ORDER-ALEX-MARIA](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-order-by-id-alex-user-maria-client-allowed.jpg)

Já um usuário que não tem permissão de admin, não pode acessar pedidos de outros clientes, apenas os dele.

![GET-ORDER-MARIA-NOT-ALLOWED](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-order-by-id-maria-user-alex-client-denied.jpg)

## Produtos

Para o controle de produtos, é possível verificar alguns endpoints utilizando os métodos HTTP (GET, POST, PUT e DELETE). Alguns erros são reconhecidos via código, tais quais operações proibidas,
campos requeridos, quantidade mínimas de caracteres, valores positivos para preços, deletar produtos inexistentes.
Vale ressaltar também que apenas usuários admin podem mexer nos produtos, porém, clientes podem consultar os pedidos, seja por id ou uma consulta paginada.

### GET 

![GET-PRODUCT-BY-ID](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-product-by-id-ok.jpg)

![GET-PRODUCT-PAGED-1](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-product-page-ok.jpg)

![GET-PRODUCT-PAGED-2](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-product-page-2-ok.jpg)

### POST

![POST-PRODUCT](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-product-new-ok.jpg)

![POST-PRODUCT-ERROR](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-product-new-error.jpg)

### PUT

![PUT-PRODUCT](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-product-put-ok.jpg)

### DELETE

![DELETE-PRODUCT](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-product-delete.jpg)

Ressaltando que as verificações não são exclusivamente implementadas de acordo com as imagens em que aparecem e sim em diversas operações para garantir um funcionamento eficiente.

## Categorias

Na seção de categorias temos uma busca paginada pelas categorias.

![GET-CATEGORIES](https://github.com/ohyescris/assets/blob/main/images/dscommerce-fifthproject/endpoint-postman-categories.jpg)

# Tecnologias utilizadas back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven

# Como executar o projeto

Pré-requisitos: Java 17

```bash
# clonar repositório
git clone https://github.com/ohyescris/fifthproject

# executar o projeto
./mvnw spring-boot:run
```

# Autor

Cristiano da Silva Araújo

https://www.linkedin.com/in/cristiano-araújo-8172191a3

