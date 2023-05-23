import * as React from "react";
import { useState, useEffect } from "react";
import { AppBar, Box, Toolbar, Button, IconButton, Badge } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { ThemeProvider } from "@emotion/react";
import { CustonTheme } from "../Theme";

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
  const [countCart, setCountCart] = useState([]);

  useEffect(() => {
    getNumOfCart().then((result) => {
      setCountCart(result);
    });
  }, [countCart]);

  return (
    <ThemeProvider theme={CustonTheme}>
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static" className="" color="primary">
          <Toolbar>
            <Button href="/" color="inherit">
              My Holiday
            </Button>
            <Button href="/about" color="inherit" sx={{ mr: "auto" }}>
              About
            </Button>
            <IconButton href="/cart" color="inherit">
              <Badge badgeContent={countCart} color="error">
                <ShoppingCartIcon />
              </Badge>
            </IconButton>
          </Toolbar>
        </AppBar>
      </Box>
    </ThemeProvider>
  );
}
