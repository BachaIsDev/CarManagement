package com.example.carmanagement.model.driver;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "balance")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "green_dollars")
    private Long greenDollars;
    @Column(name = "red_dollars")
    private Long redDollars;
    @Column(name = "blue_dollars")
    private Long blueDollars;


}
