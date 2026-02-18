
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

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

    public static void main (String []args){

        boolean running =  true;
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to Travel Planner");
        System.err.println("==========================");

        System.out.println("All dates should be entered in format dd/MM/yyyy");

        while(running){

            System.out.println("\nChoose an option:");
            System.out.println("1. Calculate trip duration");
            System.out.println("2. Validate travel dates");
            System.out.println("3. Calculate hotel check-in and check-out");
            System.out.println("4. Check if trip overlaps with a holiday");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            choice = Integer.parseInt(scanner.nextLine());

            LocalDate departureDate;
            LocalDate returnDate;


            if(choice == 5){
                running = false;
                return;
            }

            switch (choice) {
                case 1:
                    System.out.println("\nEnter departure date: ");
                    String departure = scanner.nextLine();

                    System.out.println("\nEnter return date: ");
                    String returned = scanner.nextLine();

                    departureDate = parseDate(departure);
                    returnDate = parseDate(returned);

                    System.out.println("Trip duration: " + calculateTripDuration(departureDate, returnDate) + " days");
                    break;

                case 2:
                    System.out.println("\nEnter departure date: ");
                    departureDate = parseDate(scanner.nextLine());

                    System.out.println("\nEnter return date: ");
                    returnDate = parseDate(scanner.nextLine());

                    boolean isValid =  validateTravelDates(departureDate, returnDate);
                    if(isValid){
                        System.out.println("\nTravel dates are valid!");
                    }else{
                        System.out.println("\nTravel dates are invalid!");
                    }

                    break;
                    
                
            }
        }
    }
}
