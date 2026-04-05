import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Collection;
import org.junit.Test;

public class DiarioPessoalTest {

    @Test
    public void testaCadastroPesquisaERemocao() {
        DiarioPessoal diario = new DiarioPessoal();

        assertTrue(diario.cadastraRegistro("Dia feliz", "Hoje foi um bom dia", 10, 6));
        assertTrue(diario.cadastraRegistro("Estudo Java", "Estudei programação orientada a objetos", 10, 6));
        assertFalse(diario.cadastraRegistro("Dia feliz", "Título repetido", 11, 6));

        Collection<RegistroDiario> registros = diario.pesquisaRegistrosPorData(10, 6);
        assertEquals(2, registros.size());

        try {
            assertTrue(diario.removeRegistro("Dia feliz"));
        } catch (RegistroInexistenteException e) {
            fail("Exceção não esperada");
        }

        registros = diario.pesquisaRegistrosPorData(10, 6);
        assertEquals(1, registros.size());
    }

    @Test
    public void testaPersistencia() {
        DiarioPessoal diario1 = new DiarioPessoal();
        DiarioPessoal diario2 = new DiarioPessoal();

        try {
            diario1.cadastraRegistro("Meu registro", "Guardei este texto no arquivo", 15, 7);
            diario1.salvarDados();

            diario2.recuperarDados();

            Collection<RegistroDiario> registros = diario2.pesquisaRegistrosPorData(15, 7);
            assertEquals(1, registros.size());

        } catch (IOException e) {
            fail("Exceção não esperada");
        }
    }
}