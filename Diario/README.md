# Mini Sistema de Diário Pessoal

Este projeto implementa um mini sistema de diário pessoal em Java.

## Descrição
O sistema permite cadastrar registros de um diário, pesquisar registros por data, remover registros e salvar/recuperar os dados em arquivo.  
Além disso, o sistema possui uma interface gráfica simples para interação com o usuário.

## Funcionalidades
- Cadastrar registros com título, texto, dia e mês
- Pesquisar registros por data
- Remover registros pelo título
- Salvar os dados em arquivo
- Recuperar os dados do arquivo
- Interface gráfica utilizando JOptionPane para interação com o usuário

## Estrutura do projeto
- `Diario.java`: interface do sistema
- `DiarioPessoal.java`: classe principal com as funcionalidades
- `RegistroDiario.java`: representa um registro do diário
- `GravadorDeDados.java`: responsável pela persistência dos dados
- `RegistroInexistenteException.java`: exceção para registro inexistente
- `DiarioPessoalTest.java`: classe de testes
- `MainDiarioGUI.java`: classe responsável pela interface gráfica do sistema

## Estrutura de dados utilizada
O sistema utiliza um `Map<String, RegistroDiario>`, onde a chave é o título do registro.

## Persistência
A persistência é feita em arquivo por meio da gravação e recuperação de objetos com a classe `GravadorDeDados`.

## Interface gráfica
Foi implementada uma interface gráfica simples utilizando a classe `JOptionPane`, permitindo ao usuário interagir com o sistema através de janelas para realizar operações como cadastro, pesquisa, remoção e salvamento de dados.