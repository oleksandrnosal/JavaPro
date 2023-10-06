package ua.kiev.prog.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

// DB -> E -> R -> S -> |DTO| -> C -> JSON/View

@Data
@NoArgsConstructor
public class LocationDTO {
    private String ip;
    private String city;
    private String region;
    private String country;
    private double RateOfUSD;

    private LocationDTO(String ip, String city, String region, String country, double RateOfUSD) {
        this.ip = ip;
        this.city = city;
        this.region = region;
        this.country = country;
        this.RateOfUSD = RateOfUSD;
    }

    public static LocationDTO of(String ip, String city, String region, String country, double RateOfUSD) {
        return new LocationDTO(ip, city, region, country, RateOfUSD);
    }
}
