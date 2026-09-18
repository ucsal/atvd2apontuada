package padroesextensao.decorator;

public class DecoradorWhatsApp extends NotificacaoDecorator {

    private static final double CUSTO_WHATSAPP = 0.03;

    public DecoradorWhatsApp(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public String enviar(String destinatario) {
        return super.enviar(destinatario) + "\n💬 WhatsApp enviado para " + destinatario;
    }

    @Override
    public double custo() {
        return super.custo() + CUSTO_WHATSAPP;
    }
}