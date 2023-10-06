package org.chap11;

public class OptionalMain {
    public String getCarInsuranceName1(Person1 person) {
        if(person != null) {
            Car1 car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if(insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }

    public String getCarInsuranceName2(Person1 person) {
        if (person == null) {
            return  "Unknown";
        }

        Car1 car1 = person.getCar();
        if (car1 == null) {
            return "Unknown";
        }

        Insurance insurance = car1.getInsurance();
        if(insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }
}
