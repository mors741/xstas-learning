package com.staslabs.xstas.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

    @Size(max = 5000)
    @Column(name = "unit_order")
    @Getter @Setter private byte order;

    @Size(max = 10000)
    @Column(name = "content")
    @Getter @Setter private String content;
}
