package Lecture16;

public class CarNotAvailableException extends Exception {
    private String carModel;
    private String date;

    public CarNotAvailableException(String carModel, String date) {
        super("Car not available: " + carModel + " on " + date);
        this.carModel = carModel;
        this.date = date;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getDate() {
        return date;
    }

    public static void main(String[] args) {
        CarRentalService service = new CarRentalService();

        String model = "Toyota Corolla";
        String date = "2025-11-01";

        try {
            // First rent attempt — should succeed
            service.rentCar(model, date);

            // Second rent attempt — should throw exception
            service.rentCar(model, date);
        } catch (CarNotAvailableException e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("Details → Model: " + e.getCarModel() + ", Date: " + e.getDate());
        }

        // Return the car
        service.returnCar(model);
    }
}

 class Car {
    private String model;
    private boolean available;

    public Car(String model) {
        this.model = model;
        this.available = true; // By default, car is available
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        available = false;
    }

    public void returnCar() {
        available = true;
    }
}

class CarRentalService {
    private Car[] cars;

    public CarRentalService() {
        cars = new Car[2];  // Array of 2 cars
        cars[0] = new Car("Toyota Corolla");
        cars[1] = new Car("Honda Civic");
    }

    public void rentCar(String model, String date) throws CarNotAvailableException {
        Car car = findCar(model);
        if (car == null || !car.isAvailable()) {
            throw new CarNotAvailableException(model, date);
        }
        car.rent();
        System.out.println("Successfully rented " + model + " on " + date);
    }

    public void returnCar(String model) {
        Car car = findCar(model);
        if (car != null) {
            car.returnCar();
            System.out.println(model + " has been returned.");
        } else {
            System.out.println("Car not found in system.");
        }
    }

    private Car findCar(String model) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getModel().equals(model)) {
                return cars[i];
            }
        }
        return null; // Not found
    }
}





