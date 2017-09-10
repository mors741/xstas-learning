package com.staslabs.xstas.data.entity;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id"})
public class Course {

    @Getter @Setter private long id;
    @Getter @Setter private int categoryId;
//    @Getter @Setter private final Teacher author;
    @Getter @Setter private String name;
    @Getter @Setter private BigDecimal price;
    @Getter @Setter private int level;

}
