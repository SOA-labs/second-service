package ru.artemiyandstepan.secondservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Location {
    private Double x;
    private float y;
    private String name;
}
