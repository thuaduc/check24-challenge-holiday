import React from "react";
import Hotel from "./Hotel";
import { List, Container, Typography } from "@mui/material";
import BestHotel from "./BestHotel";
import "./HotelList.css";

const HotelList = ({ data, best_hotels }) => {
  if (data.length == 0) {
    return (
      <div>
        <Container>
          <div className="hero-titel">
            Welcome to Check24 Holiday, your gateway to the best of Mallorca!
          </div>
          <div className="hero-description">
            Get ready to embark on an unforgettable journey with the best hotel
            offers from Check24 Holiday. Discover a vast selection of
            accommodations that cater to every budget and preference, ensuring
            you find the perfect hotel for your dream vacation. With competitive
            prices and a user-friendly booking platform, Check24 Holiday makes
            it effortless to secure the best deal for your stay, allowing you to
            relax and enjoy your trip to the fullest.
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
          {data.map((h) => (
            <li key={h.id}>
              <Hotel hotel={h} />
            </li>
          ))}
        </List>
      </div>
    );
  }
};

export default HotelList;
