import java.io.Serializable;

public class RegistroDiario implements Serializable {
    private String titulo;
    private String texto;
    private int dia;
    private int mes;

    public RegistroDiario(String titulo, String texto, int dia, int mes) {
        this.titulo = titulo;
        this.texto = texto;
        this.dia = dia;
        this.mes = mes;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "Registro: " + titulo + " - Data: " + dia + "/" + mes + " - Texto: " + texto;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RegistroDiario)) {
            return false;
        }

        RegistroDiario outro = (RegistroDiario) obj;

        return this.titulo.equals(outro.titulo)
                && this.texto.equals(outro.texto)
                && this.dia == outro.dia
                && this.mes == outro.mes;
    }

    @Override
    public int hashCode() {
        return this.titulo.hashCode() + this.texto.hashCode() + this.dia + this.mes;
    }
}