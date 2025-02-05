package fr.poutchinystudio.whirling_back.identification;

import fr.poutchinystudio.whirling_back.message.MessageInput;
import fr.poutchinystudio.whirling_back.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IdentificationService {

    @Autowired
    private IdentificationRepository repository;

    @Autowired
    private MessageService messageService;

    public Identification identificationCheck(Identification identification) {
        Optional<Identification> optionalIdentification = repository.findByUserId(identification.getUserId());
        if (optionalIdentification.isPresent() && identification.getUserName().equals(optionalIdentification.get().getUserName())) return optionalIdentification.get();

        Identification correctIdentification = new Identification(userIdGeneration(), userNameGeneration());
        repository.save(correctIdentification);
        return correctIdentification;
    }

    private String userNameGeneration() {
        List<String> usernames = Arrays.asList("Anqi Sheng", "Hermes Trismegistus", "Ostanes", "Nicolas Flamel", "Perenelle Flamel", "Christian Rosenkreuz", "Abraham Eleazar", "Diablo", "Alphonse Elric", "Edward Elric");
        return usernames.get((int)(Math.random() * usernames.size()));
    }

    private String userIdGeneration() {
        String userId;
        do {
            userId = letterShuffler(3) + "-" + letterShuffler(4) + "-" + letterShuffler(3);
        } while (repository.findByUserId(userId).isPresent());
        return userId;
    }

    private String letterShuffler(int times) {
        String shuffled = "";
        for (int i = 0; i < times; i++) {
            shuffled += (char)(new Random().nextInt(26) + 'a');
        }
        return shuffled;
    }

    public Identification updateUserName(IdentificationToUpdate identificationToUpdate) {
        Optional<Identification> optionalIdentification = repository.findByUserId(identificationToUpdate.getIdentification().getUserId());
        if (optionalIdentification.isEmpty() || !optionalIdentification.get().getUserName().equals(identificationToUpdate.getIdentification().getUserName())) return identificationToUpdate.getIdentification();

        String newUserName = identificationToUpdate.getNewUserName();
        if (newUserName.length() > 22) newUserName = newUserName.substring(0, 22);
        Identification updatedUser = new Identification(identificationToUpdate.getIdentification().getUserId(), newUserName);
        repository.save(updatedUser);

        messageService.sendMessage("global", new MessageInput(updatedUser, identificationToUpdate.getIdentification().getUserName() + " >>> " + newUserName));

        return updatedUser;
    }
}
