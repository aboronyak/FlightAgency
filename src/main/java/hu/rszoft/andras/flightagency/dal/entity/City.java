package hu.rszoft.andras.flightagency.dal.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@Table(name = "city")
@Entity
public class City {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private Long population;

    public City() {
    }

    @Override
    public String toString() {
        return "City {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", poupulation=" + population  +
                '}';
    }
}