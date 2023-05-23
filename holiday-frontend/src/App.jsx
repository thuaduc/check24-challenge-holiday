import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { SearchForm } from "./components/SearchForm/SearchForm";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { NavBar } from "./components/NavBar/NavBar";
import Cart from "./components/Cart/Cart";

export default function App() {
  return (
    <Router>
      <NavBar />
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <Routes>
          <Route path="/" element={<SearchForm />} />
          <Route path="/cart" element={<Cart />} />
        </Routes>
      </LocalizationProvider>
    </Router>
  );
}
