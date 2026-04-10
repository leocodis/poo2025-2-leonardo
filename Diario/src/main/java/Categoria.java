import java.io.Serializable;

public class Categoria implements Serializable {
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Categoria)) {
            return false;
        }

        Categoria outra = (Categoria) obj;
        return this.nome.equalsIgnoreCase(outra.nome);
    }

    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }
}