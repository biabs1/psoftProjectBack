# psoftProjectBack

Esta é a implementação do backend do sistema AJuDE, uma ferramenta desenvolvida para a disciplina de Projeto de Software que permite a organização de doações para projetos/campanhas. 

## Requisitos

Os requisitos aqui implementados foram especificados [neste documento](https://docs.google.com/document/d/1h5WhnOhvyRmIbj_obhWK5XmoJgK35lVWPM2UwwMOT_Y/preview)

## Dependências
```
Java 1.8 ou superior
Maven
```
## Deploy
O link para deploy da aplicação (apenas Backend) está no link: https://psoft-ajude.herokuapp.com/
O link para deploy da aplicação (Backend + Frontend) está no link: http://ajude.netlify.com/

## Vídeo demonstrativo
Segue link para vídeo demonstrativo da aplicação:https://youtu.be/-qGLa6BqiAs

## Rotas e acesso a API

Você pode ter acesso a documentação das rotas com os métodos utilizados no seguinte link: https://psoft-ajude.herokuapp.com/swagger-ui.html# 

## Padrão de Desenvolvimento

Para o projeto em questão utilizamos o padrão MVC(Model-View-Controller), que é baseado em uma divisão de camadas na aplicação. Sua utilização é ampla devido a separação em camadas de apresentação, de lógica de negócio e de gerenciamento do fluxo da aplicação, aumentando as capacidades de reutilização e de manutenção do projeto. 

Para mais informações sobre o padrão arquitetural utilizado, acesse o link: https://www.devmedia.com.br/introducao-ao-padrao-mvc/29308

## Tratamento de erros e Código de Status

Para entradas anômalas as esperadas, requisições mal formuladas e eventos inesperados. O código trata o erro com diferentes formas, seja ele de falta de permissão, dados não encontrados no banco, tokens inválidos ou expirados.
Cada exception lançada possui um status code associado, você pode verificar uma lista dos status utilizados no projeto aqui: https://developer.mozilla.org/pt-BR/docs/Web/HTTP/Status
