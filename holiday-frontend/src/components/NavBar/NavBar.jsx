import * as React from "react";
import {
  AppBar,
  Box,
  Toolbar,
  Button,
  IconButton,
  Container,
} from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { ThemeProvider } from "@emotion/react";
import { CustonTheme } from "../Theme";

export function NavBar() {
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
              <ShoppingCartIcon />
            </IconButton>
          </Toolbar>
        </AppBar>
      </Box>
    </ThemeProvider>
  );
}
