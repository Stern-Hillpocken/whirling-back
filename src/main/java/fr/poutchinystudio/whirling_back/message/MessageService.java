package fr.poutchinystudio.whirling_back.message;

import fr.poutchinystudio.whirling_back.dto.OneValueObject;
import fr.poutchinystudio.whirling_back.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;

    public void sendMessage(String channel, OneValueObject message, boolean isGameSending) {
        String naming = isGameSending ? "GAME" : userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName()).get().getName();
        MessageOutput messageOutput = new MessageOutput(
            naming,
            message.value,
            System.currentTimeMillis()
        );
        messagingTemplate.convertAndSend(
                "/messages/" + channel + "/notifications",
                messageOutput
        );
    }
}
