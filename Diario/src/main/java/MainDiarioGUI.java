import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.Collection;

public class MainDiarioGUI {

    public static void main(String[] args) {
        DiarioPessoal diario = new DiarioPessoal();

        try {
            diario.recuperarDados();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados");
        }

        int opcao = -1;

        while (opcao != 0) {
            String menu = "=== DIÁRIO PESSOAL ===\n"
                    + "1. Cadastrar registro\n"
                    + "2. Pesquisar por data\n"
                    + "3. Pesquisar por título\n"
                    + "4. Remover registro\n"
                    + "5. Atualizar texto de registro\n"
                    + "6. Mostrar quantidade de registros\n"
                    + "7. Listar todos os registros\n"
                    + "8. Salvar dados\n"
                    + "0. Sair";

            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcao) {

                case 1:
                    String titulo = JOptionPane.showInputDialog("Título:");
                    String texto = JOptionPane.showInputDialog("Texto:");
                    int dia = Integer.parseInt(JOptionPane.showInputDialog("Dia:"));
                    int mes = Integer.parseInt(JOptionPane.showInputDialog("Mês:"));

                    boolean cadastrado = diario.cadastraRegistro(titulo, texto, dia, mes);

                    if (cadastrado) {
                        JOptionPane.showMessageDialog(null, "Registro cadastrado!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Já existe um registro com esse título.");
                    }
                    break;

                case 2:
                    int diaBusca = Integer.parseInt(JOptionPane.showInputDialog("Dia:"));
                    int mesBusca = Integer.parseInt(JOptionPane.showInputDialog("Mês:"));

                    Collection<RegistroDiario> registrosData = diario.pesquisaRegistrosPorData(diaBusca, mesBusca);

                    if (registrosData.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.");
                    } else {
                        String resultado = "";
                        for (RegistroDiario r : registrosData) {
                            resultado += r.toString() + "\n";
                        }
                        JOptionPane.showMessageDialog(null, resultado);
                    }
                    break;

                case 3:
                    String trechoTitulo = JOptionPane.showInputDialog("Digite parte do título:");
                    Collection<RegistroDiario> registrosTitulo = diario.pesquisaRegistrosPorTitulo(trechoTitulo);

                    if (registrosTitulo.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.");
                    } else {
                        String resultado = "";
                        for (RegistroDiario r : registrosTitulo) {
                            resultado += r.toString() + "\n";
                        }
                        JOptionPane.showMessageDialog(null, resultado);
                    }
                    break;

                case 4:
                    String tituloRemover = JOptionPane.showInputDialog("Título do registro:");

                    try {
                        diario.removeRegistro(tituloRemover);
                        JOptionPane.showMessageDialog(null, "Registro removido!");
                    } catch (RegistroInexistenteException e) {
                        JOptionPane.showMessageDialog(null, "Registro não encontrado.");
                    }
                    break;

                case 5:
                    String tituloAtualizar = JOptionPane.showInputDialog("Título do registro:");
                    String novoTexto = JOptionPane.showInputDialog("Novo texto:");

                    try {
                        diario.atualizaTextoRegistro(tituloAtualizar, novoTexto);
                        JOptionPane.showMessageDialog(null, "Texto atualizado com sucesso!");
                    } catch (RegistroInexistenteException e) {
                        JOptionPane.showMessageDialog(null, "Registro não encontrado.");
                    }
                    break;

                case 6:
                    JOptionPane.showMessageDialog(null, "Quantidade de registros: " + diario.quantidadeRegistros());
                    break;

                case 7:
                    Collection<RegistroDiario> todos = diario.listarTodosRegistros();

                    if (todos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum registro cadastrado.");
                    } else {
                        String resultado = "";
                        for (RegistroDiario r : todos) {
                            resultado += r.toString() + "\n";
                        }
                        JOptionPane.showMessageDialog(null, resultado);
                    }
                    break;

                case 8:
                    try {
                        diario.salvarDados();
                        JOptionPane.showMessageDialog(null, "Dados salvos!");
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar.");
                    }
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }
}