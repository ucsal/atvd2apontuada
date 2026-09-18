package padroesextensao.decorator;

public class NotificacaoSimples implements Notificacao {

    @Override
    public String enviar(String destinatario) {
        return "📧 E-mail enviado para " + destinatario;
    }

    @Override
    public double custo() {
        return 0.0;
    }
}