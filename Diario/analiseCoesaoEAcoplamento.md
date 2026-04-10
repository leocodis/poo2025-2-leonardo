# Análise de Coesão e Acoplamento

O sistema apresenta boa coesão, pois cada classe possui uma responsabilidade específica. A classe `RegistroDiario` representa os dados principais do registro, enquanto a classe `Categoria` representa a classificação do registro. A classe `DiarioPessoal` concentra as regras de negócio do sistema, implementando a interface `Diario`. Já a classe `GravadorDeDados` cuida apenas da persistência.

Quanto ao acoplamento, ele é moderado e aceitável. A classe `DiarioPessoal` depende de `RegistroDiario`, `Categoria` e `GravadorDeDados`, o que é natural para a implementação das funcionalidades. Ainda assim, as responsabilidades estão bem separadas.

## Possíveis melhorias futuras

- Reduzir dependência direta de `GravadorDeDados` por meio de abstração
- Melhorar validação de entradas
- Evitar repetição de código na interface gráfica
- Criar métodos mais específicos para edição de registros
- Melhorar tratamento de erros na interface com o usuário

## Bad Smells identificados

- Repetição de lógica na interface gráfica
- Possível crescimento excessivo da classe `DiarioPessoal`
- Falta de validação em alguns campos de entrada
- Forte dependência de arquivo fixo para persistência