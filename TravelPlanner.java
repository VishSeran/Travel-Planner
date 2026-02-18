
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TravelPlanner {

    String pattern = "dd/MM/yyyy";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    //calculate the duration of trip in days
    public static long calculateTripDuration(LocalDate departureDate, LocalDate returnDate) {

        return ChronoUnit.DAYS.between(departureDate, returnDate);

    }

    //validate method for departure, return dates
    public static boolean validateTravelDays(LocalDate departureDate, LocalDate returnDate) {


        LocalDate todayDate = LocalDate.now();

        if(departureDate.isBefore(todayDate)){
            
            System.out.println("Departure date should not be in the past");
            return false;
            
        }

        if(departureDate.isAfter(returnDate) || departureDate.isEqual(returnDate)){
            
            System.out.println("Return date should be after departure date");
            return false;
        }

        if(calculateTripDuration(departureDate, returnDate) > 90){
            
            System.out.println("Trip should not be longer than 90 days");
            return false;
        }

        return true;

    }

}
