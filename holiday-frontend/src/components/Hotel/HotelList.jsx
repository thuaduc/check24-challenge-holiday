import React from "react";
import Hotel from "./Hotel";
import { List, Container, Typography } from "@mui/material";
import BestHotel from "./BestHotel";
import Offer from "../Offer/Offer";
import "./HotelList.css";

const HotelList = ({
  offers,
  hotel,
  best_hotels,
  parsed_query,
  callbackFunction,
}) => {
  if (offers.length == 0) {
    if (hotel.length == 0) {
      return (
        <div>
          <Container>
            <div className="hero-titel">
              Welcome to Check24 Holiday, your gateway to the best of Mallorca!
            </div>
            <div className="hero-description">
              Get ready to embark on an unforgettable journey with the best
              hotel offers from Check24 Holiday. Discover a vast selection of
              accommodations that cater to every budget and preference, ensuring
              you find the perfect hotel for your dream vacation.
            </div>
            <br />
            <div className="hero-titel">
              Discover the best hotels in Mallorca!
            </div>
            <List>
              {best_hotels.map((h) => (
                <li key={h.id}>
                  <BestHotel hotel={h} />
                </li>
              ))}
            </List>
          </Container>
        </div>
      );
    } else {
      return (
        <div>
          <List>
            {hotel.map((h) => (
              <li key={h.id}>
                <Hotel
                  hotel={h}
                  callbackFunction={callbackFunction}
                  callbackQuery={parsed_query}
                />
              </li>
            ))}
          </List>
        </div>
      );
    }
  } else {
    return (
      <div>
        <List>
          {offers.map((o) => (
            <li key={o.offerId}>
              <Offer parsed_offer={o} is_order={false} />
            </li>
          ))}
        </List>
      </div>
    );
  }
};

export default HotelList;
