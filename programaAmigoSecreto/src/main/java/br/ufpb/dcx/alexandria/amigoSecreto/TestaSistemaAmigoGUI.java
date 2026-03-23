package br.ufpb.dcx.alexandria.amigoSecreto;

import javax.swing.JOptionPane;

public class TestaSistemaAmigoGUI {

    public static void main(String[] args) {

        SistemaAmigo sistema = new SistemaAmigo();

        int quantidade = Integer.parseInt(
                JOptionPane.showInputDialog("Digite a quantidade de amigos:")
        );

        for (int i = 0; i < quantidade; i++) {
            String nome = JOptionPane.showInputDialog("Digite o nome do amigo " + (i + 1) + ":");
            String email = JOptionPane.showInputDialog("Digite o email do amigo " + (i + 1) + ":");
            sistema.cadastraAmigo(nome, email);
        }

        for (int i = 0; i < quantidade; i++) {
            String emailDaPessoa = JOptionPane.showInputDialog("Digite o email da pessoa:");
            String emailAmigoSorteado = JOptionPane.showInputDialog("Digite o email do amigo sorteado:");

            try {
                sistema.configuraAmigoSecretoDe(emailDaPessoa, emailAmigoSorteado);
            } catch (AmigoInexistenteException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        String remetente = JOptionPane.showInputDialog("Digite o email do remetente:");
        String texto = JOptionPane.showInputDialog("Digite o texto da mensagem:");
        int opcao = JOptionPane.showConfirmDialog(
                null,
                "A mensagem é anônima?",
                "Mensagem Anônima",
                JOptionPane.YES_NO_OPTION
        );

        boolean anonima = (opcao == JOptionPane.YES_OPTION);

        sistema.enviarMensagemParaTodos(texto, remetente, anonima);

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Mensagem enviada com sucesso.\n\n");
        relatorio.append("Mensagens cadastradas:\n");

        for (Mensagem m : sistema.pesquisaTodasAsMensagens()) {
            relatorio.append(m.getTextoCompletoAExibir()).append("\n");
        }

        JOptionPane.showMessageDialog(null, relatorio.toString());
    }
}