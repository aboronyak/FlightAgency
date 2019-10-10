package hu.rszoft.andras.flightagency.service.api.data;

import hu.rszoft.andras.flightagency.dal.entity.Airline;
import hu.rszoft.andras.flightagency.dal.entity.Flight;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Itinerary {

    private List<Flight> transfers = new ArrayList<>();
    private Long fullDuration = 0L;
    private boolean hasOtherAirlineTransfer;
    private Airline airline;

    public void addTransfer(Flight flight) {
        this.transfers.add(flight);
        if (fullDuration > 0) {
            int remain = (int)(fullDuration%60);
            if (remain != 0) {
                fullDuration += 60 - remain;
            }
        }
        fullDuration += flight.getTimeInterval();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (airline != null) {
            builder.append(String.format("%10s:%n", airline.getName()));
        } else {
            builder.append(String.format("%10s:%n", "Bármely légitársasággal a legrövidebb út"));
        }
        if(CollectionUtils.isEmpty(transfers) || hasOtherAirlineTransfer) {
            builder.append(String.format("%10s%-20s%n","","Nincs útvonal!"));
        } else {
            if (hasOtherAirlineTransfer) {
                builder.append(String.format("%10s%-20s%n","","FIGYELEM! Csak a választott légitársaság járataival nem érhető el a célállomás, a hiányzó útvonalra az alkalmazás más légitásraságot javasol!"));
            }
            transfers.forEach(flight -> {
                if (airline == null) {
                    builder.append(String.format("%10s%s: %s -> %s: %s%n", "",flight.getAirline().getName(), flight.getCityFrom().getName(), flight.getCityTo().getName(), getFormattedDuration(flight.getTimeInterval())));
                } else {
                    builder.append(String.format("%10s%s -> %s: %s%n", "", flight.getCityFrom().getName(), flight.getCityTo().getName(), getFormattedDuration(flight.getTimeInterval())));
                }
            });
            builder.append(String.format("%10s------%n",""));
            builder.append(String.format("%10sÖsszesen: %s%n","", getFormattedDuration(fullDuration)));
        }

        return builder.toString();
    }

    private String getFormattedDuration(Long durationMin) {
        int min = (int)(durationMin % 60);
        int hour = (int)(durationMin-min) / 60;
        if (hour == 0) {
            return String.format("%s perc", min);
        } else {
            return String.format("%s óra %s perc", hour, min);
        }
    }
}
