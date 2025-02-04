package fr.poutchinystudio.whirling_back.identification;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Identification {
    @Id
    private String userId;
    private String userName;
}
