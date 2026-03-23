package br.ufpb.dcx.alexandria.diario;

import java.util.ArrayList;

public class SistemaDiario {

    private ArrayList<RegistroDiario> registros;

    public SistemaDiario(){
        registros = new ArrayList<>();
    }

    public void adicionarRegistro(RegistroDiario r) {
        registros.add(r);
    }
    //busca pelo termo (se não encontrar lança uma exceção)
    public ArrayList<RegistroDiario> buscarPorTermo(String termo) throws Exception{
        ArrayList<RegistroDiario> encontrados = new ArrayList<>();

        for(RegistroDiario r : registros){
            if(r.contemPalavra(termo)){
                encontrados.add(r);
            }
        }
        if( encontrados.isEmpty()){
            throw new Exception("Nenhum registro encontrado com esse termo!");
        }
        return encontrados;
    }
    public void listarTodos(){
        for( RegistroDiario r : registros){
            IO.println(r);
            IO.println("----------");
        }
    }

}
