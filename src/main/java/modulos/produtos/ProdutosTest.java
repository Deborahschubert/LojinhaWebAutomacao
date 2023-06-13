package modulos.produtos;

import Paginas.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        //Vou maximizar a tela
        this.navegador.manage().window().maximize();

        //Vou definir um tempo de espera padrão de 10 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Navegar para apágina na Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarValorIgualAZero() {

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("informe usuario")
                .informarASenha("informe senha")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("000")
                .informarCorDoProduto("preto, branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor maior a 7 mil")
    public void testNaoEPermitidoRegistrarValorMaiorQueSeteMil(){
       String mensagemApresentada = new LoginPage(navegador)
               .informarOUsuario("informe usuario")
               .informarASenha("informe senha")
               .submeterFormularioDeLogin()
               .acessarFormularioAdicaoNovoProduto()
               .informarNomeDoProduto("Notebook")
               .informarValorDoProduto("700020")
               .informarCorDoProduto("branco")
               .submeterFormularioDeAdicaoComErro()
               .capturarMensagemApresentada();
       Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("Posso adicionar produtos que tenham valor 0,01")
    public void testPossoAdicionarProdutosComValorDeUmCentavo(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("informe usuario")
                .informarASenha("informe senha")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Kindle")
                .informarValorDoProduto("001")
                .informarCorDoProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que tenham valor 7.000,00")
    public void testPossoAdicionarProdutosComValorDeSeteMilReais(){
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("informe usuario")
                .informarASenha("informe senha")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("Iphone 8")
                .informarValorDoProduto("700000")
                .informarCorDoProduto("Gold")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach() {
        //Vou fechar o navegador
        navegador.quit();
    }
}
