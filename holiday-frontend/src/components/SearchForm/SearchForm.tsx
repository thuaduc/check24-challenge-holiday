import { useState } from "react";
import "./SearchForm.css";
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
import { useQuery } from "@tanstack/react-query";

const styles = {
  backgroundLandscape: {
    backgroundImage: `url(${mallorca})`,
    backgroundSize: "cover",
    backgroundPosition: "center",
    height: "40vh",
    width: "100%",
    minHeight: "100%",
  },

  backgroundWhite: {
    backgroundColor: "#fff",
    borderRadius: "10px",
    opacity: "0.95",
  },
};

const departureAirports = [
  { value: "JFK", label: "New York (JFK)" },
  { value: "LAX", label: "Los Angeles (LAX)" },
  { value: "LHR", label: "London Heathrow (LHR)" },
];

interface Hotel {
  hotelid: number;
  hotelname: string;
  hotelstars: number;
}

const mock_hotel: Hotel[] = [
  {
    hotelid: 1,
    hotelname: "Iberostar Playa de Muro",
    hotelstars: 4.0,
  },
  {
    hotelid: 2,
    hotelname: "Prinsotel La Pineda",
    hotelstars: 4.0,
  },
  {
    hotelid: 3,
    hotelname: "BlueSea Gran Playa",
    hotelstars: 3.0,
  },
  {
    hotelid: 4,
    hotelname: "Grupotel Gran Vista & Spa",
    hotelstars: 4.0,
  },
  {
    hotelid: 5,
    hotelname: "Playa Esperanza Hotel",
    hotelstars: 4.0,
  },
  {
    hotelid: 6,
    hotelname: "Hotel Cala d'Or",
    hotelstars: 4.0,
  },
  {
    hotelid: 7,
    hotelname: "Valentin Paguera",
    hotelstars: 3.0,
  },
  {
    hotelid: 8,
    hotelname: "Hotel Vibra Beverly Playa",
    hotelstars: 4.0,
  },
  {
    hotelid: 9,
    hotelname: "Bonsol Hotel Resort & Spa",
    hotelstars: 5.0,
  },
  {
    hotelid: 10,
    hotelname: "Bon Aire Apartments",
    hotelstars: 1.0,
  },
];

export function SearchForm() {
  const [departureAirport, setDepartureAirport] = useState("");
  const [departureDate, setDepartureDate] = useState<Dayjs | null>(null);
  const [returnDate, setReturnDate] = useState<Dayjs | null>(null);
  const [countAdults, setCountAdults] = useState<Number | null>(null);
  const [countChildren, setCountChildren] = useState<Number | null>(null);
  const [duration, setDuration] = useState<Number | null>(null);

  const handleSearch = () => {
    if (
      departureAirport == "" ||
      departureDate == null ||
      returnDate == null ||
      departureDate > returnDate ||
      duration == 0 ||
      countAdults == 0
    ) {
      alert("wrong input");
    } else {
    }
  };

  const searchQuery = useQuery<Hotel[]>({
    queryKey: ["search"],
    queryFn: () => {
      return mock_hotel;
    },
  });

  // if (searchQuery.data) {
  //   return (
  //     <div>
  //       {searchQuery.data.map((hotel) => (
  //         <div>
  //           {hotel.hotelname} has {hotel.hotelid} and {hotel.hotelstars} stars
  //         </div>
  //       ))}
  //     </div>
  //   );
  // }
  return (
    <ThemeProvider theme={CustonTheme}>
      <Box sx={styles.backgroundLandscape}>
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
        </Container>
      </Box>
    </ThemeProvider>
  );
}
