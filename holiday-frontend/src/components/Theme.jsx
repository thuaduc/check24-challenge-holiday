import { createTheme, ThemeProvider } from "@mui/material/styles";

export const CustonTheme = createTheme({
  palette: {
    primary: {
      light: "#5e92f3", // A light blue color for primary elements
      main: "#1e467f", // A dark blue color for primary elements
      dark: "#102b4a", // An even darker blue color for primary elements
      contrastText: "#fff", // White text to contrast against the blue background
    },
    secondary: {
      main: "#f8f9fe",
    },
  },

  typography: {
    fontFamily: ["Inter", "sans-serif"].join(","),
  },
});
