import { useState } from "react";
import {
  Box,
  Container,
  Grid,
  TextField,
  Button,
  MenuItem,
  Typography,
  Stack,
} from "@mui/material";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import mallorca from "../../background/mallorca.jpeg";
import { departure_airports } from "./DepartureAirportsData";
import dayjs from "dayjs";

const bg_landscape = {
  backgroundImage: `linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url(${mallorca})`,
  backgroundSize: "cover",
  backgroundPosition: "center",
  height: 400,
};
const bg_white = {
  backgroundColor: "secondary.main",
  borderRadius: "15px",
  opacity: "0.97",
  mt: -10,
  boxShadow: 2,
};

export function SearchForm(search_function, set_parsed_query) {
  const [departureAirport, setDepartureAirport] = useState("MUC");
  const [departureDate, setDepartureDate] = useState(dayjs());
  const [returnDate, setReturnDate] = useState(dayjs().add(2, "day"));
  const [countAdults, setCountAdults] = useState(2);
  const [countChildren, setCountChildren] = useState(0);
  const [duration, setDuration] = useState(0);

  return (
    <Box sx={{ bgcolor: "secondary.main" }}>
      <Box sx={bg_landscape}>
        <Container sx={{ pt: 10 }}>
          <Typography
            variant="h4"
            fontWeight="bold"
            color={"#fff"}
            sx={{ ml: "5px" }}
          >
            Welcome to
          </Typography>
          <Typography variant="h1" fontWeight="bold" color={"#fff"}>
            Check-24 Mallorca
          </Typography>
          <Typography
            variant="h6"
            fontWeight="semi-bold"
            color={"#fff"}
            sx={{ ml: "5px" }}
          >
            Book your stay and enjoy the best accommodation in Mallorca
          </Typography>
        </Container>
      </Box>
      <Box>
        <Container maxWidth="lg" sx={bg_white}>
          <Grid container spacing={2} sx={{ pt: 2 }}>
            <Grid item xs={12} sm={4}>
              <TextField
                select
                fullWidth
                label={
                  <Typography variant="subtitel2" fontWeight="bold">
                    {"Departure Airport"}
                  </Typography>
                }
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
                label={
                  <Typography variant="subtitel2" fontWeight="bold">
                    {"Departure Date"}
                  </Typography>
                }
                value={departureDate}
                onChange={(e) => setDepartureDate(e)}
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <DatePicker
                sx={{ width: "100%" }}
                label={
                  <Typography variant="subtitel2" fontWeight="bold">
                    {"Return Date"}
                  </Typography>
                }
                value={returnDate}
                onChange={(e) => setReturnDate(e)}
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                fullWidth
                type="number"
                label={
                  <Typography variant="subtitel2" fontWeight="bold">
                    {"Adults"}
                  </Typography>
                }
                value={countAdults}
                onChange={(e) => setCountAdults(parseInt(e.target.value))}
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                fullWidth
                type="number"
                label={
                  <Typography variant="subtitel2" fontWeight="bold">
                    {"Children"}
                  </Typography>
                }
                value={countChildren}
                onChange={(e) => setCountChildren(parseInt(e.target.value))}
              />
            </Grid>
            <Grid item xs={12} sm={4}>
              <TextField
                fullWidth
                type="number"
                label={
                  <Typography variant="subtitel2" fontWeight="bold">
                    {"Durations (days)"}
                  </Typography>
                }
                value={duration}
                onChange={(e) => setDuration(parseInt(e.target.value))}
              />
            </Grid>
          </Grid>
          <Stack sx={{ pb: 2, pt: 2, alignItems: "center" }}>
            <Button
              variant="contained"
              color="primary"
              size="medium"
              sx={{ width: 250 }}
              onClick={() => {
                search_function.search_function(
                  departureAirport,
                  departureDate,
                  returnDate,
                  countAdults,
                  countChildren
                );
              }}
            >
              Search Now
            </Button>
          </Stack>
        </Container>
      </Box>
      <Box sx={{ height: 50, bgcolor: "secondary.main" }}></Box>
    </Box>
  );
}
