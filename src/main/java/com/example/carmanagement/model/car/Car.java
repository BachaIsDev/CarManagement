package com.example.carmanagement.model.car;

import com.example.carmanagement.model.driver.Driver;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "vin")
    private String vin;
    @Column(name = "reg_number")
    private String regNumber;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "car_model")
    private String carModel;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Driver driver;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "year_of_issue")
    private Date yearOfIssue;
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Detail> details;
}
