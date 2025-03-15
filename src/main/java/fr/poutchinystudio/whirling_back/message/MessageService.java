package fr.poutchinystudio.whirling_back.message;

import fr.poutchinystudio.whirling_back.dto.OneValueObject;
import fr.poutchinystudio.whirling_back.user.User;
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

    public void sendMessage(String channel, OneValueObject message) {
        MessageOutput messageOutput = new MessageOutput(
            userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName())
                    .get().getName(),
                message.value,
            System.currentTimeMillis()
        );
        System.out.println(userRepository.findById(SecurityContextHolder.getContext().getAuthentication().getName())
                .get().getName() + channel+" >in> "+message.value);
        messagingTemplate.convertAndSend(
                "/messages/" + channel + "/notifications",
                messageOutput
        );
    }
}
