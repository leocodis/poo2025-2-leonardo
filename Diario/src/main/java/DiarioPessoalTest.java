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

    @Test
    public void testaPesquisaPorTitulo() {
        DiarioPessoal diario = new DiarioPessoal();

        diario.cadastraRegistro("Dia feliz", "Hoje foi ótimo", 10, 6);
        diario.cadastraRegistro("Dia triste", "Hoje foi difícil", 11, 6);
        diario.cadastraRegistro("Estudo Java", "Aprendi streams", 12, 6);

        Collection<RegistroDiario> encontrados = diario.pesquisaRegistrosPorTitulo("Dia");
        assertEquals(2, encontrados.size());
    }

    @Test
    public void testaAtualizacaoDeTexto() {
        DiarioPessoal diario = new DiarioPessoal();
        diario.cadastraRegistro("Meu dia", "Texto antigo", 1, 1);

        try {
            assertTrue(diario.atualizaTextoRegistro("Meu dia", "Texto novo"));
            Collection<RegistroDiario> registros = diario.pesquisaRegistrosPorTitulo("Meu dia");
            RegistroDiario r = registros.iterator().next();
            assertEquals("Texto novo", r.getTexto());
        } catch (RegistroInexistenteException e) {
            fail("Exceção não esperada");
        }
    }

    @Test
    public void testaQuantidadeRegistros() {
        DiarioPessoal diario = new DiarioPessoal();

        diario.cadastraRegistro("Registro 1", "Texto 1", 1, 1);
        diario.cadastraRegistro("Registro 2", "Texto 2", 2, 2);
        diario.cadastraRegistro("Registro 3", "Texto 3", 3, 3);

        assertEquals(3, diario.quantidadeRegistros());
    }

    @Test
    public void testaListarTodosRegistros() {
        DiarioPessoal diario = new DiarioPessoal();

        diario.cadastraRegistro("Registro A", "Texto A", 1, 1);
        diario.cadastraRegistro("Registro B", "Texto B", 2, 2);

        Collection<RegistroDiario> todos = diario.listarTodosRegistros();
        assertEquals(2, todos.size());
    }
}