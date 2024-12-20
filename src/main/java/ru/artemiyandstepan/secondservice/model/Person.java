package ru.artemiyandstepan.secondservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Person {
    private String name;
    private Double height;
    private Color eyeColor;
    private Color hairColor;
    private Country nationality;
    private Location location;
}