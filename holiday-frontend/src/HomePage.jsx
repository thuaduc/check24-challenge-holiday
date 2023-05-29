import { SearchForm } from "./components/SearchForm/SearchForm";
import { BestHotels } from "./components/BestHotel/BestHotels";
import { Features } from "./components/Features/Features";
import { useState } from "react";
import Hotel from "./components/Hotel/Hotel";
import Offer from "./components/Offer/Offer";
import { Box, Button, Container, List, Typography, Stack } from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";

export const HomePage = () => {
  const [hotels, sethotels] = useState([]);
  const [offers, setoffers] = useState([]);
  const [status, setStatus] = useState("home");
  const [parsedQuery, setParsedQuery] = useState();

  const handleSearch = async (
    departureAirport,
    departureDate,
    returnDate,
    countAdults,
    countChildren
  ) => {
    const parsed_query = Promise.resolve({
      departureAirport: departureAirport,
      departureDate: departureDate.toISOString(),
      returnDate: returnDate.toISOString(),
      countAdults: countAdults,
      countChildren: countChildren,
    }).then((data) => setParsedQuery(data));

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
        if (data.length > 0) {
          setStatus("hotel");
        } else {
          setStatus("no-hotel");
        }
      })
      .catch((error) => console.log(error));
  };

  const submitQuerryOffer = async (hotelId, parsedQuery) => {
    const query =
      "http://localhost:8080/api/v1/offer?search=id==" +
      hotelId +
      ",outboundDepartureAirport==" +
      parsedQuery.departureAirport +
      ",outboundDepartureDatetime==" +
      parsedQuery.departureDate.replace("Z", "+00:00") +
      ",inboundArrivalDatetime==" +
      parsedQuery.returnDate.replace("Z", "+00:00") +
      ",countAdults==" +
      parsedQuery.countAdults +
      ",countChildren==" +
      parsedQuery.countChildren;

    console.log(query);
    fetch(query)
      .then((respone) => respone.json())
      .then((data) => {
        setoffers(data);
      })
      .catch((error) => console.log(error));
    setStatus("offer");
  };

  const saveQuery = async (parsed_query) => {
    console.log(parsedQuery);
    setParsedQuery(parsed_query);
  };

  switch (status) {
    case "home":
      return (
        <>
          <SearchForm
            search_function={handleSearch}
            set_parsed_query={saveQuery}
          />
          <BestHotels />
          <Features />
        </>
      );
    case "no-hotel":
      return (
        <Container>
          <Stack direction="column" sx={{ height: 660 }}>
            <Typography
              variant="h3"
              fontWeight="semi-bold"
              align="center"
              mt={10}
            >
              No Hotel Founded
            </Typography>
            <Button
              href="/"
              color="primary"
              sx={{ mt: 5 }}
              startIcon={<HomeIcon />}
            >
              My Holiday
            </Button>
          </Stack>
        </Container>
      );
    case "hotel":
      return (
        <>
          <SearchForm
            search_function={handleSearch}
            set_parsed_query={saveQuery}
          />
          <Box sx={{ bgcolor: "seconday.main" }}>
            <Container>
              <List>
                {hotels.map((h) => (
                  <li key={h.id}>
                    <Hotel
                      hotel={h}
                      callback_function={submitQuerryOffer}
                      callback_query={parsedQuery}
                    />
                  </li>
                ))}
              </List>
            </Container>
          </Box>
        </>
      );

    case "offer":
      console.log(offers);
      return (
        <>
          <SearchForm
            search_function={handleSearch}
            set_parsed_query={saveQuery}
          />
          <Box sx={{ bgcolor: "seconday.main" }}>
            <Container>
              <Button
                sx={{ mt: 5, mb: 5, color: "primary" }}
                onclick={() => {
                  setStatus("hotel");
                  window.location.reload();
                }}
              >
                Back to Hotel List
              </Button>
              <List>
                {offers.map((o) => (
                  <li key={o.id}>
                    <Offer parsed_offer={o} is_order={false} />
                  </li>
                ))}
              </List>
            </Container>
          </Box>
        </>
      );
  }
};
