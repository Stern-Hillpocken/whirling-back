package fr.poutchinystudio.whirling_back.message;

import fr.poutchinystudio.whirling_back.identification.IdentificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;
    private final IdentificationRepository identificationRepository;

    void sendMessage(String channel, MessageInput messageInput) {
        if (identificationRepository.findByUserId(messageInput.getIdentification().getUserId()).isEmpty()) return;
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
