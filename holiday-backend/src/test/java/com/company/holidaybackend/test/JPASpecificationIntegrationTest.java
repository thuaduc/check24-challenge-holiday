package com.company.holidaybackend.test;

import com.company.holidaybackend.Model.Offer;
import com.company.holidaybackend.Search.OfferSpecification;
import com.company.holidaybackend.Search.SearchCriteria;
import com.company.holidaybackend.Repository.OfferRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = JpaContext.class)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres",
        "spring.datasource.username=postgres",
        "spring.datasource.password=postgres",
        "spring.datasource.driver-class-name=org.postgresql.Driver",
        "spring.jpa.show-sql=true",
        "spring.jpa.hibernate.ddl-auto=update",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect"
        })
@DataJpaTest
public class JPASpecificationIntegrationTest {

    @Autowired
    private OfferRepository offerRepository;

    private Offer offer1;

    @Before
    public void init() {
        offer1 = new Offer();
        offer1.setOfferId(1L);
        offer1.setHotelId(1);
        offer1.setCountAdults(1);
        offer1.setCountChildren(1);
        offer1.setPrice(1000);

        offer1.setInboundArrivalAirport("HCM");
        offer1.setInboundDepartureAirport("HCM");
        offer1.setInboundArrivalDatetime("2022-10-12T14:40:00+02:00");
        offer1.setInboundDepartureDatetime("2022-10-12T08:35:00+02:00");

        offer1.setOutboundArrivalAirport("HCM");
        offer1.setOutboundDepartureAirport("HCM");
        offer1.setOutboundArrivalDatetime("2022-10-05T14:25:00+02:00");
        offer1.setOutboundDepartureDatetime("2022-10-05T09:30:00+02:00");

        offer1.setMealType("breakfast");
        offer1.setOceanView(true);
        offer1.setRoomType("apartment");
        offerRepository.save(offer1);
    }

    @Test
    public void test1() {

    }
}
