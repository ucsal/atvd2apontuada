package padroesextensao.decorator;

public class DecoradorPush extends NotificacaoDecorator {

    private static final double CUSTO_PUSH = 0.02;

    public DecoradorPush(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public String enviar(String destinatario) {
        return super.enviar(destinatario) + "\n🔔 Push enviado para " + destinatario;
    }

    @Override
    public double custo() {
        return super.custo() + CUSTO_PUSH;
    }
}