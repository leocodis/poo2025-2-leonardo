package br.ufpb.dcx.alexandria.amigoSecreto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaAmigoMap {

    private Map<String, Amigo> amigos;
    private List<Mensagem> mensagens;

    public SistemaAmigoMap() {
        this.amigos = new HashMap<>();
        this.mensagens = new ArrayList<>();
    }

    public void cadastraAmigo(String nome, String email) {
        Amigo amigo = new Amigo(nome, email);
        amigos.put(email, amigo);
    }

    public Amigo pesquisaAmigo(String email) throws AmigoInexistenteException {
        Amigo amigo = amigos.get(email);

        if (amigo == null) {
            throw new AmigoInexistenteException("Amigo com email " + email + " não encontrado.");
        }

        return amigo;
    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado)
            throws AmigoInexistenteException {

        Amigo pessoa = amigos.get(emailDaPessoa);

        if (pessoa == null) {
            throw new AmigoInexistenteException("Amigo com email " + emailDaPessoa + " não encontrado.");
        }

        pessoa.setEmailAmigoSorteado(emailAmigoSorteado);
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa)
            throws AmigoInexistenteException, AmigoNaoSorteadoException {

        Amigo pessoa = amigos.get(emailDaPessoa);

        if (pessoa == null) {
            throw new AmigoInexistenteException("Amigo com email " + emailDaPessoa + " não encontrado.");
        }

        if (pessoa.getEmailAmigoSorteado() == null) {
            throw new AmigoNaoSorteadoException("Amigo secreto ainda não foi sorteado.");
        }

        return pessoa.getEmailAmigoSorteado();
    }

    public void enviarMensagemParaAlguem(String texto, String emailRemetente,
                                         String emailDestinatario, boolean anonima) {

        Mensagem m = new MensagemParaAlguem(texto, emailRemetente, emailDestinatario, anonima);
        mensagens.add(m);
    }

    public void enviarMensagemParaTodos(String texto, String emailRemetente,
                                        boolean anonima) {

        Mensagem m = new MensagemParaTodos(texto, emailRemetente, anonima);
        mensagens.add(m);
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> resultado = new ArrayList<>();

        for (Mensagem m : mensagens) {
            if (m.ehAnonima()) {
                resultado.add(m);
            }
        }

        return resultado;
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {
        return mensagens;
    }

    public void sortear() {
        List<Amigo> lista = new ArrayList<>(amigos.values());

        List<Amigo> sorteados = new ArrayList<>(lista);

        for (Amigo a : lista) {
            if (sorteados.size() == 1 && sorteados.get(0).getEmail().equals(a.getEmail())) {
                sortear();
                return;
            }

            int pos = (int) (Math.random() * sorteados.size());
            Amigo sorteado = sorteados.get(pos);

            while (sorteado.getEmail().equals(a.getEmail())) {
                pos = (int) (Math.random() * sorteados.size());
                sorteado = sorteados.get(pos);
            }

            a.setEmailAmigoSorteado(sorteado.getEmail());
            sorteados.remove(sorteado);
        }
    }
}