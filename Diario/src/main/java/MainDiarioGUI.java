import javax.swing.*;
import java.io.IOException;
import java.util.Collection;

public class MainDiarioGUI {

    public static void main(String[] args) {
        DiarioPessoal diario = new DiarioPessoal();

        try {
            diario.recuperarDados();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar os dados.");
        }

        JFrame frame = new JFrame("Diário Pessoal");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuArquivo = new JMenu("Arquivo");
        JMenu menuRegistros = new JMenu("Registros");
        JMenu menuPesquisa = new JMenu("Pesquisa");
        JMenu menuExtras = new JMenu("Extras");

        JMenuItem itemSalvar = new JMenuItem("Salvar");
        JMenuItem itemRecuperar = new JMenuItem("Recuperar Dados");
        JMenuItem itemSair = new JMenuItem("Sair");

        JMenuItem itemCadastrar = new JMenuItem("Cadastrar Registro");
        JMenuItem itemRemover = new JMenuItem("Remover Registro");
        JMenuItem itemAtualizarTexto = new JMenuItem("Atualizar Texto");
        JMenuItem itemAtualizarCategoria = new JMenuItem("Atualizar Categoria");

        JMenuItem itemPesquisarData = new JMenuItem("Pesquisar por Data");
        JMenuItem itemPesquisarTitulo = new JMenuItem("Pesquisar por Título");
        JMenuItem itemPesquisarCategoria = new JMenuItem("Pesquisar por Categoria");

        JMenuItem itemQuantidade = new JMenuItem("Quantidade de Registros");
        JMenuItem itemListarTodos = new JMenuItem("Listar Todos");

        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemRecuperar);
        menuArquivo.add(itemSair);

        menuRegistros.add(itemCadastrar);
        menuRegistros.add(itemRemover);
        menuRegistros.add(itemAtualizarTexto);
        menuRegistros.add(itemAtualizarCategoria);

        menuPesquisa.add(itemPesquisarData);
        menuPesquisa.add(itemPesquisarTitulo);
        menuPesquisa.add(itemPesquisarCategoria);

        menuExtras.add(itemQuantidade);
        menuExtras.add(itemListarTodos);

        menuBar.add(menuArquivo);
        menuBar.add(menuRegistros);
        menuBar.add(menuPesquisa);
        menuBar.add(menuExtras);

        frame.setJMenuBar(menuBar);

        JLabel label = new JLabel("Sistema Diário Pessoal", SwingConstants.CENTER);
        frame.add(label);

        itemSalvar.addActionListener(event -> {
            try {
                diario.salvarDados();
                JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao salvar os dados.");
            }
        });

        itemRecuperar.addActionListener(event -> {
            try {
                diario.recuperarDados();
                JOptionPane.showMessageDialog(frame, "Dados recuperados com sucesso.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Erro ao recuperar os dados.");
            }
        });

        itemSair.addActionListener(event -> {
            System.exit(0);
        });

        itemCadastrar.addActionListener(event -> {
            try {
                String titulo = JOptionPane.showInputDialog(frame, "Título:");
                if (titulo == null) return;

                String texto = JOptionPane.showInputDialog(frame, "Texto:");
                if (texto == null) return;

                String diaStr = JOptionPane.showInputDialog(frame, "Dia:");
                if (diaStr == null) return;

                String mesStr = JOptionPane.showInputDialog(frame, "Mês:");
                if (mesStr == null) return;

                String categoria = JOptionPane.showInputDialog(frame, "Categoria:");
                if (categoria == null) return;

                int dia = Integer.parseInt(diaStr);
                int mes = Integer.parseInt(mesStr);

                boolean cadastrou = diario.cadastraRegistro(titulo, texto, dia, mes, categoria);

                if (cadastrou) {
                    JOptionPane.showMessageDialog(frame, "Registro cadastrado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Já existe um registro com esse título.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Dia e mês devem ser números inteiros.");
            }
        });

        itemRemover.addActionListener(event -> {
            String titulo = JOptionPane.showInputDialog(frame, "Título do registro a remover:");
            if (titulo == null) return;

            try {
                diario.removeRegistro(titulo);
                JOptionPane.showMessageDialog(frame, "Registro removido com sucesso.");
            } catch (RegistroInexistenteException ex) {
                JOptionPane.showMessageDialog(frame, "Registro não encontrado.");
            }
        });

        itemAtualizarTexto.addActionListener(event -> {
            String titulo = JOptionPane.showInputDialog(frame, "Título do registro:");
            if (titulo == null) return;

            String novoTexto = JOptionPane.showInputDialog(frame, "Novo texto:");
            if (novoTexto == null) return;

            try {
                diario.atualizaTextoRegistro(titulo, novoTexto);
                JOptionPane.showMessageDialog(frame, "Texto atualizado com sucesso.");
            } catch (RegistroInexistenteException ex) {
                JOptionPane.showMessageDialog(frame, "Registro não encontrado.");
            }
        });

        itemAtualizarCategoria.addActionListener(event -> {
            String titulo = JOptionPane.showInputDialog(frame, "Título do registro:");
            if (titulo == null) return;

            String novaCategoria = JOptionPane.showInputDialog(frame, "Nova categoria:");
            if (novaCategoria == null) return;

            try {
                diario.atualizaCategoriaRegistro(titulo, novaCategoria);
                JOptionPane.showMessageDialog(frame, "Categoria atualizada com sucesso.");
            } catch (RegistroInexistenteException ex) {
                JOptionPane.showMessageDialog(frame, "Registro não encontrado.");
            }
        });

        itemPesquisarData.addActionListener(event -> {
            try {
                String diaStr = JOptionPane.showInputDialog(frame, "Dia:");
                if (diaStr == null) return;

                String mesStr = JOptionPane.showInputDialog(frame, "Mês:");
                if (mesStr == null) return;

                int dia = Integer.parseInt(diaStr);
                int mes = Integer.parseInt(mesStr);

                Collection<RegistroDiario> registros = diario.pesquisaRegistrosPorData(dia, mes);

                if (registros.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nenhum registro encontrado.");
                } else {
                    StringBuilder resultado = new StringBuilder();
                    for (RegistroDiario r : registros) {
                        resultado.append(r).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, resultado.toString());
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Dia e mês devem ser números inteiros.");
            }
        });

        itemPesquisarTitulo.addActionListener(event -> {
            String trecho = JOptionPane.showInputDialog(frame, "Digite parte do título:");
            if (trecho == null) return;

            Collection<RegistroDiario> registros = diario.pesquisaRegistrosPorTitulo(trecho);

            if (registros.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum registro encontrado.");
            } else {
                StringBuilder resultado = new StringBuilder();
                for (RegistroDiario r : registros) {
                    resultado.append(r).append("\n");
                }
                JOptionPane.showMessageDialog(frame, resultado.toString());
            }
        });

        itemPesquisarCategoria.addActionListener(event -> {
            String categoria = JOptionPane.showInputDialog(frame, "Digite a categoria:");
            if (categoria == null) return;

            Collection<RegistroDiario> registros = diario.pesquisaRegistrosPorCategoria(categoria);

            if (registros.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum registro encontrado.");
            } else {
                StringBuilder resultado = new StringBuilder();
                for (RegistroDiario r : registros) {
                    resultado.append(r).append("\n");
                }
                JOptionPane.showMessageDialog(frame, resultado.toString());
            }
        });

        itemQuantidade.addActionListener(event -> {
            int quantidade = diario.quantidadeRegistros();
            JOptionPane.showMessageDialog(frame, "Quantidade de registros: " + quantidade);
        });

        itemListarTodos.addActionListener(event -> {
            Collection<RegistroDiario> registros = diario.listarTodosRegistros();

            if (registros.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhum registro cadastrado.");
            } else {
                StringBuilder resultado = new StringBuilder();
                for (RegistroDiario r : registros) {
                    resultado.append(r).append("\n");
                }
                JOptionPane.showMessageDialog(frame, resultado.toString());
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}