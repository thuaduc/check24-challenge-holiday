import { useState, useEffect } from "react";
import { Button, Container, List, Typography, Stack } from "@mui/material";
import Offer from "../Offer/Offer";
import HomeIcon from "@mui/icons-material/Home";
import { API_CART_GET } from "../../Api";
import { Link } from "react-router-dom";

const getOrders = async () => {
  try {
    const response = await fetch(API_CART_GET);
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
        <Stack direction="column" sx={{ height: 660 }}>
          <Typography
            variant="h3"
            fontWeight="semi-bold"
            align="center"
            mt={10}
          >
            Add your first Order now!
          </Typography>
          <Button
            href="/home"
            color="primary"
            sx={{ mt: 5 }}
            startIcon={<HomeIcon />}
          >
            My Holiday
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
