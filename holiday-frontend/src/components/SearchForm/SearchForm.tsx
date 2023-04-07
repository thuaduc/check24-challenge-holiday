import { useState } from "react";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import Mallorca from "../../../public/mallorca.jpeg";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import DatePicker from "@mui/lab/DatePicker";
import Typography from "@mui/material/Typography";
import dayjs, { Dayjs } from "dayjs";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";

const styles = {
  backgroundLandscape: {
    backgroundImage: `url(${Mallorca})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    height: "50vh",
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

  const handleCountAdults = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = parseInt(e.target.value);
    if (value == null) {
    } else if (value >= 1) {
      setCountAdults(value);
    } else {
      setCountAdults(1);
    }
  };

  const handleCountChildren = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = parseInt(e.target.value);
    if (value >= 1) {
      setCountChildren(value);
    } else {
      setCountChildren(1);
    }
  };

  const handleDuration = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = parseInt(e.target.value);
    if (value >= 1) {
      setDuration(value);
    } else {
      setDuration(1);
    }
  };

  const handleSearch = () => {};

  return (
    <>
      <Box
        sx={{
          ...styles.backgroundLandscape,
          display: "flex",
          flex: 1,
          minHeight: "100%",
        }}
      ></Box>
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
                fullWidth
                sx={{ width: "100%" }}
                value={departureDate}
                onChange={setDepartureDate}
                label="Departure Date"
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <DatePicker
                fullWidth
                sx={{ width: "100%" }}
                value={departureDate}
                onChange={setDepartureDate}
                label="Departure Date"
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                fullWidth
                type="number"
                label="Adults"
                value={countAdults}
                onChange={handleCountAdults}
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                fullWidth
                type="number"
                label="Children"
                value={countChildren}
                onChange={(e) => {
                  handleCountChildren;
                }}
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                fullWidth
                type="number"
                label="Duration (days)"
                value={duration}
                onChange={handleDuration}
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
    </>
  );
}
