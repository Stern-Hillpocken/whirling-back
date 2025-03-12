package fr.poutchinystudio.whirling_back.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    private String id;

    private String name;

    private String game;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return getName();
    }

    @Override
    public String getUsername() {
        return getId();
    }

    public String getRole() {
        return "ROLE_USER";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public void setName(String newName) {
        name = newName;
    }
}
