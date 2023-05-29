import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { NavBar } from "./components/NavBar/NavBar";
import { Footer } from "./components/NavBar/Footer";
import Cart from "./components/Cart/Cart";
import { ThemeProvider } from "@emotion/react";
import { CustonTheme } from "./components/Theme";
import { HomePage } from "./HomePage";

export default function App() {
  return (
    <Router>
      <ThemeProvider theme={CustonTheme}>
        <NavBar />
        <LocalizationProvider dateAdapter={AdapterDayjs}>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/cart" element={<Cart />} />
          </Routes>
        </LocalizationProvider>
        <Footer />
      </ThemeProvider>
    </Router>
  );
}
