package pl.klubstrzelecki.serwer_klub_strzelecki.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewsDTO {
    private long id;
    private String title;
    private String content;
}
