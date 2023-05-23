package com.company.holidaybackend.Repository;

import com.company.holidaybackend.Model.HotelList;
import com.company.holidaybackend.Model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    @Query(value = "SELECT NEW HotelList(o.hotelId, (SELECT h.name FROM Hotel h WHERE h.id = o.hotelId)," +
            "(SELECT h.stars FROM Hotel h WHERE h.id = o.hotelId), COUNT(*), MIN(o.price)) " +
            "FROM Offer o " +
            "WHERE " +
            "(:outboundDepartureAirport IS NULL OR o.outboundDepartureAirport = :outboundDepartureAirport) AND " +
            "(cast(:outboundDepartureDatetime as TIMESTAMP ) IS NULL " +
            "OR DATE(cast(:outboundDepartureDatetime as TIMESTAMP)) = DATE(o.outboundDepartureDatetime) ) AND " +
            "(cast(:inboundArrivalDatetime as TIMESTAMP ) IS NULL " +
            "OR DATE(cast(:inboundArrivalDatetime as TIMESTAMP )) = DATE(o.inboundArrivalDatetime)) AND " +
            "(:countAdults = 0 OR o.countAdults = :countAdults) AND " +
            "(:countChildren = 0 OR o.countChildren = :countChildren) AND " +
            "(:duration = 0 OR o.duration = :duration) " +
            "GROUP BY o.hotelId ORDER BY MIN(o.price) "
    )
    List<HotelList> query_and_return_min_price(@Param("outboundDepartureAirport") String inboundDepartureAirport,
                                               @Param("outboundDepartureDatetime") java.sql.Timestamp outboundDepartureDatetime,
                                               @Param("inboundArrivalDatetime") java.sql.Timestamp inboundArrivalDatetime,
                                               @Param("countAdults") int countAdults,
                                               @Param("countChildren") int countChildren,
                                               @Param("duration") int duration

    );

    @Query(value = "SELECT o " +
            "FROM Offer o " +
            "WHERE " +
            ":id = o.hotelId AND " +
            "(:outboundDepartureAirport IS NULL OR o.outboundDepartureAirport = :outboundDepartureAirport) AND " +
            "(cast(:outboundDepartureDatetime as TIMESTAMP ) IS NULL " +
            "OR DATE(cast(:outboundDepartureDatetime as TIMESTAMP)) = DATE(o.outboundDepartureDatetime) ) AND " +
            "(cast(:inboundArrivalDatetime as TIMESTAMP ) IS NULL " +
            "OR DATE(cast(:inboundArrivalDatetime as TIMESTAMP )) = DATE(o.inboundArrivalDatetime)) AND " +
            "(:countAdults = 0 OR o.countAdults = :countAdults) AND " +
            "(:countChildren = 0 OR o.countChildren = :countChildren) AND " +
            "(:duration = 0 OR o.duration = :duration) " +
            "ORDER BY o.price "
    )
    List<Offer> query_and_return_offers(@Param("id") int id,
                                        @Param("outboundDepartureAirport") String outboundDepartureAirport,
                                        @Param("outboundDepartureDatetime") java.sql.Timestamp outboundDepartureDatetime,
                                        @Param("inboundArrivalDatetime") java.sql.Timestamp inboundArrivalDatetime,
                                        @Param("countAdults") int countAdults,
                                        @Param("countChildren") int countChildren,
                                        @Param("duration") int duration
    );

}
