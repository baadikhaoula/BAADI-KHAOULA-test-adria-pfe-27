package ma.khaoulabaadi.chatbotservice.telegram;

import ma.khaoulabaadi.chatbotservice.service.AiAgentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final AiAgentService aiAgentService;

    @Value("${telegram.bot.username}")
    private String botUsername;

    public TelegramBot(@Value("${telegram.bot.token}") String botToken,
                       AiAgentService aiAgentService) {
        super(botToken);
        this.aiAgentService = aiAgentService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String userMessage = update.getMessage().getText();

            try {
                // Appeler l'IA pour générer une réponse
                String response = aiAgentService.chat(userMessage, chatId);

                // Envoyer la réponse à l'utilisateur
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText(response);
                execute(message);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}