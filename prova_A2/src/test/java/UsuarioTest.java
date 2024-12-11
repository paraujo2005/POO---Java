import org.example.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveCriarUsuarioComAtributosCorretos() {
        Usuario usuario = new Usuario("12345678900", "senha123", "usuario@teste.com", "Empresa Teste");

        assertNotNull(usuario, "O usuário não deve ser nulo");
        assertEquals("12345678900", usuario.getCpf(), "CPF deve ser definido corretamente");
        assertEquals("senha123", usuario.getSenha(), "Senha deve ser definida corretamente");
        assertEquals("usuario@teste.com", usuario.getEmail(), "Email deve ser definido corretamente");
        assertEquals("Empresa Teste", usuario.getEmpresa(), "Empresa deve ser definida corretamente");
    }

    @Test
    void deveAlterarAtributosDoUsuario() {
        Usuario usuario = new Usuario("12345678900", "senha123", "usuario@teste.com", "Empresa Teste");

        usuario.setSenha("novaSenha123");
        usuario.setEmail("novoemail@teste.com");
        usuario.setEmpresa("Nova Empresa");

        assertEquals("novaSenha123", usuario.getSenha(), "Senha deve ser alterada corretamente");
        assertEquals("novoemail@teste.com", usuario.getEmail(), "Email deve ser alterado corretamente");
        assertEquals("Nova Empresa", usuario.getEmpresa(), "Empresa deve ser alterada corretamente");
    }

    @ParameterizedTest
    @CsvSource({
            "12345678900, senhaSegura123, usuario@empresa.com, Acme Corporation",
            "98765432100, outraSenha456, teste@gmail.com, Tech Solutions",
            "11122233344, senhaForte987, contato@startup.com, Startup Inovadora"
    })
    void deveCriarMultiplosUsuariosComDadosDiferentes(String cpf, String senha, String email, String empresa) {
        Usuario usuario = new Usuario(cpf, senha, email, empresa);

        assertEquals(cpf, usuario.getCpf(), "CPF deve ser definido corretamente");
        assertEquals(senha, usuario.getSenha(), "Senha deve ser definida corretamente");
        assertEquals(email, usuario.getEmail(), "Email deve ser definido corretamente");
        assertEquals(empresa, usuario.getEmpresa(), "Empresa deve ser definida corretamente");
    }

    @Test
    void devePermitirCpfComTamanhoMaximo() {
        String cpfLongo = "123456789012345"; // 15 caracteres
        Usuario usuario = new Usuario(cpfLongo, "senha", "email@teste.com", "Empresa");

        assertEquals(cpfLongo, usuario.getCpf(), "Deve permitir CPF com 15 caracteres");
    }

    @Test
    void naoDevePossuirMetodosGetInvalidos() {
        Usuario usuario = new Usuario("12345678900", "senha123", "usuario@teste.com", "Empresa Teste");

        assertNotNull(usuario.getCpf(), "Método getCpf não deve retornar nulo");
        assertNotNull(usuario.getSenha(), "Método getSenha não deve retornar nulo");
        assertNotNull(usuario.getEmail(), "Método getEmail não deve retornar nulo");
        assertNotNull(usuario.getEmpresa(), "Método getEmpresa não deve retornar nulo");
    }

    @Test
    void devePermitirAlteracaoDeAtributosParaValoresVazios() {
        Usuario usuario = new Usuario("12345678900", "senha123", "usuario@teste.com", "Empresa Teste");

        usuario.setSenha("");
        usuario.setEmail("");
        usuario.setEmpresa("");

        assertEquals("", usuario.getSenha(), "Deve permitir alterar senha para string vazia");
        assertEquals("", usuario.getEmail(), "Deve permitir alterar email para string vazia");
        assertEquals("", usuario.getEmpresa(), "Deve permitir alterar empresa para string vazia");
    }
}