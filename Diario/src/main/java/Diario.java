import java.util.Collection;
import java.io.IOException;

public interface Diario {

    boolean cadastraRegistro(String titulo, String texto, int dia, int mes, String categoria);

    Collection<RegistroDiario> pesquisaRegistrosPorData(int dia, int mes);

    Collection<RegistroDiario> pesquisaRegistrosPorTitulo(String trechoTitulo);

    Collection<RegistroDiario> pesquisaRegistrosPorCategoria(String nomeCategoria);

    boolean removeRegistro(String titulo) throws RegistroInexistenteException;

    boolean atualizaTextoRegistro(String titulo, String novoTexto) throws RegistroInexistenteException;

    boolean atualizaCategoriaRegistro(String titulo, String novaCategoria) throws RegistroInexistenteException;

    int quantidadeRegistros();

    Collection<RegistroDiario> listarTodosRegistros();

    void salvarDados() throws IOException;

    void recuperarDados() throws IOException;
}