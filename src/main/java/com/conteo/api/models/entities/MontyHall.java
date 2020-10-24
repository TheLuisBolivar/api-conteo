package com.conteo.api.models.entities;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

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

    @Column(name = "DATE_START")
    private Long dateStart;

    @Column(name = "DATE_FINISH")
    private Long dateFinish;

    @Column(name = "FINISHED")
    private Integer finished;
}
