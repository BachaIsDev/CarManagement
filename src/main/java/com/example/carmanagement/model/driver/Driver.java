package com.example.carmanagement.model.driver;

import com.example.carmanagement.model.car.Car;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "passport")
    private String passport;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "driving_exp")
    private int drivingExp;
    @Enumerated(EnumType.STRING)
    @Column(name = "driving_permit")
    private DrivingPermit drivingPermit;
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Car> cars;
    @OneToOne
    private Balance balance;
}
