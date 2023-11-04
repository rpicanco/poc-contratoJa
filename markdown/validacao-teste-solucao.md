<h1>Teste & Validação</h1>

## Objetivo 

Testar e validar a solução através da coleção do _Postman_. É necessário executar o passo a passo descrito no [Build & Deploy da solução](https://github.com/rpicanco/poc-contratoJa/blob/main/markdown/build-deploy-solucao.md)

## Teste

1. Importe a coleção _Poc contratoJa.postman_collection.json_ e variável de ambiente _Localstack.postman_environment.json_ da pasta _postman_ no **Postman**. 

2. Recuperar o `rest-api-id` gerado na criação do AWS API Gateway de contrato.

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

1. Execute o comando a seguir alterando o `NUMERO_CONTRATO` criado na resposta da requisição do _POST Criar contrato RJ_ 

```
make show-logs | grep 'Contrato registrado com sucesso!!! Número contrato \[NUMERO_CONTRATO\] ID do registro do sistema do estado RJ'
```

:heavy_check_mark: Se apareceu o texto, sucesso.


### Cenário 2: Contrato celebrado no SC

1. Execute o comando a seguir alterando o `NUMERO_CONTRATO` criado na resposta da requisição do _POST Criar contrato SC_ 

```
make show-logs | grep 'Contrato registrado com sucesso!!! Número contrato \[NUMERO_CONTRATO\] ID do registro do sistema do estado SC'
```

:heavy_check_mark: Se apareceu o texto, sucesso.

:loudspeaker: Quando for um contrado do `RJ`, o microsserviço a ser executado é o `integra-rj`. Já se for um contrato de `SC`, o microsserviço a ser executado é o `integra-sc`. 