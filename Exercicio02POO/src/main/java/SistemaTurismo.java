import java.util.ArrayList;

public class SistemaTurismo {

    private ArrayList<PacoteTuristico> pacotes;

    public SistemaTurismo() {
        pacotes = new ArrayList<>();
    }

    public void adicionarPacote(PacoteTuristico p) throws Exception {

        if (pacotes.contains(p)) {
            throw new Exception("Pacote para esse destino já existe!");
        }

        pacotes.add(p);
    }

    public void listarPacotes() {

        for (PacoteTuristico p : pacotes) {
            System.out.println(p);
            System.out.println("--------------------");
        }
    }
}