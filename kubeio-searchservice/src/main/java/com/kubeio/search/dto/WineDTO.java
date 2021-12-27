package com.kubeio.search.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class WineDTO {

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
