import { SearchForm } from "../components/SearchForm/SearchForm";
import { BestHotels } from "../components/BestHotel/BestHotels";
import { Features } from "../components/Features/Features";
import { useState, useEffect } from "react";
import Hotel from "../components/Hotel/Hotel";
import Offer from "../components/Offer/Offer";
import {
  Box,
  Button,
  Container,
  List,
  Typography,
  Stack,
  Rating,
} from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import { API_LIST, API_OFFER, API_HOTEL_GET } from "../Api";
import { useSearchParams } from "react-router-dom";

export const HomePage = () => {
  const [hotels, sethotels] = useState([]);
  const [offers, setoffers] = useState([]);
  const [status, setStatus] = useState("home");
  const [parsedQuery, setParsedQuery] = useState();
  const [offerId, setOfferId] = useState();
  const [nameAndStars, setNameAndStars] = useState();

  const handleSearch = async (
    departureAirport,
    departureDate,
    returnDate,
    countAdults,
    countChildren
  ) => {
    Promise.resolve({
      departureAirport: departureAirport,
      departureDate: departureDate.toISOString(),
      returnDate: returnDate.toISOString(),
      countAdults: countAdults,
      countChildren: countChildren,
    }).then((data) => setParsedQuery(data));

    const query =
      API_LIST +
      "outboundDepartureAirport=" +
      departureAirport +
      ",outboundDepartureDatetime=" +
      departureDate.toISOString().replace("Z", "+00:00") +
      ",inboundArrivalDatetime=" +
      returnDate.toISOString().replace("Z", "+00:00") +
      ",countAdults=" +
      countAdults +
      ",countChildren=" +
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

  const submitQuerryOffer = async (
    hotelId,
    hotelName,
    hotelStars,
    parsedQuery
  ) => {
    setNameAndStars([hotelName, hotelStars]);
    const query =
      API_OFFER +
      "id=" +
      hotelId +
      ",outboundDepartureAirport=" +
      parsedQuery.departureAirport +
      ",outboundDepartureDatetime=" +
      parsedQuery.departureDate.replace("Z", "+00:00") +
      ",inboundArrivalDatetime=" +
      parsedQuery.returnDate.replace("Z", "+00:00") +
      ",countAdults=" +
      parsedQuery.countAdults +
      ",countChildren=" +
      parsedQuery.countChildren;

    console.log(query);
    fetch(query)
      .then((respone) => respone.json())
      .then((data) => {
        setoffers(data);
        setOfferId(data[0].hotelId);
      })
      .then(() => {
        setStatus("offer");
      })
      .catch((error) => console.log(error));
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
      console.log(nameAndStars);
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
                onClick={() => {
                  setStatus("hotel");
                }}
              >
                Back to Hotel List
              </Button>
              <Typography variant="h4" fontWeight="bold">
                {nameAndStars[0]}
              </Typography>
              <Rating
                sx={{ mb: 5 }}
                value={parseInt(nameAndStars[1])}
                readOnly
              />
              <Stack direction="row" sx={{ boxShadow: 5 }}>
                <Box
                  sx={{
                    backgroundImage: `url("/hotels/${(offerId % 40) + 1}.jpg")`,
                    width: "400px",
                    height: "300px",
                    backgroundSize: "cover",
                  }}
                />
                <Box
                  sx={{
                    backgroundImage: `url("/rooms/${(offerId % 30) + 1}.jpg")`,
                    width: "400px",
                    height: "300px",
                    backgroundSize: "cover",
                  }}
                />
                <Box
                  sx={{
                    backgroundImage: `url("/rooms/${(offerId % 29) + 2}.jpg")`,
                    width: "400px",
                    height: "300px",
                    backgroundSize: "cover",
                  }}
                />
              </Stack>
              <Typography
                variant="body1"
                fontWeight="medium"
                sx={{ mt: 5, mb: 5 }}
              >
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                enim ad minim veniam, quis nostrud exercitation ullamco laboris
                nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor
                in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                sunt in culpa qui officia deserunt mollit anim id est laborum.
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
                enim ad minim veniam, quis nostrud exercitation ullamco laboris
                nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor
                in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                sunt in culpa qui officia deserunt mollit anim id est laborum.
              </Typography>
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
