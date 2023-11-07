<h2>Para usuários do Windows</h2>

Caso você está usando o sistema operacional _Windows_, você pode rodar um ambiente do sistema operacional _Linux_ (_Ubuntu_) dentro do _Windows_ para executar os comandos. 

### Instalar o WSL2 e Ubuntu pelo Powershell no modo administrador

1. No Powershell (como administrador), execute o comando a seguir:

```
wsl --update
```

2. Com o WSL2 instalado, execute o comando a seguir para instalar a distribuição Linux default (_Ubuntu_): 

```
wsl --install
```

3. Informe o nome do usuário e senha que você utilizará no _Linux_

<a href="/imagens/instalacao-ubuntu-no-windows-sucesso.png"></a>

4. Reinicie o sistema operacional _Windows_

:loudspeaker: Dependendo da versão do seu o sistema operacional _Windows_, a forma de instalação é diferente. Ver nas referências abaixo. 

>
> **Referência**:
> 
> https://learn.microsoft.com/pt-br/windows/wsl/install
>

### Inicie o Ubuntu no Windows

1. Clique no botão _windows_, vá em **Iniciar** -> **Ubuntu**

2. Aguarde um instante e o terminal de comando do Linux ficará disponível para você executar os comandos.