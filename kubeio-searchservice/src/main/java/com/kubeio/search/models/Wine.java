package com.kubeio.search.models;

import com.kubeio.search.dto.WineDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "wines")
public class Wine {

    @Id
    private String uuid;

    @Field(type = FieldType.Text, name = "country")
    private String country;

    @Field(type = FieldType.Text, name = "description")
    private String description;

    @Field(type = FieldType.Text, name = "variety")
    private String variety;

    @Field(type = FieldType.Text, name = "winery")
    private String winery;

    @Override
    public String toString() {
        return "Wine [uuid = " + this.uuid + "]";
    }

    public static Wine getWineFromDTO(WineDTO wineDTO) {
        return new Wine(
                wineDTO.getUuid().toString(),
                wineDTO.getCountry(),
                wineDTO.getDescription(),
                wineDTO.getVariety(),
                wineDTO.getWinery());
    }

}
