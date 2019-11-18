package com.example.Project1.models;

/*
        {Αν ο μέσος όρος των 3 estimations είναι <2 diffculty = EASY, <=4 MEDIUM, >5 HARD.}

        To NA ίσως χρειαστεί για να ελέγξουμε παράλογες τιμές στα estimations π.χ. estimation <= 0
 */

public enum Difficulty {
    NA,
    EASY,
    MEDIUM,
    HARD
}
