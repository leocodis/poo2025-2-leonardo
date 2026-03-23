package br.ufpb.dcx.alexandria.amigoSecreto;

public class MensagemParaAlguem extends Mensagem {

    private String emailDestinatario;

    public MensagemParaAlguem(String texto, String emailRemetente,
                              String emailDestinatario, boolean anonima) {
        super(texto, emailRemetente, anonima);
        this.emailDestinatario = emailDestinatario;
    }

    @Override
    public String getTextoCompletoAExibir() {
        if (anonima) {
            return "Mensagem para " + emailDestinatario + ". Texto: " + texto;
        } else {
            return "Mensagem de: " + emailRemetente +
                    " para " + emailDestinatario +
                    ". Texto: " + texto;
        }
    }
}
