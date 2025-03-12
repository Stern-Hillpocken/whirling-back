package fr.poutchinystudio.whirling_back.message;

import fr.poutchinystudio.whirling_back.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;

    public void sendMessage(String channel, MessageInput messageInput) {
        if (userRepository.findById(messageInput.getIdentification().getUserId()).isEmpty()) return;
        MessageOutput messageOutput = new MessageOutput(
                messageInput.getIdentification().getUserName(),
                messageInput.getContent(),
                System.currentTimeMillis()
        );
        messagingTemplate.convertAndSend(
                "/messages/" + channel + "/notifications",
                messageOutput
        );
    }
}
