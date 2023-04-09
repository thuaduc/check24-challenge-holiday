import Hotel from "./components/Hotel/Hotel";
import Home from "./pages/Home";
import About from "./pages/About";
import Cart from "./pages/Cart";
import "./App.css";
import { Routes, Route } from "react-router-dom";
import { Container } from "@mui/material";
import { NavBar } from "./components/NavBar/NavBar";
import { SearchForm } from "./components/SearchForm/SearchForm";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";

function App() {
  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <NavBar />
      <Container sx={{ m: 2, p: 2 }}>
        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/about" element={<About />}></Route>
          <Route path="/cart" element={<Cart />}></Route>
        </Routes>
      </Container>
    </LocalizationProvider>
  );
}

export default App;
