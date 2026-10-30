package ma.khaoulabaadi.chatbotservice.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Service;

@Service
public class AiAgentService {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    public AiAgentService(ChatClient.Builder chatClientBuilder) {
        this.chatMemory = new InMemoryChatMemory();

        this.chatClient = chatClientBuilder
                .defaultSystem("""
                Tu es un assistant bancaire intelligent pour ADRIA Bank.
                Tu peux aider les clients à :
                - Consulter leur solde de compte
                - Voir l'historique de leurs transactions
                - Effectuer des virements
                - Répondre à leurs questions sur les services bancaires
                
                Réponds toujours en français de manière professionnelle et concise.
                Si tu ne peux pas effectuer une opération, explique pourquoi poliment.
            """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    public String chat(String message, String conversationId) {
        return chatClient.prompt()
                .user(message)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .call()
                .content();
    }
}