import "./SearchForm.css";
import { useState } from "react";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import mallorca from "../../../public/mallorca.png";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { ThemeProvider } from "@emotion/react";
import { CustonTheme } from "../Theme";
import dayjs, { Dayjs } from "dayjs";

const styles = {
  backgroundLandscape: {
    backgroundImage: `url(${mallorca})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    height: "40vh",
    width: "100%",
  },

  backgroundWhite: {
    backgroundColor: "#fff",
    borderRadius: "10px",
    opacity: "0.96",
  },
};

const departureAirports = [
  { value: "JFK", label: "New York (JFK)" },
  { value: "LAX", label: "Los Angeles (LAX)" },
  { value: "LHR", label: "London Heathrow (LHR)" },
];

export function SearchForm() {
  const [departureAirport, setDepartureAirport] = useState("");
  const [departureDate, setDepartureDate] = useState<Dayjs | null>(null);
  const [returnDate, setReturnDate] = useState<Dayjs | null>(null);
  const [countAdults, setCountAdults] = useState(1);
  const [countChildren, setCountChildren] = useState(0);
  const [duration, setDuration] = useState(1);

  const handleDepartureAirportChange = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setDepartureAirport(e.target.value);
  };
  const handleSearch = () => {};

  return (
    <>
      <ThemeProvider theme={CustonTheme}>
        <Box
          sx={{
            ...styles.backgroundLandscape,
            minHeight: "100%",
          }}
        >
          <Container>
            <div className="container">
              <div className="first">
                <p className="style">Explore</p>
              </div>
              <div className="second">
                <p className="style">Mallorca</p>
              </div>
            </div>
          </Container>
        </Box>
        <Box sx={{ flex: 1 }}>{}</Box>
        <Box>
          <Container maxWidth="md" sx={{ ...styles.backgroundWhite, mt: -10 }}>
            <Grid container spacing={2} alignItems="center">
              <Grid item xs={12} sm={4}>
                <TextField
                  select
                  fullWidth
                  label="Departure Airport"
                  value={departureAirport}
                  onChange={handleDepartureAirportChange}
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
                  onChange={setDepartureDate}
                  label="Departure Date"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <DatePicker
                  sx={{ width: "100%" }}
                  value={returnDate}
                  onChange={setReturnDate}
                  label="Departure Date"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  type="number"
                  label="Adults"
                  value={countAdults}
                  onChange={(e) => setCountAdults}
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  type="number"
                  label="Children"
                  value={countChildren}
                  onChange={(e) => setCountChildren}
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  type="number"
                  label="Duration (days)"
                  value={duration}
                  onChange={(e) => setDuration}
                />
              </Grid>
              <Grid item xs={12} sx={{}}>
                <Button
                  variant="contained"
                  color="primary"
                  onClick={handleSearch}
                >
                  Search
                </Button>
              </Grid>
            </Grid>
          </Container>
        </Box>
      </ThemeProvider>
    </>
  );
}
