
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class TravelPlanner {

    private static final String pattern = "dd/MM/yyyy";
    private static final  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    //calculate the duration of trip in days
    public static long calculateTripDuration(LocalDate departureDate, LocalDate returnDate) {

        return ChronoUnit.DAYS.between(departureDate, returnDate);

    }

    //validate method for departure, return dates
    public static boolean validateTravelDates(LocalDate departureDate, LocalDate returnDate) {


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

    //Calculates hotel check-in and check-out dates based on travel dates
    public static String calculateHotelDates(LocalDate departureDate, LocalDate returnDate){

        String checkInDate = departureDate.format(formatter);
        String checkOutDate = returnDate.format(formatter);

        return "Hotel CheckIn Date: " + checkInDate + " Hotel CheckOut Date: " + checkOutDate;
        
    } 

    //Checks if a trip overlaps with a specific holiday
    public static boolean holidayOverlap (LocalDate departureDate, LocalDate returnDate, LocalDate holiday){

        return (holiday.isEqual(departureDate) || holiday.isEqual(returnDate)||
        (holiday.isAfter(departureDate) && holiday.isBefore(returnDate)));
    }

    //Parses a date string into a LocalDate object
    private static LocalDate parseDate (String dateStr) throws DateTimeParseException {

        return LocalDate.parse(dateStr,formatter);
    }

}
