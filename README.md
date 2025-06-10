Sistema CRUD - Cavaleiros do Zodíaco (Andrei e Claiton)
Este projeto Java implementa um sistema CRUD (Create, Read, Update, Delete) para gerenciar dados do universo dos Cavaleiros do Zodíaco em um banco de dados MySQL hospedado no Aiven.

🚀 Funcionalidades
O sistema permite operações básicas de CRUD para as tabelas: Deuses, Esferas_Poder, Seguidores, Templos, e Rituais.

⚙️ Pré-requisitos
Java Development Kit (JDK): Versão 11 ou superior.

IntelliJ IDEA: Ou outra IDE Java.

MySQL Connector/J JAR: O driver JDBC.

Serviço MySQL no Aiven: Com o banco de dados deuses_entidades_divinas_Andrei_e_Claiton criado (ou o seu banco de dados, nisso teria que alterar no código o nome do banco).

☁️ Configuração do Banco de Dados
Crie o banco de dados deuses_entidades_divinas_Andrei_e_Claiton (ou o seu) no seu serviço MySQL do Aiven.

Crie as tabelas (Deuses, Esferas_Poder, Seguidores, Templos, Rituais) no banco de dados. A tabela Deuses deve ser criada primeiro.

Obtenha suas credenciais de conexão no painel do Aiven (Host, Porta, Nome do Banco de Dados, Usuário, Senha, Certificado CA).

🔑 Configuração das Credenciais no Código
Abra src/main/java/conexao/Conexao.java e atualize as seguintes constantes com suas informações do Aiven:

public class Conexao {
    private static final String DB_HOST = "bd-andrei-ba-8f3d.faivencloud.com"; // Seu Host
    private static final String DB_PORT = "19535"; // Sua Porta
    private static final String DB_NAME = "deuses_entidades_divinas_Andrei_e_Claiton"; // Nome do seu BD
    private static final String DB_USER = "avnadmin"; // Seu Usuário
    private static final String DB_PASSWORD = "SUA_SENHA"; // Sua Senha
    // ...
}


🔐 Configuração SSL (Essencial para Aiven)
Para conectar ao Aiven, você precisa configurar o SSL.

Baixe o Certificado CA (ca.pem ou similar) do seu serviço Aiven.

Importe para o TrustStore do Java:

keytool -import -trustcacerts -file /caminho/para/o/seu/ca.pem -alias aiven_mysql_ca -keystore "$JAVA_HOME/lib/security/cacerts"


(Use a senha changeit se não alterou).

💻 Configuração do Projeto no IntelliJ IDEA
Clone o Repositório: git clone <URL_DO_SEU_REPOSITORIO>

Abra o Projeto no IntelliJ.

Configure o JDK do Projeto: Em File > Project Structure... > Project, selecione seu JDK.

Adicione o MySQL Connector/J: Baixe o mysql-connector-j-X.Y.Z.jar e adicione-o como uma biblioteca ao seu projeto em File > Project Structure... > Libraries > + > Java.

Verifique a Estrutura de Pastas:

src/main/java/
├── DAO/
├── conexao/
├── entity/
└── mainapp/


▶️ Como Executar o Sistema
No IntelliJ, vá em Build > Rebuild Project.

Execute a classe principal: src/main/java/mainapp/Main.java. Clique com o botão direito e selecione Run 'Main.main()'.

O console exibirá as operações CRUD sendo executadas.

⚠️ Solução de Problemas
Communications link failure / UnknownHostException:

Verifique o DB_HOST em Conexao.java (erro de digitação).

Teste a conectividade de rede com ping seu.host.do.aiven.com.

Verifique seu firewall.

Erros relacionados a SSL:

Garanta que o certificado CA foi importado corretamente para o TrustStore do Java.
