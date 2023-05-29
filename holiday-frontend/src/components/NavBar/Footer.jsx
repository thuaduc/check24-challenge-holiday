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

export function Footer() {
  return (
    <Box sx={{ flexGrow: 1, bottom: 0, width: "100%" }}>
      <AppBar position="static" className="" color="primary">
        <Container>
          <Toolbar></Toolbar>
        </Container>
      </AppBar>
    </Box>
  );
}
