import { useState } from "react";
import "./SearchForm.css";
import { Box } from "@mui/material";
import Container from "@mui/material/Container";
import mallorca from "./mallorca.png";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { ThemeProvider } from "@emotion/react";
import { CustonTheme } from "../Theme";
import HotelList from "../Hotel/HotelList";

const styles = {
  backgroundLandscape: {
    backgroundImage: `url(${mallorca})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    height: "50vh",
    width: "100%",
    minHeight: "100%",
  },

  backgroundWhite: {
    backgroundColor: "#fff",
    borderRadius: "10px",
    opacity: "0.95",
  },
};

const departureAirports = [{ value: "FMI", label: "FRANKFURT" }];

export function SearchForm() {
  const [departureAirport, setDepartureAirport] = useState("FMI");
  const [departureDate, setDepartureDate] = useState();
  const [returnDate, setReturnDate] = useState();
  const [countAdults, setCountAdults] = useState(1);
  const [countChildren, setCountChildren] = useState(0);
  const [duration, setDuration] = useState(0);
  const [hotels, sethotels] = useState([]);

  const handleSearch = () => {
    // console.log(departureAirport);
    // console.log(departureDate.toISOString());
    // console.log(returnDate.toISOString());
    // console.log(countAdults);
    if (
      departureAirport == "" ||
      departureDate == null ||
      returnDate == null ||
      departureDate > returnDate ||
      countAdults == 0
    ) {
      alert("wrong input");
    } else {
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
    }
  };

  return (
    <ThemeProvider theme={CustonTheme}>
      <Box sx={styles.backgroundLandscape}>
        <Container>
          <div className="container">
            <div className="first">
              <p className="style">Explorar</p>
            </div>
            <div className="second">
              <p className="style">Mallorca</p>
            </div>
          </div>
        </Container>
      </Box>
      <Box>
        <Container maxWidth="md" sx={{ ...styles.backgroundWhite, mt: -10 }}>
          <Grid container spacing={2} alignItems="center">
            <Grid item xs={12} sm={4}>
              <TextField
                select
                fullWidth
                label="Departure Airport"
                value={departureAirport}
                onChange={(e) => setDepartureAirport(e.target.value)}
              >
                {departureAirports.map((airport) => (
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
          <HotelList data={hotels} />
        </Container>
      </Box>
    </ThemeProvider>
  );
}
