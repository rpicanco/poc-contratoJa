<h1>Pré-requisitos</h1>

:heavy_check_mark: [Sistema operacional Linux](#sistema-operacional-linux)

:heavy_check_mark: [Docker](#docker)

:heavy_check_mark: [Docker Compose](#docker-compose)

:heavy_check_mark: [Maven](#maven)

:heavy_check_mark: [Make](#make)

:heavy_check_mark: [Pip](#pip)

:heavy_check_mark: [AWS CLI local](#awscli-local)

>
> **Nota**:
> 
> Clique <a href="para-usuarios-windows.md">aqui</a> para usuários do sistema operacional _Windows_
> 

## Sistema operacional Linux 

### Docker

1. Instalar o Docker

```
sudo apt-get update
```

```
sudo apt-get install ca-certificates curl gnupg
```
	
```
sudo install -m 0755 -d /etc/apt/keyrings
```

```
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
```

```
sudo chmod a+r /etc/apt/keyrings/docker.gpg
```

```
echo \
  "deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  "$(. /etc/os-release && echo "$VERSION_CODENAME")" stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

```
sudo apt-get update
```

```
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```
**Do you want to continue? [Y/n]** y

2. Iniciar o Docker

```
sudo service docker start
```

3. Verificar se a instalação do Docker está ok

```
sudo docker run hello-world
```

>
> **Referência**:
> 
> https://docs.docker.com/engine/install/ubuntu/#install-using-the-repository
>

### Docker Compose

1. Instalar o Docker Compose

```
sudo curl -L https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose
```

```
sudo chmod +x /usr/local/bin/docker-compose
```

2. Verifique se a instalação do docker compose está ok

```
docker-compose version
```

### Maven

1. Instalar o Maven

```
sudo apt-get update
```

```
sudo apt-get install maven
```
**Do you want to continue? [Y/n]** y

2. Verifique se a instalação do maven está ok

```
mvn --version
```

### Make

1. Instalar o Make

```
sudo apt update
```

```
sudo apt install make
```

### Pip

1. Instalar o Pip

```
sudo apt update
```

```
sudo apt install python3-pip
```
**Do you want to continue? [Y/n]** y

### AWS CLI local

1. Instalar o AWS CLI local

```
pip install awscli-local
```

2. Copiar a pasta `.aws` com os arquivos `config.txt` e `credentials.txt` na pasta _/home/<<seu_usuário>>/_

>
> **Referência**:
> 
> https://docs.localstack.cloud/user-guide/integrations/aws-cli/#localstack-aws-cli-awslocal
>
                          
### Ambiente de execução da PoC

<img src="/imagens/ambiente-execucao.png">