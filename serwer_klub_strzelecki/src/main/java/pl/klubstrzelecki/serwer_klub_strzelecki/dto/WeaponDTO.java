package pl.klubstrzelecki.serwer_klub_strzelecki.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WeaponDTO {
    private long id;
    private String name;
    private String type;
    private double caliber;
    private String image;
}
