package vn.codegym.flightagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.codegym.flightagency.model.Passenger;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long > {

    // Creator: Duy
    Passenger findPassengerByIdentifierCard(String idCard);

    //BHung
    Passenger findByIdentifierCard(String identifyCard);
}