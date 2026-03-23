package br.ufpb.dcx.alexandria.diario;

import java.util.Objects;

public class RegistroDiario {
    private String data;
    private String texto;


    public RegistroDiario(String data, String texto){
        this.texto = texto;
        this.data = data;
    }

    public RegistroDiario(){
        this("","");
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

                //( busca por palavra)
    public boolean contemPalavra(String termo){
        return texto.toLowerCase().contains(termo.toLowerCase());
    }

    public String toString(){
        return "Data: "+ this.data + "\nTexto: " + texto;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistroDiario)) return false;
        RegistroDiario that = (RegistroDiario) o;
        return Objects.equals(data, that.data) &&
                Objects.equals(texto, that.texto);
    }


    @Override
    public int hashCode() {
        return Objects.hash(data, texto);
    }
}
