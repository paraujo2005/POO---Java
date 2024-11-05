import org.example.Produto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class ProdutoTest {
    private Produto produto;
    private Produto produto2;
    private Produto produto3;

    @Test
    public void testCriacaoProduto() throws IOException {
        produto = new Produto("Arroz", 10.0);

        //Produto Inicializado
        assertNotNull(produto);

        //Produto Retornando Nome Correto
        assertEquals("Arroz", produto.getNome());

        //Produto Retornando Valor Correto
        assertEquals(10, produto.getPreco());
    }

    @Test
    public void testFuncaoEqualsProduto() throws IOException {
        produto = new Produto("Arroz", 10.0);
        produto2 = new Produto("Arroz", 10.0);
        produto3 = new Produto("Chocolate", 3.5);

        //Teste de Igualdade Produtos Iguais
        assertEquals(produto,produto2);

        //Tesde de Igualdade Produtos Diferentes
        assertNotEquals(produto2,produto3);
    }

    @Test
    public void testFuncaoHashCodeProduto() throws IOException {
        produto = new Produto("Arroz", 10.0);
        produto2 = new Produto("Arroz", 3.5);
        produto3 = new Produto("Chocolate", 3.5);

        //Teste de Igualdade dos HashCodes dos Produtos com o Mesmo Nome
        assertEquals(produto.hashCode(), produto2.hashCode());

        //Teste de Igualdade dos HashCodes dos Produtos com Nomes Diferentes
        assertNotEquals(produto2.hashCode(), produto3.hashCode());
    }

    @Test
    public void testFuncaoCompareToProduto() throws IOException {
        produto = new Produto("Arroz", 10.0);
        produto2 = new Produto("Arroz", 3.5);
        produto3 = new Produto("Chocolate", 3.5);

        //Teste de CompareTo de Produtos com Preços Diferentes
        assertTrue(produto.compareTo(produto2) > 0);
        assertTrue(produto2.compareTo(produto) < 0);

        //Teste de CompareTo de Produtos com Preços Iguais
        assertEquals(0, produto3.compareTo(produto2));
        assertEquals(0, produto2.compareTo(produto3));
    }
}
