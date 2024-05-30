package pl.klubstrzelecki.serwer_klub_strzelecki.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewsDTO {
    private long id;
    private String title;
    private String content;
}
