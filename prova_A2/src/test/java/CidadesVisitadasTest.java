import org.example.CidadesVisitadas;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CidadesVisitadasTest {

    @Test
    void deveCriarCidadeVisitadaComAtributosCorretos() {
        CidadesVisitadas cidade = new CidadesVisitadas("São Paulo", "Brasil", 2022);

        assertNotNull(cidade, "A cidade não deve ser nula");
        assertEquals("São Paulo", cidade.getNome(), "Nome da cidade deve ser definido corretamente");
        assertEquals("Brasil", cidade.getPais(), "País deve ser definido corretamente");
        assertEquals(2022, cidade.getAno(), "Ano deve ser definido corretamente");
    }

    @Test
    void deveAlterarAtributosDaCidadeVisitada() {
        CidadesVisitadas cidade = new CidadesVisitadas("Rio de Janeiro", "Brasil", 2021);

        cidade.setNome("Salvador");
        cidade.setPais("Portugal");
        cidade.setAno(2023);

        assertEquals("Salvador", cidade.getNome(), "Nome da cidade deve ser alterado corretamente");
        assertEquals("Portugal", cidade.getPais(), "País deve ser alterado corretamente");
        assertEquals(2023, cidade.getAno(), "Ano deve ser alterado corretamente");
    }

    @ParameterizedTest
    @CsvSource({
            "Paris, França, 2019",
            "Londres, Reino Unido, 2020",
            "Nova York, Estados Unidos, 2018"
    })
    void deveCriarMultiplasCidadesVisitadasComDadosDiferentes(String nome, String pais, int ano) {
        CidadesVisitadas cidade = new CidadesVisitadas(nome, pais, ano);

        assertEquals(nome, cidade.getNome(), "Nome da cidade deve ser definido corretamente");
        assertEquals(pais, cidade.getPais(), "País deve ser definido corretamente");
        assertEquals(ano, cidade.getAno(), "Ano deve ser definido corretamente");
    }

    @Test
    void devePermitirNomesComTamanhoMaximo() {
        String nomeLongo = "Uma Cidade Muito Longa com Nome Extremamente Extenso para Teste";
        CidadesVisitadas cidade = new CidadesVisitadas(nomeLongo, "Brasil", 2022);

        assertEquals(nomeLongo, cidade.getNome(), "Deve permitir nome de cidade longo");
    }

    @Test
    void devePermitirPaisesComNomesLongos() {
        String paisLongo = "Reino Unido da Grã-Bretanha e Irlanda do Norte";
        CidadesVisitadas cidade = new CidadesVisitadas("Cidade Teste", paisLongo, 2022);

        assertEquals(paisLongo, cidade.getPais(), "Deve permitir nome de país longo");
    }

    @Test
    void devePermitirAnosAnterioresEPosteriores() {
        CidadesVisitadas cidadePassado = new CidadesVisitadas("Cidade Antiga", "País Antigo", 1900);
        CidadesVisitadas cidadeFuturo = new CidadesVisitadas("Cidade Futura", "País Futuro", 2050);

        assertEquals(1900, cidadePassado.getAno(), "Deve permitir anos anteriores");
        assertEquals(2050, cidadeFuturo.getAno(), "Deve permitir anos futuros");
    }

    @Test
    void naoDevePossuirMetodosGetInvalidos() {
        CidadesVisitadas cidade = new CidadesVisitadas("São Paulo", "Brasil", 2022);

        assertNotNull(cidade.getNome(), "Método getNome não deve retornar nulo");
        assertNotNull(cidade.getPais(), "Método getPais não deve retornar nulo");
        assertNotEquals(0, cidade.getAno(), "Método getAno deve retornar valor válido");
    }

    @Test
    void devePermitirAlteracaoDeAtributosParaValoresVazios() {
        CidadesVisitadas cidade = new CidadesVisitadas("São Paulo", "Brasil", 2022);

        cidade.setNome("");
        cidade.setPais("");

        assertEquals("", cidade.getNome(), "Deve permitir alterar nome para string vazia");
        assertEquals("", cidade.getPais(), "Deve permitir alterar país para string vazia");
    }
}