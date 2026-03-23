package br.ufpb.dcx.alexandria.amigoSecreto;

public class SistemaAmigoMapTest {

    public static void main(String[] args) {

        SistemaAmigoMap sistema = new SistemaAmigoMap();

        sistema.cadastraAmigo("José", "jose@email.com");
        sistema.cadastraAmigo("Maria", "maria@email.com");

        try {
            sistema.configuraAmigoSecretoDe("jose@email.com", "maria@email.com");
            sistema.configuraAmigoSecretoDe("maria@email.com", "jose@email.com");
        } catch (AmigoInexistenteException e) {
            System.out.println(e.getMessage());
        }

        sistema.enviarMensagemParaAlguem(
                "Oi José!", "maria@email.com", "jose@email.com", true
        );

        sistema.enviarMensagemParaTodos(
                "Feliz amigo secreto!", "maria@email.com", true
        );

        for (Mensagem m : sistema.pesquisaMensagensAnonimas()) {
            System.out.println(m.getTextoCompletoAExibir());
        }

        try {
            String amigo = sistema.pesquisaAmigoSecretoDe("jose@email.com");

            if (amigo.equals("maria@email.com")) {
                System.out.println("Ok");
            }

        } catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
            System.out.println(e.getMessage());
        }

        sistema.sortear();

        try {
            System.out.println("José tirou: " + sistema.pesquisaAmigoSecretoDe("jose@email.com"));
            System.out.println("Maria tirou: " + sistema.pesquisaAmigoSecretoDe("maria@email.com"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}