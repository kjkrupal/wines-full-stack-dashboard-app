package com.kubeio.wines.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "WINES")
@Getter @Setter @NoArgsConstructor
public class Wine implements Serializable {

    private static final long serialVersionUID = -4439114469417994311L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "uuid", updatable = false, nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "country")
    private String country;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "points")
    private Long points;

    @Column(name = "price")
    private Double price;

    @Column(name = "variety")
    private String variety;

    @Column(name = "winery")
    private String winery;

    @Override
    public String toString() {
        return "Wine [id = " + this.id + " uuid = " + this.uuid + "]";
    }
}