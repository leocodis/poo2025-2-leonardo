import java.util.Objects;

public class PacoteTuristico {

    private String destino;
    private double preco;
    private int dias;

    // Construtor vazio
    public PacoteTuristico() {
    }

    // Construtor com parâmetros
    public PacoteTuristico(String destino, double preco, int dias) {
        this.destino = destino;
        this.preco = preco;
        this.dias = dias;
    }

    // Gets
    public String getDestino() {
        return destino;
    }

    public double getPreco() {
        return preco;
    }

    public int getDias() {
        return dias;
    }

    // Sets
    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    // Método extra
    public double calcularPrecoPorDia() {
        return preco / dias;
    }

    // toString
    @Override
    public String toString() {
        return "Destino: " + destino +
                "\nPreço: " + preco +
                "\nDias: " + dias;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PacoteTuristico)) return false;
        PacoteTuristico p = (PacoteTuristico) o;
        return Objects.equals(destino, p.destino);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(destino);
    }
}