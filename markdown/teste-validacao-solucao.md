<h1>Teste & Validação</h1>

## Objetivo 

Testar e validar a solução através da coleção do _Postman_. É necessário executar o passo a passo descrito no [Build & Deploy da solução](https://github.com/rpicanco/poc-contratoJa/blob/main/markdown/build-deploy-solucao.md)

## Teste

1. Importe no <a href="/postman/" rel="some text"><img src="imagens/icone-postman.png" alt="Postman" width="50" height="80" /></a> a coleção **Poc contratoJa.postman_collection.json** e variável de ambiente **Localstack.postman_environment.json** da pasta _postman_ do projeto. 

2. No terminal, recupere o `rest-api-id` gerado na criação do AWS API Gateway de contrato no _localstack_.

```
sudo make get-rest-api-id
```

3. Com o `rest-api-id`, atualize a variável de ambiente no _postman_ `REST_API_ID`.

:loudspeaker: A variável de ambiente `LOCALSTACK_ENDPOINT` está de acordo com a porta definida no `docker-compose.yml`. Por exemplo, `http://localhost:4566`.

4. Execute os seguintes _requests_:

* Criar contrato RJ
* Criar contrato SC

## Validação

| UF     | Resultado esperado                                                  |
| -------| ------------------------------------------------------------------- |
| RJ     | Contrato foi processado com sucesso pelo microsserviço `integra-rj` |
| SC     | Contrato foi processado com sucesso pelo microsserviço `integra-sc` |

### Cenário 1: Contrato celebrado no RJ

1. Execute o comando a seguir alterando o `NUMERO_CONTRATO` gerado na resposta da requisição do _POST Criar contrato RJ_ no _postman_ 

```
make show-logs | grep 'Contrato registrado com sucesso!!! Número contrato \[NUMERO_CONTRATO\] ID do registro do sistema do estado RJ'
```

:heavy_check_mark: Se apareceu o texto, sucesso.


### Cenário 2: Contrato celebrado em SC

1. Execute o comando a seguir alterando o `NUMERO_CONTRATO` gerado na resposta da requisição do _POST Criar contrato SC_ no _postman_ 

```
make show-logs | grep 'Contrato registrado com sucesso!!! Número contrato \[NUMERO_CONTRATO\] ID do registro do sistema do estado SC'
```

:heavy_check_mark: Se apareceu o texto, sucesso.

:loudspeaker: Quando for um contrado celebrado do `RJ`, o microsserviço a ser executado é o `integra-rj`. Já se for um contrato celebrado em `SC`, o microsserviço a ser executado é o `integra-sc`. 