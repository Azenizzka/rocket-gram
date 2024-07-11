package ru.azenizzka.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public record AuthData(String rocketURI,String authToken, String userId) {

}
