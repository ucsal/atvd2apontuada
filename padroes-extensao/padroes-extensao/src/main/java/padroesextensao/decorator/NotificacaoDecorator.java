package padroesextensao.decorator;

public abstract class NotificacaoDecorator implements Notificacao {

    protected Notificacao notificacaoEnvolvida;

    public NotificacaoDecorator(Notificacao notificacao) {
        this.notificacaoEnvolvida = notificacao;
    }

    @Override
    public String enviar(String destinatario) {
        return notificacaoEnvolvida.enviar(destinatario);
    }

    @Override
    public double custo() {
        return notificacaoEnvolvida.custo();
    }
}