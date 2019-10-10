package hu.rszoft.andras.flightagency.dal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "airline")
@Entity
public class Airline {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    public Airline() {
    }

    @Override
    public String toString() {
        return "AirLine {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}