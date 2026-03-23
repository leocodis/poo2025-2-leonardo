package br.ufpb.dcx.alexandria.diario;

import java.util.ArrayList;
import java.util.Scanner;

public class ProgramaPrincipal {
    void main(String [] args){
        Scanner scanner = new Scanner(System.in);
        SistemaDiario sistema = new SistemaDiario();

        //adicionando registros
        int quantRegistros = 3;

        for(int i = quantRegistros;i<quantRegistros;i++){

            IO.println("\nRegistro" + (i + 1));

            IO.println("Data: ");
            String data = scanner.nextLine();

            IO.println("Texto do dia: ");
            String texto = scanner.nextLine();

            RegistroDiario r = new RegistroDiario(data,texto);
            sistema.adicionarRegistro(r);
        }
        //buscar registros
        IO.println("Digite um termo para buscar: ");
        String termo = scanner.nextLine();

        try{
            ArrayList<RegistroDiario> resultados = sistema.buscarPorTermo(termo);

            IO.println("\n ---- Resultados ----");
            for(RegistroDiario r : resultados){
                IO.println(r);
                IO.println("----------------");
            }

        }catch (Exception e){
            IO.println(e.getMessage());
        }
        scanner.close();
    }
}
