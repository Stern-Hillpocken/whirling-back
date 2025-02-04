package fr.poutchinystudio.whirling_back.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageOutput {

    public String user;
    private String content;
    public long date;
}
