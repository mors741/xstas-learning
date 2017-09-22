package com.staslabs.xstas.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@ToString @EqualsAndHashCode
public class Course implements Serializable {

    @Id
    @Column(name = "course_id")
    @Getter @Setter private long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "course_name")
    @Getter @Setter private String name;

    @Size(max = 5000)
    @Column(name = "course_desc")
    @Getter @Setter private String description;

    @Column(name = "cost")
    @Getter @Setter private int cost;

    @Size(max = 100)
    @Column(name = "promotion")
    @Getter @Setter private String promotion;

    @Size(max = 100)
    @Column(name = "trailer")
    @Getter @Setter private String trailer;

    @Size(max = 30)
    @Column(name = "wistia_project_id")
    @Getter @Setter private String wistiaProjectId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Getter @Setter private User student;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "state")
    @Getter @Setter private State state;
}
