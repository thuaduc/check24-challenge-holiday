import * as React from "react";
import { useState, useEffect } from "react";
import {
  AppBar,
  Box,
  Toolbar,
  Button,
  IconButton,
  Badge,
  Container,
} from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import HomeIcon from "@mui/icons-material/Home";
import InfoIcon from "@mui/icons-material/Info";

const getNumOfCart = async () => {
  const query = "http://localhost:8080/api/v1/cart/all";
  try {
    const response = await fetch(query);
    const data = await response.json();
    return data.length;
  } catch (error) {
    console.log(error);
    return 0;
  }
};

export function NavBar() {
  const [countCart, setCountCart] = useState(0);

  useEffect(() => {
    getNumOfCart().then((result) => {
      setCountCart(result);
    });
  }, [countCart]);

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" className="" color="primary">
        <Container>
          <Toolbar>
            <Button
              href="/"
              color="inherit"
              sx={{ ml: -5 }}
              startIcon={<HomeIcon />}
            >
              My Holiday
            </Button>
            <Button
              href="/about"
              color="inherit"
              sx={{ mr: "auto" }}
              startIcon={<InfoIcon />}
            >
              About
            </Button>
            <Badge badgeContent={countCart} color="error">
              <Button
                href="/cart"
                color="inherit"
                sx={{ mr: -8 }}
                startIcon={<ShoppingCartIcon />}
              >
                Cart
              </Button>
            </Badge>
          </Toolbar>
        </Container>
      </AppBar>
    </Box>
  );
}
