package br.ufpb.dcx.alexandria.amigoSecreto;

import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {

    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public SistemaAmigo() {
        this.mensagens = new ArrayList<>();
        this.amigos = new ArrayList<>();
    }

    public void cadastraAmigo(String nome, String email) {
        Amigo novoAmigo = new Amigo(nome, email);
        amigos.add(novoAmigo);
    }

    public Amigo pesquisaAmigo(String email) throws AmigoInexistenteException {
        for (Amigo a : amigos) {
            if (a.getEmail().equals(email)) {
                return a;
            }
        }
        throw new AmigoInexistenteException("Amigo com email " + email + " não encontrado.");
    }

    public void enviarMensagemParaAlguem(String texto, String emailRemetente,
                                         String emailDestinatario, boolean ehAnonima) {
        Mensagem novaMensagem = new MensagemParaAlguem(
                texto, emailRemetente, emailDestinatario, ehAnonima
        );
        mensagens.add(novaMensagem);
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente,
                                        boolean ehAnonima) {
        Mensagem novaMensagem = new MensagemParaTodos(
                texto, emailRemetente, ehAnonima
        );
        mensagens.add(novaMensagem);
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> mensagensAnonimas = new ArrayList<>();

        for (Mensagem m : mensagens) {
            if (m.ehAnonima()) {
                mensagensAnonimas.add(m);
            }
        }

        return mensagensAnonimas;
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado)
            throws AmigoInexistenteException {

        Amigo pessoa = null;

        for (Amigo a : amigos) {
            if (a.getEmail().equals(emailDaPessoa)) {
                pessoa = a;
                break;
            }
        }

        if (pessoa == null) {
            throw new AmigoInexistenteException(
                    "Amigo com email " + emailDaPessoa + " não encontrado."
            );
        }

        pessoa.setEmailAmigoSorteado(emailAmigoSorteado);
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {
        return mensagens;
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa)
            throws AmigoInexistenteException, AmigoNaoSorteadoException {

        Amigo pessoa = null;

        for (Amigo a : amigos) {
            if (a.getEmail().equals(emailDaPessoa)) {
                pessoa = a;
                break;
            }
        }

        if (pessoa == null) {
            throw new AmigoInexistenteException(
                    "Amigo com email " + emailDaPessoa + " não encontrado."
            );
        }

        if (pessoa.getEmailAmigoSorteado() == null) {
            throw new AmigoNaoSorteadoException(
                    "Amigo secreto de " + emailDaPessoa + " ainda não foi sorteado."
            );
        }

        return pessoa.getEmailAmigoSorteado();
    }

    public void sortear() {
        List<Amigo> sorteados = new ArrayList<>(amigos);

        for (Amigo a : amigos) {
            if (sorteados.size() == 1 && sorteados.get(0).getEmail().equals(a.getEmail())) {
                sortear();
                return;
            }

            int posicaoSorteada = (int) (Math.random() * sorteados.size());
            Amigo amigoSorteado = sorteados.get(posicaoSorteada);

            while (amigoSorteado.getEmail().equals(a.getEmail())) {
                posicaoSorteada = (int) (Math.random() * sorteados.size());
                amigoSorteado = sorteados.get(posicaoSorteada);
            }

            a.setEmailAmigoSorteado(amigoSorteado.getEmail());
            sorteados.remove(amigoSorteado);
        }
    }
}
