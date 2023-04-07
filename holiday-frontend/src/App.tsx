import Hotel from "./components/Hotel/Hotel";
import Home from "./pages/Home";
import About from "./pages/About";
import Cart from "./pages/Cart";
import { Routes, Route } from "react-router-dom";
import { Container } from "@mui/material";
import { NavBar } from "./components/NavBar/NavBar";
import { SearchForm } from "./components/SearchForm/SearchForm";

function App() {
  return (
    <>
      <NavBar />
      <SearchForm />
      <Container sx={{ m: 2, p: 2 }}>
        <Routes>
          <Route path="/" element={<Home />}></Route>
          <Route path="/about" element={<About />}></Route>
          <Route path="/cart" element={<Cart />}></Route>
        </Routes>
      </Container>
    </>
  );
}

export default App;
