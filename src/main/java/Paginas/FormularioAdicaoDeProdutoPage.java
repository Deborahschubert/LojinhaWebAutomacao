package Paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicaoDeProdutoPage {
    private WebDriver navegador;

    public FormularioAdicaoDeProdutoPage (WebDriver navegador){
        this.navegador = navegador;
    }

    public FormularioAdicaoDeProdutoPage informarNomeDoProduto (String produtoNome){
        navegador.findElement(By.id("produtonome")).sendKeys(produtoNome);

        return this;
    }

    public FormularioAdicaoDeProdutoPage informarValorDoProduto (String produtoValor){
        navegador.findElement(By.name("produtovalor")).sendKeys(produtoValor);

        return this;
    }
    public FormularioAdicaoDeProdutoPage informarCorDoProduto (String produtoCor){
        navegador.findElement(By.id("produtocores")).sendKeys(produtoCor);

        return this;
    }
    public ListaDeProdutosPage submeterFormularioDeAdicaoComErro(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new ListaDeProdutosPage (navegador);
    }
    public FormularioEdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso(){
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        return new FormularioEdicaoDeProdutoPage(navegador);

    }
}
