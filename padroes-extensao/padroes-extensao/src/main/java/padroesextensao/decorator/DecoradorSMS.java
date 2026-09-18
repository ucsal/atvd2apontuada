package padroesextensao.decorator;

public class DecoradorSMS extends NotificacaoDecorator {

    private static final double CUSTO_SMS = 0.05;

    public DecoradorSMS(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public String enviar(String destinatario) {
        return super.enviar(destinatario) + "\n📱 SMS enviado para " + destinatario;
    }

    @Override
    public double custo() {
        return super.custo() + CUSTO_SMS;
    }
}