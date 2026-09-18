package padroesextensao.decorator;

public class ExemploDecorator {

    public static void main(String[] args) {
        System.out.println("=== Sistema de Notificações – Padrão Decorator ===\n");

        Notificacao simples = new NotificacaoSimples();
        System.out.println("--- Notificação Simples (E-mail) ---");
        System.out.println(simples.enviar("joao@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", simples.custo()) + "\n");

        Notificacao emailSms = new DecoradorSMS(simples);
        System.out.println("--- E-mail + SMS ---");
        System.out.println(emailSms.enviar("maria@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", emailSms.custo()) + "\n");

        Notificacao emailSmsPush = new DecoradorPush(emailSms);
        System.out.println("--- E-mail + SMS + Push ---");
        System.out.println(emailSmsPush.enviar("carlos@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", emailSmsPush.custo()) + "\n");

        Notificacao completa = new DecoradorWhatsApp(emailSmsPush);
        System.out.println("--- E-mail + SMS + Push + WhatsApp ---");
        System.out.println(completa.enviar("ana@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", completa.custo()) + "\n");

        Notificacao emailComLog = new DecoradorLog(simples);
        System.out.println("--- E-mail + Log ---");
        System.out.println(emailComLog.enviar("pedro@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", emailComLog.custo()) + "\n");

        Notificacao comboTotal = new DecoradorLog(new DecoradorWhatsApp(new DecoradorPush(new DecoradorSMS(new NotificacaoSimples()))));
        System.out.println("--- Combo: E-mail + SMS + Push + WhatsApp + Log ---");
        System.out.println(comboTotal.enviar("lucas@email.com"));
        System.out.println("Custo: R$ " + String.format("%.2f", comboTotal.custo()) + "\n");
    }
}