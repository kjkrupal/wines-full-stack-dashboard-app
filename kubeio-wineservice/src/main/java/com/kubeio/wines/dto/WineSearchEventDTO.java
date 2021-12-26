package com.kubeio.wines.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
public class WineSearchEventDTO {

    private UUID uuid;

    private String country;

    private String description;

    private String variety;

    private String winery;

    @Override
    public String toString() {
        return "Wine [uuid = " + this.uuid + "]";
    }
}
