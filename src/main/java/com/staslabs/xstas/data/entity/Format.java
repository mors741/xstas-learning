package com.staslabs.xstas.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Format {
    TEXT("Текст", 10),
    VIDEO("Видео", 20);
    // TODO: different kind of playbuzz formats will be here

    @Getter private final String name;
    @Getter private final int bonusPoints;
}
