import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
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
