package fr.poutchinystudio.whirling_back.identification;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/identification")
@RequiredArgsConstructor
public class IdentificationController {

    private final IdentificationService service;

    @PostMapping
    public Identification identificationCheck(
            @RequestBody Identification identification
    ) {
        return service.identificationCheck(identification);
    }

    @PostMapping("/update-username")
    public Identification updateUserName(
            @RequestBody IdentificationToUpdate identificationToUpdate
    ) {
        return service.updateUserName(identificationToUpdate);
    }

}
