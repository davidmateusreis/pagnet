<h1 align="center">
  Desafio Backend Pagnet
</h1>

Série apresentada [aqui](https://www.youtube.com/playlist?list=PLiFLtuN04BS1c-JvhKFxYyeD-GVtnwUcx) para ilustrar todas as etapas de desenvolvimento, desde a arquitetura até o deploy de uma aplicação do mundo real. Esse projeto foi baseado [nesse desafio](https://github.com/Pagnet/desafio-back-end/tree/master) para uma vaga backend. A solução desenvolvida possui projetos frontend e backend e utiliza Spring Batch para o processamento de arquivos CNAB e exibe os seus lançamentos importados em uma interface SPA com React.

## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc)
- [Spring Batch](https://spring.io/projects/spring-batch)
- [Vite](https://vitejs.dev)
- [React](https://pt-br.react.dev)
- [Tailwind](https://tailwindcss.com)
- [PostgreSQL](https://www.postgresql.org/)

## Como Executar

A execução deve ser feita com o Docker Compose.

- Clonar repositório git:
```
git clone https://github.com/davidmateusreis/pagnet.git
```
- Executar o script de inicialização:
```
chmod +x start.sh
./start.sh
```
- Acessar aplicação em `http://localhost:9090`. O arquivo de upload a ser usado deve ser no formato CNAB, anexado em `files`.

## Ambiente

Os projetos foram publicados no [Render](https://render.com) e o sistema pode ser acessado [nesse link](https://frontend-pagnet-q0ri.onrender.com).

## Decisões de Arquitetura

- O controle de unicidade das transações é feito pelo nome do arquivo CNAB, o que significa que o processamento das transações é feito apenas uma vez por arquivo.
- O arquivo CNAB deve ser nomeado com um id ou timestamp, pois ele será passado como
parâmetro do job e só pode ser importado uma única vez.
- Caso seja informado um arquivo já importado, deve ser informada uma mensagem de
erro ao usuário.
- Caso haja erro no processamento é possível submeter o mesmo arquivo várias vezes para habilitar o restart de onde o processamento parou.
- Se o arquivo for muito grande, é possível utilizar uma estratégia de particionamento
no job, melhorando assim a performance.