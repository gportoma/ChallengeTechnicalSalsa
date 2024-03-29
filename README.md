# Challenge Technical Salsa

## Introdução

Este projeto é parte do desafio técnico proposto pela Salsa Technology como parte do processo seletivo. Como candidato,
estou desenvolvendo uma automação de testes de APIs para demonstrar minhas habilidades técnicas e conhecimento prático.

O objetivo principal deste desafio é criar um framework de automação robusto e estruturado, capaz de testar APIs RESTful
de forma eficiente e confiável. Através deste projeto, busco destacar minhas habilidades em desenvolvimento de software,
automação de testes e familiaridade com ferramentas populares neste domínio.

## Tecnologias Utilizadas

Este projeto utiliza as seguintes tecnologias e ferramentas:

- **Java:** Linguagem de programação principal para desenvolvimento do código.
- **RestAssured:** Framework de automação de testes para testar APIs RESTful em Java.
- **Gradle:** Ferramenta de automação de build utilizada para gerenciar as dependências e compilar o projeto.
- **JUnit:** Framework de testes unitários para execução dos testes automatizados.
- **YAML:** Formato de arquivo utilizado para armazenar configurações externas, como URLs base e outras informações de
  configuração.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

### src/main/java/br.com.guilherme/core

Este diretório contém as classes principais da aplicação. Notavelmente, inclui a classe `RestAssuredConfig`, responsável
por configurar o RestAssured, incluindo a definição do URI base.

### src/main/java/br.com.guilherme/model

Aqui estão as classes de modelo representando objetos JSON usados em requisições e respostas da API. Essas classes são
mapeadas para a estrutura JSON e usadas para serialização e desserialização.

### src/main/java/br.com.guilherme/runner

Este diretório contém as classes de execução de testes. Essas classes contêm chamadas RestAssured para interagir com as
APIs e assertivas para validar as respostas. Cada caso de teste é representado como um método dentro dessas classes.

### src/main/java/br.com.guilherme/util

Contém classes utilitárias usadas em todo o projeto. Atualmente, inclui apenas a classe `YAMLConfigReader`, que facilita
a leitura de arquivos YAML para recuperar valores de configuração para a aplicação.

### src/main/resources/

Este diretório contém o arquivo YAML usado para passar informações de configuração para a aplicação, como URLs base,
detalhes de autenticação, etc.
