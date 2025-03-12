package fr.poutchinystudio.whirling_back.user;

import fr.poutchinystudio.whirling_back.identification.IdCredentials;
import fr.poutchinystudio.whirling_back.jwt.JwtUtils;
import fr.poutchinystudio.whirling_back.util.Utils;
import fr.poutchinystudio.whirling_back.identification.Identification;
import fr.poutchinystudio.whirling_back.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MessageService messageService;

    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    private User getUser() throws NoSuchElementException {
        Optional<User> optionalUser = repository.findById(SecurityContextHolder.getContext().getAuthentication().getName());
        return optionalUser.get();
    }

    public IdCredentials register(Identification identification) {
        Optional<User> optionalUser = repository.findById(identification.getUserId());

        if (optionalUser.isPresent() && identification.getUserName().equals(optionalUser.get().getUsername())) {
            System.out.println("User is present");
            String jwt = jwtUtils.generateToken(optionalUser.get().getUsername());
            return new IdCredentials(optionalUser.get().getId(), optionalUser.get().getName(), jwt);
        }

        User correctUser = new User(userIdGeneration(), userNameGeneration(), null);
        repository.save(correctUser);

        System.out.println("Create new jwt");
        String jwt = jwtUtils.generateToken(correctUser.getUsername());

        System.out.println(correctUser.getId() + correctUser.getName() + jwt);

        return new IdCredentials(correctUser.getId(), correctUser.getName(), jwt);
    }

    private String userNameGeneration() {
        List<String> usernames = Arrays.asList("Anqi Sheng", "Hermes Trismegistus", "Ostanes", "Nicolas Flamel", "Perenelle Flamel", "Christian Rosenkreuz", "Abraham Eleazar", "Diablo", "Alphonse Elric", "Edward Elric");
        return usernames.get((int)(Math.random() * usernames.size()));
    }

    private String userIdGeneration() {
        String userId;
        do {
            userId = Utils.letterShuffler(3) + "-" + Utils.letterShuffler(4) + "-" + Utils.letterShuffler(3);
        } while (repository.findById(userId).isPresent());
        return userId;
    }

    public User updateName(String newName) {
        User user = getUser();

        if (newName.length() > 22) newName = newName.substring(0, 22);
        user.setName(newName);
        repository.save(user);

        //messageService.sendMessage("global", new MessageInput(new Identification("GAME", "GAME"), identificationToUpdate.getIdentification().getUserName() + " >>> " + newUserName));

        return user;
    }
}
