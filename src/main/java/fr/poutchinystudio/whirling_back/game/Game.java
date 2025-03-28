package fr.poutchinystudio.whirling_back.game;

import fr.poutchinystudio.whirling_back.user.User;
import fr.poutchinystudio.whirling_back.user.UserRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Game {

    @Id
    private String id;
    private String password;
    private String ownerId;
    private long date;
    private boolean isStarted;
    private List<String> playersId;

    public void addPlayerId(String playerId) {
        playersId.add(playerId);
    }

    private String ownerName() {
        UserRepository userRepository = null;
        Optional<User> optionalUser = userRepository.findById(ownerId);
        return optionalUser.get().getName();
    }

    private List<String> playersName() {
        UserRepository userRepository = null;
        List<String> pNames = null;
        for (String pId : playersId) {
            pNames.add(userRepository.findById(pId).get().getName());
        }
        return pNames;
    }
}
