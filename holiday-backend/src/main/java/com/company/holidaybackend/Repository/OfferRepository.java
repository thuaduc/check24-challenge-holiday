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
    //DATE(cast(:inboundDepartureDatetime as TIMESTAMP)) = DATE(o.inboundDepartureDatetime)


    @Query(value = "SELECT NEW HotelList(o.hotelId, COUNT(*), MIN(o.price)) " +
            "FROM Offer o " +
            "WHERE " +
            "(:inboundDepartureAirport IS NULL OR o.inboundDepartureAirport = :inboundDepartureAirport) AND " +
            "(cast(:inboundDepartureDatetime as TIMESTAMP ) IS NULL " +
            "OR DATE(cast(:inboundDepartureDatetime as TIMESTAMP)) = DATE(o.inboundDepartureDatetime) ) AND " +
            "(:outboundArrivalAirport IS NULL OR o.outboundArrivalAirport = :outboundArrivalAirport) AND " +
            "(cast(:outboundArrivalDatetime as TIMESTAMP ) IS NULL " +
            "OR DATE(cast(:outboundArrivalDatetime as TIMESTAMP )) = DATE(o.outboundArrivalDatetime)) AND " +
            "(:countAdults = 0 OR o.countAdults = :countAdults) AND " +
            "(:countChildren = 0 OR o.countChildren = :countChildren) AND " +
            "(:duration = 0 OR o.duration = :duration) AND " +
            "(:mealType IS NULL OR o.mealType = :mealType) AND " +
            "(:roomType IS NULL OR o.roomType = :roomType) AND " +
            "(:oceanView IS NULL OR cast(o.oceanView as string ) = :oceanView) " +
            "GROUP BY o.hotelId ORDER BY MIN(o.price) "
    )
    List<HotelList> query_and_return_min_price(@Param("inboundDepartureAirport") String inboundDepartureAirport,
                                               @Param("inboundDepartureDatetime") java.sql.Timestamp inboundDepartureDatetime,
                                               @Param("outboundArrivalAirport") String outboundArrivalAirport,
                                               @Param("outboundArrivalDatetime") java.sql.Timestamp outboundArrivalDatetime,
                                               @Param("countAdults") int countAdults,
                                               @Param("countChildren") int countChildren,
                                               @Param("duration") int duration,
                                               @Param("mealType") String mealType,
                                               @Param("roomType") String roomType,
                                               @Param("oceanView") String oceanView

    );

    @Query("SELECT o " +
            "FROM Offer o " +
            "WHERE " +
            ":id = o.hotelId AND " +
            "(:inboundDepartureAirport IS NULL OR o.inboundDepartureAirport = :inboundDepartureAirport) AND " +
            "(cast(:inboundDepartureDatetime as TIMESTAMP ) IS NULL " +
            "OR DATE(cast(:inboundDepartureDatetime as TIMESTAMP)) = DATE(o.inboundDepartureDatetime) ) AND " +
            "(:outboundArrivalAirport IS NULL OR o.outboundArrivalAirport = :outboundArrivalAirport) AND " +
            "(cast(:outboundArrivalDatetime as TIMESTAMP ) IS NULL " +
            "OR DATE(cast(:outboundArrivalDatetime as TIMESTAMP )) = DATE(o.outboundArrivalDatetime)) AND " +
            "(:countAdults = 0 OR o.countAdults = :countAdults) AND " +
            "(:countChildren = 0 OR o.countChildren = :countChildren) AND " +
            "(:duration = 0 OR o.duration = :duration) AND " +
            "(:mealType IS NULL OR o.mealType = :mealType) AND " +
            "(:roomType IS NULL OR o.roomType = :roomType) AND " +
            "(:oceanView IS NULL OR cast(o.oceanView as string ) = :oceanView) "
    )
    List<Offer> query_and_return_offers(@Param("id") int id,
                                        @Param("inboundDepartureAirport") String inboundDepartureAirport,
                                        @Param("inboundDepartureDatetime") java.sql.Timestamp inboundDepartureDatetime,
                                        @Param("outboundArrivalAirport") String outboundArrivalAirport,
                                        @Param("outboundArrivalDatetime") java.sql.Timestamp outboundArrivalDatetime,
                                        @Param("countAdults") int countAdults,
                                        @Param("countChildren") int countChildren,
                                        @Param("duration") int duration,
                                        @Param("mealType") String mealType,
                                        @Param("roomType") String roomType,
                                        @Param("oceanView") String oceanView
    );

}
