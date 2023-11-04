<h1>Build & Deploy da solução</h1>

:heavy_check_mark: [Clone do projeto do Github](#clone-projeto-github)

:heavy_check_mark: [Build e deploy da solução](#build-deploy-solução)

## Clone do projeto do Github

**Objetivo**: Baixar o código fonte da Poc ContratoJá no repositório do _Github_ para o seu ambiente local _Linux_.

1. Faça o clone do projeto em alguma pasta de sua preferência. Por exemplo, `/opt`

```
sudo git clone https://github.com/rpicanco/poc-contratoJa.git
``` 

2. Entre na pasta raiz do repositório.

## Build e deploy da solução

**Objetivo**: Contruir e deployar todos os componentes da solução no seu ambiente local _Linux_ usando o **Localstack**.

1. Inicializar o **Localstack**

```
sudo make start-localstack
```

2. Verifique se o container do localstack foi inicializado com sucesso

```
docker ps
```

:loudspeaker: Aguarde o status do container ficar: **Up XX seconds (healthy)**.

3. Montar toda a stack necessária com os serviços da AWS utilizando o **Localstack** através do _Make_

```
sudo make monta-localstack-env
```

4. Construir e deployar os microserviços `integra-rj` e `integra-sc`.

```
sudo make build-start
```

:loudspeaker: O primeiro build e deploy é sempre mais demorado por precisar baixar todas as imagens necessárias.

3. Verificar se a solução está rodando

:point_right: Aparecerá apenas log do tipo _INFO_ e no final a lista dos componentes/containers inicializados e prontos para serem utilizados.

<img src="/imagens/build-start-sucesso.png">

:point_right: Além dos containers dos microsserviços e do localstack, temos o container do _mockoon_ (https://mockoon.com/), que é a nossa solução de mock para REST API para simular o sistema dos estados.

:loudspeaker: Se tudo estiver ok, a solução estará pronta para ser testada no _postman_.