import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DiarioPessoal implements Diario {
    private Map<String, RegistroDiario> registros;
    private GravadorDeDados gravador;

    public DiarioPessoal() {
        this.registros = new HashMap<>();
        this.gravador = new GravadorDeDados();
    }

    @Override
    public boolean cadastraRegistro(String titulo, String texto, int dia, int mes, String categoria) {
        if (this.registros.containsKey(titulo)) {
            return false;
        }

        Categoria cat = new Categoria(categoria);
        this.registros.put(titulo, new RegistroDiario(titulo, texto, dia, mes, cat));
        return true;
    }

    @Override
    public Collection<RegistroDiario> pesquisaRegistrosPorData(int dia, int mes) {
        return this.registros.values()
                .stream()
                .filter(r -> r.getDia() == dia && r.getMes() == mes)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<RegistroDiario> pesquisaRegistrosPorTitulo(String trechoTitulo) {
        return this.registros.values()
                .stream()
                .filter(r -> r.getTitulo().toLowerCase().contains(trechoTitulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<RegistroDiario> pesquisaRegistrosPorCategoria(String nomeCategoria) {
        return this.registros.values()
                .stream()
                .filter(r -> r.getCategoria().getNome().equalsIgnoreCase(nomeCategoria))
                .collect(Collectors.toList());
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
    public boolean atualizaTextoRegistro(String titulo, String novoTexto) throws RegistroInexistenteException {
        RegistroDiario registro = this.registros.get(titulo);

        if (registro == null) {
            throw new RegistroInexistenteException("Registro não encontrado: " + titulo);
        }

        registro.setTexto(novoTexto);
        return true;
    }

    @Override
    public boolean atualizaCategoriaRegistro(String titulo, String novaCategoria) throws RegistroInexistenteException {
        RegistroDiario registro = this.registros.get(titulo);

        if (registro == null) {
            throw new RegistroInexistenteException("Registro não encontrado: " + titulo);
        }

        registro.setCategoria(new Categoria(novaCategoria));
        return true;
    }

    @Override
    public int quantidadeRegistros() {
        return (int) this.registros.values()
                .stream()
                .count();
    }

    @Override
    public Collection<RegistroDiario> listarTodosRegistros() {
        return this.registros.values()
                .stream()
                .collect(Collectors.toList());
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