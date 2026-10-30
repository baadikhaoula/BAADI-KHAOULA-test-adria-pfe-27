package ma.khaoulabaadi.chatbotservice.web.rest;

import ma.khaoulabaadi.chatbotservice.service.AiAgentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatbotController {

    private final AiAgentService aiAgentService;

    public ChatbotController(AiAgentService aiAgentService) {
        this.aiAgentService = aiAgentService;
    }

    @GetMapping
    public String chat(@RequestParam String message,
                       @RequestParam(defaultValue = "test-user") String conversationId) {
        return aiAgentService.chat(message, conversationId);
    }
}