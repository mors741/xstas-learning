package com.staslabs.xstas.data.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "modules")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Module implements Serializable {
    @Id
    @Column(name = "module_id")
    @Getter
    @Setter
    private long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "module_name")
    @Getter @Setter private String name;

    @Size(max = 1000)
    @Column(name = "module_desc")
    @Getter @Setter private String description;

    @Column(name = "module_order")
    @Getter @Setter private byte order;

    @ManyToOne
    @JoinColumn(name = "course", nullable = false)
    @Getter @Setter private Course course;

    @OneToMany(mappedBy = "module")
    @Getter @Setter private Set<Unit> units;

}
