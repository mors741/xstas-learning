package com.staslabs.xstas.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "units")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Unit implements Serializable {

    @Id
    @Column(name = "unit_id")
    @Getter
    @Setter
    private long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "unit_name")
    @Getter @Setter private String name;

    @Column(name = "unit_order")
    @Getter @Setter private byte order;

    @Size(max = 10000)
    @Column(name = "content")
    @Getter @Setter private String content;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "format")
    @Getter @Setter private Format format;

    @ManyToOne
    @JoinColumn(name = "module", nullable = false)
    @Getter @Setter private Module module;
}
