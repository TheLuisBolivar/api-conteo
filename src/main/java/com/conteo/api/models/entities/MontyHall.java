package com.conteo.api.models.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "MONTY_HALL")
public class MontyHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "DOOR_SELECTED")
    private Integer doorSelected;

    @Column(name = "DOOR_CORRECT")
    private Integer doorCorrect;

    @Column(name = "DOOR_SUGGESTION")
    private Integer doorSuggestion;

    @Column(name = "DATE_START")
    private String dateStart;

    @Column(name = "DATE_FINISH")
    private String dateFinish;

    @Column(name = "FINISHED")
    private Integer finished;

    @Column(name = "RESULT")
    private String result;
}
