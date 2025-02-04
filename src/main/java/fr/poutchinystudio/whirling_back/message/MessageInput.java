package fr.poutchinystudio.whirling_back.message;

import fr.poutchinystudio.whirling_back.identification.Identification;
import lombok.Getter;

@Getter
public class MessageInput {

    private Identification identification;
    private String content;
}
