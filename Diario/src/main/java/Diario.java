import java.util.Collection;
import java.io.IOException;

public interface Diario {
    boolean cadastraRegistro(String titulo, String texto, int dia, int mes);
    Collection<RegistroDiario> pesquisaRegistrosPorData(int dia, int mes);
    boolean removeRegistro(String titulo) throws RegistroInexistenteException;
    void salvarDados() throws IOException;
    void recuperarDados() throws IOException;
}