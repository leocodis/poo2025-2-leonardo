import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DiarioPessoal implements Diario {
    private Map<String, RegistroDiario> registros;
    private GravadorDeDados gravador;

    public DiarioPessoal() {
        this.registros = new HashMap<>();
        this.gravador = new GravadorDeDados();
    }

    @Override
    public boolean cadastraRegistro(String titulo, String texto, int dia, int mes) {
        if (this.registros.containsKey(titulo)) {
            return false;
        }

        this.registros.put(titulo, new RegistroDiario(titulo, texto, dia, mes));
        return true;
    }

    @Override
    public Collection<RegistroDiario> pesquisaRegistrosPorData(int dia, int mes) {
        Collection<RegistroDiario> encontrados = new ArrayList<>();

        for (RegistroDiario r : this.registros.values()) {
            if (r.getDia() == dia && r.getMes() == mes) {
                encontrados.add(r);
            }
        }

        return encontrados;
    }

    @Override
    public boolean removeRegistro(String titulo) throws RegistroInexistenteException {
        if (!this.registros.containsKey(titulo)) {
            throw new RegistroInexistenteException("Registro não encontrado: " + titulo);
        }

        this.registros.remove(titulo);
        return true;
    }

    @Override
    public void salvarDados() throws IOException {
        this.gravador.salvarRegistros(this.registros);
    }

    @Override
    public void recuperarDados() throws IOException {
        this.registros = this.gravador.recuperarRegistros();
    }
}