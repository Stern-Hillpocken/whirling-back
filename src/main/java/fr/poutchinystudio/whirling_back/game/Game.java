package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.user.User;
import fr.poutchinystudio.whirling_back.user.UserRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {

    @Id
    String id;
    String password;
    String ownerId;
    long date;
    boolean isStarted;
    ArrayList<String> playersId;

    public void addPlayerId(String playerId) {
        playersId.add(playerId);
    }

    private String ownerName() {
        UserRepository userRepository = null;
        Optional<User> optionalUser = userRepository.findById(ownerId);
        return optionalUser.get().getName();
    }

    private ArrayList<String> playersName() {
        UserRepository userRepository = null;
        ArrayList<String> pNames = null;
        for (String pId : playersId) {
            pNames.add(userRepository.findById(pId).get().getName());
        }
        return pNames;
    }
}
