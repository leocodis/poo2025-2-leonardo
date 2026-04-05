import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class GravadorDeDados {
    public static final String ARQUIVO_REGISTROS = "registros.dat";

    public HashMap<String, RegistroDiario> recuperarRegistros() throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQUIVO_REGISTROS))) {
            return (HashMap<String, RegistroDiario>) in.readObject();
        } catch (FileNotFoundException e) {
            return new HashMap<>();
        } catch (ClassNotFoundException e) {
            throw new IOException("Erro ao recuperar os dados do arquivo.");
        }
    }

    public void salvarRegistros(Map<String, RegistroDiario> registros) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_REGISTROS))) {
            out.writeObject(registros);
        }
    }
}