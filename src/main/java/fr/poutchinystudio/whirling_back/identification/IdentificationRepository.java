package fr.poutchinystudio.whirling_back.identification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdentificationRepository extends JpaRepository<Identification, Long> {
    Optional<Identification> findByUserId(String userId);
}
