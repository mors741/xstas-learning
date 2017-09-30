package com.staslabs.xstas.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "teasers")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Teaser implements Serializable{
    @Id
    @Column(name = "teaser_id")
    @Getter
    @Setter
    private long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "teaser_name")
    @Getter @Setter private String name;

    @Size(max = 1000)
    @Column(name = "teaser_desc")
    @Getter @Setter private String description;

    @Size(min = 1, max = 100)
    @Column(name = "video")
    @Getter @Setter private String video;

    @ManyToOne
    @JoinColumn(name = "creator", nullable = false)
    @Getter @Setter private User creator;

}
