import { useState, useEffect } from "react";
import { Button, Container, List, Typography, Stack } from "@mui/material";
import Offer from "../Offer/Offer";
import { Link } from "react-router-dom";

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

const Cart = () => {
  const [allorders, setAllOrders] = useState([]);

  useEffect(() => {
    getOrders().then((result) => {
      setAllOrders(result);
    });
  }, []);
  if (allorders.length == 0) {
    return (
      <Container>
        <Stack direction="column">
          <Typography variant="h4" align="center" mt={10}>
            Add your first Order now!
          </Typography>
          <Button component={Link} to="/">
            To Search Page
          </Button>
        </Stack>
      </Container>
    );
  }
  return (
    <Container>
      <List>
        {allorders.map((o) => (
          <li key={o.offerId}>
            <Offer parsed_offer={o} is_order={true} />
          </li>
        ))}
      </List>
    </Container>
  );
};

export default Cart;
