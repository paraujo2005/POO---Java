import org.example.Estoque;
import org.example.Produto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class EstoqueTest {
    private Estoque estoque;
    private Produto produto;

    @Test
    public void testAdicionarProduto() throws IOException {
        estoque = new Estoque();
        produto = new Produto("Arroz", 10.0);

        //Adicionando Produto
        assertTrue(estoque.adicionarProduto(produto));

        //Adicionando Produto Duplicado
        assertFalse(estoque.adicionarProduto(produto));
    }

    @Test
    public void testRemoverProduto() throws IOException {
        estoque = new Estoque();
        produto = new Produto("Arroz", 10.0);
        estoque.adicionarProduto(produto);

        //Remover Prouto Existente
        assertTrue(estoque.removerProduto("Arroz"));

        //Remover Produto Existente pela Segunda Vez
        assertFalse(estoque.removerProduto("Arroz"));

        //Remover Produto Inexistente
        assertFalse(estoque.removerProduto("Chocolate"));
    }

    @Test
    public void testBuscarProduto() throws IOException {
        estoque = new Estoque();
        produto = new Produto("Arroz", 10.0);
        estoque.adicionarProduto(produto);

        //Buscar Produto Existente
        assertNotNull(estoque.buscarProduto("Arroz"));

        //Buscar Produto Inexistente
        assertNull(estoque.buscarProduto("Chocolate"));
    }

    @Test
    public void testListarProduto() throws IOException {
        estoque = new Estoque();
        produto = new Produto("Arroz", 10.0);
        Produto produto2 = new Produto("Chocolate", 3.50);

        estoque.adicionarProduto(produto);
        estoque.adicionarProduto(produto2);

        //Verificar se Produtos Adicionados são Listados Corretamente
        assertEquals(2, estoque.listarProdutos().size());
        assertTrue(estoque.listarProdutos().contains(produto));
        assertTrue(estoque.listarProdutos().contains(produto2));
    }
}