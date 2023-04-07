import { createTheme, ThemeProvider } from "@mui/material/styles";
import { ReactNode } from "react";

export const CustonTheme = createTheme({
  palette: {
    primary: {
      light: "#5e92f3", // A light blue color for primary elements
      main: "#1e467f", // A dark blue color for primary elements
      dark: "#102b4a", // An even darker blue color for primary elements
      contrastText: "#fff", // White text to contrast against the blue background
    },
    secondary: {
      light: "#ffd280", // A light orange color for secondary elements
      main: "#ff9f1c", // An orange color for secondary elements
      dark: "#ff6b00", // A darker orange color for secondary elements
      contrastText: "#000", // Black text to contrast against the orange background
    },
  },
});
