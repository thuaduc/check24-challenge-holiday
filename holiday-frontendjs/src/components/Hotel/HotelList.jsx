import React from "react";
import Hotel from "./Hotel";
import { List } from "@mui/material";

const HotelList = ({ data }) => {
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
};

export default HotelList;
