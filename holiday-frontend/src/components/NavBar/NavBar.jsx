import * as React from "react";
import { useState, useEffect } from "react";
import { AppBar, Box, Toolbar, Button, Badge, Container } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import HomeIcon from "@mui/icons-material/Home";
import InfoIcon from "@mui/icons-material/Info";
import { API_CART_GET } from "../../Api";

const getNumOfCart = async () => {
  const query = API_CART_GET;
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
              sx={{ ml: -5, mr: "auto" }}
              startIcon={<HomeIcon />}
            >
              My Holiday
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
