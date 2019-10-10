package hu.rszoft.andras.flightagency.dal.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Table(name = "flight")
@Entity
public class Flight {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name="city_id_from")
    private City cityFrom;
    @ManyToOne
    @JoinColumn(name="city_id_to")
    private City cityTo;

    /**
     * Távolság km-ben
     */
    @Column(name = "distance")
    private Long distance;

    /**
     * Menetrend szerinti időintervallum másodpercben
     */
    @Column(name = "time_interval")
    private Long timeInterval;
    @ManyToOne
    @JoinColumn(name="airline_id")
    private Airline airline;


    @Override
    public String toString() {
        return "Flight {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", citiFrom=" + cityFrom  +
                ", citiTo=" + cityTo  +
                ", distance=" + distance  +
                ", timeInterval=" + timeInterval  +
                ", airline=" + airline  +
                '}';
    }
}
