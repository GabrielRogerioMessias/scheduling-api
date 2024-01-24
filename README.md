# Descrição do Projeto - API para agendamentos médicos no âmbito empresarial

## Visão Geral
O projeto é uma API robusta desenvolvida em Java, utilizando o framework Spring Boot, voltada para o gerenciamento completo de agendamentos em um ambiente de saúde. Com funcionalidades abrangentes que incluem operações CRUD para médicos, funcionários, informações de agendamento, agendamentos e usuários com permissões controladas, o sistema oferece uma solução completa para o controle eficiente e seguro de agendamentos médicos.

## Tecnologias Utilizadas
- **Java 17:** A linguagem de programação de última geração foi escolhida para aproveitar suas funcionalidades mais recentes e melhorias de desempenho.
- **Spring Boot:** O framework oferece uma base sólida para desenvolvimento de aplicativos Java, facilitando a criação de APIs RESTful e integração com outros módulos do Spring.
- **Spring Data JPA:** Facilita a implementação de operações CRUD no banco de dados MySQL, proporcionando um mapeamento objeto-relacional eficiente.
- **MySQL:** O banco de dados relacional foi escolhido para armazenar e gerenciar os dados de maneira estruturada e confiável.
- **Token JWT (JSON Web Token):** O sistema de autenticação baseado em token JWT garante a segurança das operações, fornecendo uma solução robusta para autenticação e autorização.
- **Mockito e JUnit:** Foram utilizados para implementar testes unitários, garantindo a confiabilidade e integridade do código.

## Principais Recursos
- **Médicos:** CRUD completo para criação, leitura, atualização e exclusão de registros de médicos.
- **Funcionários:** CRUD para gestão eficiente de informações sobre os funcionários do sistema.
- **Informações de Agendamento:** Operações CRUD para gerenciamento de informações específicas de agendamento. (Consulta demissional, admissional, rotineira…)
- **Agendamentos:** CRUD para criar, visualizar, atualizar e excluir agendamentos, proporcionando uma gestão completa do fluxo de compromissos, também é possível bloquear agendamentos para uma determinada data, se algum médico não for comparecer.
- **Agendamento Empregado:** Funcionalidades CRUD específicas para agendamentos relacionados a empregados.
- **Usuários com Permissões Controladas:** Sistema de controle de usuários com permissões específicas, garantindo a segurança das operações e protegendo áreas sensíveis do sistema.
- **Autenticação e Autorização:** Implementação de um sistema seguro de autenticação e autorização baseado em token JWT, garantindo a proteção dos dados e operações.
- **Testes Unitários:** Utilização do Mockito e JUnit para garantir a confiabilidade e qualidade do código por meio de testes unitários abrangentes.

## Conclusão
O projeto destaca-se como uma solução abrangente e eficiente para o gerenciamento de agendamentos no ambiente empresarial. Sua versatilidade permite a marcação de diversos tipos de consultas, incluindo exames admissionais, demissionais e retornos pós-maternidade. Além disso, oferece a flexibilidade de emitir uma variedade de relatórios, proporcionando uma visão completa do fluxo de agendamentos.

A capacidade de registrar ausências de pacientes que não comparecem às consultas complementa a funcionalidade do sistema, tornando-o uma ferramenta completa para a gestão eficaz do calendário.

A escolha criteriosa das tecnologias, aliada à implementação de funcionalidades robustas, é evidente no desempenho e na segurança do sistema. Através de práticas de teste sólidas, garantimos a confiabilidade do código, assegurando um ambiente confiável para o gerenciamento eficaz e seguro de agendamentos empresariais.
