<h2>Para usuários do Windows</h2>

Caso você está usando o sistema operacional _Windows_, você pode rodar um ambiente do sistema operacional _Linux_ (_Ubuntu_) dentro do _Windows_ para executar os comandos. 

### Instalar o WSL2 e Ubuntu pelo Powershell no modo administrador

1. No Powershell (como administrador), execute o comando a seguir:

```
wsl --install -d Ubuntu
```

:loudspeaker: Dependendo da versão do seu o sistema operacional _Windows_, a forma de instalação é diferente. Ver nas referências abaixo. 

>
> **Referência**:
> 
> https://learn.microsoft.com/pt-br/windows/wsl/install
>

### Configure um usuário e senha para o ambiente Linux (Ubuntu)

1. Se a tela do terminal do Ubuntu não aparecer automaticamente, no botão _windows_, vá em **Iniciar** -> **Ubuntu**

2. Aguarde um instante e aparecerá a opção para você definir um usuário e senha.

:loudspeaker: Após a definição do usuário e senha, o terminal de comando do Linux ficará disponível para você executar os comandos.