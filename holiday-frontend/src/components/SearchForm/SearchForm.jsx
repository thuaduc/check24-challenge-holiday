import { useState } from "react";
import "./SearchForm.css";
import {
  Box,
  Container,
  Grid,
  TextField,
  Button,
  MenuItem,
} from "@mui/material";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { ThemeProvider } from "@emotion/react";
import { CustonTheme } from "../Theme";
import mallorca from "./mallorca4.jpeg";
import mallorca2 from "./mallorca.png";
import HotelList from "../Hotel/HotelList";
import { best_hotels } from "./BestHotelList";
import { departure_airports } from "./DepartureAirportList";

const styles = {
  backgroundLandscape: {
    backgroundImage: `url(${mallorca})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    height: "55vh",
    width: "100%",
    minHeight: "100%",
  },

  backgroundWhite: {
    backgroundColor: "#fff",
    borderRadius: "10px",
    opacity: "0.96",
  },
};

export function SearchForm() {
  const [departureAirport, setDepartureAirport] = useState("MUC");
  const [departureDate, setDepartureDate] = useState();
  const [returnDate, setReturnDate] = useState();
  const [countAdults, setCountAdults] = useState(1);
  const [countChildren, setCountChildren] = useState(0);
  const [duration, setDuration] = useState(0);
  const [hotels, sethotels] = useState([]);

  const handleSearch = () => {
    const query =
      "http://localhost:8080/api/v1/list?search=outboundDepartureAirport==" +
      departureAirport +
      ",outboundDepartureDatetime==" +
      departureDate.toISOString().replace("Z", "+00:00") +
      ",inboundArrivalDatetime==" +
      returnDate.toISOString().replace("Z", "+00:00") +
      ",countAdults==" +
      countAdults +
      ",countChildren==" +
      countChildren;

    console.log(query);
    fetch(query)
      .then((respone) => respone.json())
      .then((data) => {
        sethotels(data);
      })
      .catch((e) => console.log(e));

    // fetch(query)
    //   .then((re) => )
    //   .then((data) => {
    //     console.log(data);
    //     sethotels(data);
    //   })
    //   .catch((error) => console.log(error));

    // try {
    //   const response = await fetch(query);
    //   console.log(response);
    //   //   const data = await response.json();
    //   //   sethotels(data);
    // } catch (error) {
    //   console.log(error);
    //   console.log(hotels);
    // }
  };

  return (
    <ThemeProvider theme={CustonTheme}>
      <Box sx={styles.backgroundLandscape}>
        {/* <Container>
          <div className="container">
            <div className="first">
              <p className="style">Explorar</p>
            </div>
            <div className="second">
              <p className="style">Mallorca</p>
            </div>
          </div>
        </Container> */}
      </Box>
      <Box>
        <Container maxWidth="lg" sx={{ ...styles.backgroundWhite, mt: -15 }}>
          <Grid container spacing={2} alignItems="center">
            <Grid item xs={12} sm={4}>
              <TextField
                select
                fullWidth
                label="Departure Airport"
                value={departureAirport}
                onChange={(e) => setDepartureAirport(e.target.value)}
              >
                {departure_airports.map((airport) => (
                  <MenuItem key={airport.value} value={airport.value}>
                    {airport.label}
                  </MenuItem>
                ))}
              </TextField>
            </Grid>
            <Grid item xs={12} sm={4}>
              <DatePicker
                sx={{ width: "100%" }}
                value={departureDate}
                onChange={(e) => setDepartureDate(e)}
                label="Departure Date"
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <DatePicker
                sx={{ width: "100%" }}
                value={returnDate}
                onChange={(e) => setReturnDate(e)}
                label="Return Date"
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                fullWidth
                type="number"
                label="Adults"
                value={countAdults}
                onChange={(e) => setCountAdults(parseInt(e.target.value))}
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                fullWidth
                type="number"
                label="Children"
                value={countChildren}
                onChange={(e) => setCountChildren(parseInt(e.target.value))}
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                fullWidth
                type="number"
                label="Duration (days)"
                value={duration}
                onChange={(e) => setDuration(parseInt(e.target.value))}
              />
            </Grid>
            <Grid item xs={12} sx={{}}>
              <Button
                variant="contained"
                color="primary"
                size="large"
                onClick={handleSearch}
              >
                Search
              </Button>
            </Grid>
          </Grid>
          <HotelList data={hotels} best_hotels={best_hotels} />
        </Container>
      </Box>
    </ThemeProvider>
  );
}
