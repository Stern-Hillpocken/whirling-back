package fr.poutchinystudio.whirling_back.message;

import fr.poutchinystudio.whirling_back.dto.OneValueObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/global")
    public void sendGlobalMessage(
            @RequestBody OneValueObject message
    ) {
        messageService.sendMessage("global", message, false);
    }

    @PostMapping("/**")
    public void sendIngameMessage(
            @RequestBody OneValueObject message
    ) {
        messageService.sendMessage("ingame", message, false);
    }
}
