# MountFile
Cria um novo arquivo de texto a partir de uma união lógica do conteúdo de outros arquivos de texto. Usado para criar comandos onde somente os parametros variam, mas a estrutura do comando em si é sempre a mesma. Resumidamente haverá um arquivo texto contendo uma lista de variáveis e outro arquivo de texto contendo a lista de comandos, e após a execução do algoritmo, teremos como saída o arquivo de comandos criado.

## Exemplo
Você recebeu do usuário uma planilha com os de-para dos códigos do cadastro de centro de custo, indicando os centros de custos que deveriam ser unidos, exemplo da planilha:

|    DE         |    PARA       |
|---------------|---------------|
| 1.1.2.01.0001 | 1.1.2.13.0098 |
| 1.1.2.01.0002 | 1.1.2.13.0099 |
| 1.1.2.01.0003 | 1.1.2.13.0100 |
|        .      |        .      |
|        .      |        .      |


Ao receber a planilha você monta dois arquivos texto, um com as variaveis e outro com os comandos, os elementos no arquivo de variáveis devem ser separado por ponto e vírgula, pois essa é uma determinação do algoritmo, pois é assim que separamos os elementos, cada um em sua "coluna",  veja:

|     ARQUIVO DE VARIAVEIS    |
|-----------------------------|
| 1.1.2.01.0001;1.1.2.13.0098 |
| 1.1.2.01.0002;1.1.2.13.0099 |
| 1.1.2.01.0003;1.1.2.13.0100 |
|       .                     |
|       .                     |


|                       ARQUIVO DE COMANDOS                              |
|------------------------------------------------------------------------|
| UPDATE TMOVRATCCULOGFAT SET CODCCUSTO='$1' WHERE CODCCUSTO='$0';       |
| UPDATE TMOVRATCCU SET CODCCUSTO='$1' WHERE CODCCUSTO='$0';             |
| UPDATE TMOV SET CODCCUSTO='$1' WHERE CODCCUSTO='$0';                   |
| UPDATE TITMMOVRATCCULOGFAT SET CODCCUSTO='$1' WHERE CODCCUSTO='$0';    |
|                                 .                                      |  
|                                 .                                      |

OBS.: No arquivo de comandos o $N representa a posição da coluna no arquivo de variáveis, a contar a partir do 0.

Como saída teremos um arquivo para cada linha do arquivo de variáveis:

|                                 ARQUIVO DE SAIDA PARA LINHA 0                                |
|----------------------------------------------------------------------------------------------|
| UPDATE TMOVRATCCULOGFAT SET CODCCUSTO='1.1.2.13.0098' WHERE CODCCUSTO='1.1.2.01.0001';       |
| UPDATE TMOVRATCCU SET CODCCUSTO='1.1.2.13.0098' WHERE CODCCUSTO='1.1.2.01.0001';             |
| UPDATE TMOV SET CODCCUSTO='1.1.2.13.0098' WHERE CODCCUSTO='1.1.2.01.0001';                   |
| UPDATE TITMMOVRATCCULOGFAT SET CODCCUSTO='1.1.2.13.0098' WHERE CODCCUSTO='1.1.2.01.0001';    |
|                                                       .                                      |  
|                                                       .                                      |

|                                 ARQUIVO DE SAIDA PARA LINHA 1                                |
|----------------------------------------------------------------------------------------------|
| UPDATE TMOVRATCCULOGFAT SET CODCCUSTO='1.1.2.13.0099' WHERE CODCCUSTO='1.1.2.01.0002';       |
| UPDATE TMOVRATCCU SET CODCCUSTO='1.1.2.13.0099' WHERE CODCCUSTO='1.1.2.01.0002';             |
| UPDATE TMOV SET CODCCUSTO='1.1.2.13.0099' WHERE CODCCUSTO='1.1.2.01.0002';                   |
| UPDATE TITMMOVRATCCULOGFAT SET CODCCUSTO='1.1.2.13.0099' WHERE CODCCUSTO='1.1.2.01.0002';    |
|                                                       .                                      |  
|                                                       .                                      |

|                                 ARQUIVO DE SAIDA PARA LINHA 2                                |
|----------------------------------------------------------------------------------------------|
| UPDATE TMOVRATCCULOGFAT SET CODCCUSTO='1.1.2.13.0100' WHERE CODCCUSTO='1.1.2.01.0003';       |
| UPDATE TMOVRATCCU SET CODCCUSTO='1.1.2.13.0100' WHERE CODCCUSTO='1.1.2.01.0003';             |
| UPDATE TMOV SET CODCCUSTO='1.1.2.13.0100' WHERE CODCCUSTO='1.1.2.01.0003';                   |
| UPDATE TITMMOVRATCCULOGFAT SET CODCCUSTO='1.1.2.13.0100' WHERE CODCCUSTO='1.1.2.01.0003';    |
|                                                       .                                      |  
|                                                       .                                      |
