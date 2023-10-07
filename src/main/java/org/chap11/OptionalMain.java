package org.chap11;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(List<Person> people) {
        return people.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optIns -> optIns.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }
}
