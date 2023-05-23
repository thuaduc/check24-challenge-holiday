import { useState, useEffect } from "react";
import { Container, List } from "@mui/material";
import Offer from "../Offer/Offer";

const getOrders = async (hotelId) => {
  const query = "http://localhost:8080/api/v1/cart/all";
  try {
    const response = await fetch(query);
    const data = await response.json();
    return data;
  } catch (error) {
    console.log(error);
    return {};
  }
};

export function UserOrders() {
  const [allorders, setAllOrders] = useState([]);

  useEffect(() => {
    getOrders().then((result) => {
      setAllOrders(result);
    });
  }, []);
  if (allorders.length == 0) {
    return <div>Currently no orders</div>;
  }
  return (
    <Container>
      <List>
        {allorders.map((o) => (
          <li key={o.id}>
            <Offer parsed_offer={o} is_order={true} />
          </li>
        ))}
      </List>
    </Container>
  );
}
