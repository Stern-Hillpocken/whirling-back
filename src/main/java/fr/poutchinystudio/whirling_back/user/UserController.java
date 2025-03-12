package fr.poutchinystudio.whirling_back.user;

import fr.poutchinystudio.whirling_back.dto.OneValueObject;
import fr.poutchinystudio.whirling_back.identification.IdCredentials;
import fr.poutchinystudio.whirling_back.identification.Identification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/register")
    public IdCredentials register(
            @RequestBody Identification identification
    ) {
        return service.register(identification);
    }

    @PostMapping("/update-name")
    public ResponseEntity<OneValueObject> updateName(
            @RequestBody OneValueObject ovo
    ) {
        User userUpdated = service.updateName(ovo.getValue());
        return ResponseEntity.ok(new OneValueObject(userUpdated.getName()));
    }

}
