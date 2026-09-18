package padroesextensao.decorator;

public class DecoradorLog extends NotificacaoDecorator {

    public DecoradorLog(Notificacao notificacao) {
        super(notificacao);
    }

    @Override
    public String enviar(String destinatario) {
        String resultado = super.enviar(destinatario);
        System.out.println("[LOG] Notificação registrada para " + destinatario + " às " + java.time.LocalDateTime.now());
        return resultado + "\n📝 Log registrado";
    }

    @Override
    public double custo() {
        return super.custo();
    }
}