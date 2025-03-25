package fr.poutchinystudio.whirling_back.user;

import fr.poutchinystudio.whirling_back.dto.OneValueObject;
import fr.poutchinystudio.whirling_back.identification.IdCredentials;
import fr.poutchinystudio.whirling_back.jwt.JwtUtils;
import fr.poutchinystudio.whirling_back.util.Utils;
import fr.poutchinystudio.whirling_back.identification.Identification;
import fr.poutchinystudio.whirling_back.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final MessageService messageService;
    private final JwtUtils jwtUtils;

    private User getUser() throws NoSuchElementException {
        Optional<User> optionalUser = repository.findById(Utils.jwtUserId());
        return optionalUser.get();
    }

    public User findById(String id) throws NoSuchElementException {
        Optional<User> optionalUser = repository.findById(id);
        return optionalUser.get();
    }

    public IdCredentials register(Identification identification) {
        Optional<User> optionalUser = repository.findById(identification.getUserId());

        if (optionalUser.isPresent() && identification.getUserId().equals(optionalUser.get().getUsername())) {
            System.out.println("REGISTER: User is present");
            String jwt = jwtUtils.generateToken(optionalUser.get().getUsername());
            return new IdCredentials(optionalUser.get().getId(), optionalUser.get().getName(), jwt);
        }

        User correctUser = new User(userIdGeneration(), userNameGeneration(), null);
        repository.save(correctUser);

        System.out.println("REGISTER: Create new jwt");
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
        String oldName = user.getName();

        if (newName.length() == 0 || newName.equals("GAME")) return user;

        Optional<User> userWithNewName = repository.findByName(newName);
        if (userWithNewName.isPresent()) return user;

        if (newName.length() > 22) newName = newName.substring(0, 22);
        user.setName(newName);
        repository.save(user);

        messageService.sendMessage("global", new OneValueObject(oldName + " a changé de nom pour : " + newName), true);

        return user;
    }

    public String idToName(String id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isEmpty()) return null;
        return optionalUser.get().getName();
    }
}
