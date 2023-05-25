import {
  Button,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Typography,
  Stack,
  Box,
} from "@mui/material";
import { Bed, RestaurantMenu, Water, Hotel } from "@mui/icons-material";
import { useEffect, useState } from "react";
import Flight from "../Flight/Flight";

const getNameAndStars = async (hotelId) => {
  const query = "http://localhost:8080/api/v1/hotel/" + hotelId;

  try {
    const response = await fetch(query);
    const data = await response.json();
    const name = data.name;
    const stars = data.stars;
    return [name, stars];
  } catch (error) {
    console.log(error);
    return [];
  }
};

const handleAdd = async (offerId) => {
  const query = "http://localhost:8080/api/v1/cart/" + offerId;
  console.log(query);
  await fetch(query, {
    method: "POST",
  }).catch((error) => {
    console.log(error);
  });
  //window.location.reload();
};

const handleDelete = async (offerId) => {
  const query = "http://localhost:8080/api/v1/cart/delete/" + offerId;
  console.log(query);
  await fetch(query, {
    method: "DELETE",
  }).catch((error) => {
    console.log(error);
  });
  //window.location.reload();
};

const getTravelDurationString = (departure, arrival) => {
  const date1 = new Date(departure);
  const date2 = new Date(arrival);
  const difference = Math.abs(date1.getTime() - date2.getTime());
  return Math.ceil(difference / (1000 * 3600 * 24)).toString();
};

const Offer = ({ parsed_offer, is_order }) => {
  const [nameAndStars, setNameAndStars] = useState([]);
  const offer = parsed_offer;
  const button = is_order ? "Delete" : "Add to Cart";
  const button_color = is_order ? "error" : "primary";

  useEffect(() => {
    getNameAndStars(offer.hotelId).then((result) => {
      setNameAndStars(result);
    });
  }, [offer.hotelId]);

  return (
    <>
      {is_order == false && (
        <div>
          <h1>{nameAndStars[0]}</h1>
          <h4>{nameAndStars[1]} stars</h4>
          <Stack direction="row">
            <Box
              sx={{
                backgroundImage: `url("/hotels/${
                  (offer.hotelId % 40) + 1
                }.jpg")`,
                width: "355.5px",
                height: "300px",
                backgroundSize: "cover",
              }}
            />
            <Box
              sx={{
                backgroundImage: `url("/rooms/${
                  (offer.hotelId % 30) + 1
                }.jpg")`,
                width: "355.5px",
                height: "300px",
                backgroundSize: "cover",
              }}
            />
            <Box
              sx={{
                backgroundImage: `url("/rooms/${
                  (offer.hotelId % 29) + 2
                }.jpg")`,
                width: "355.5px",
                height: "300px",
                backgroundSize: "cover",
              }}
            />
          </Stack>
          <br />
          <br />
          <Typography>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim
            ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
            aliquip ex ea commodo consequat. Duis aute irure dolor in
            reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
            pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
            culpa qui officia deserunt mollit anim id est laborum.
          </Typography>
          <br />
          <br />
          <br />
        </div>
      )}
      <Card>
        <CardHeader
          sx={{ backgroundColor: "#ededed" }}
          title={
            <Typography fontWeight="bold">
              {"From " + offer.outboundDepartureAirport + " to FMI"}
            </Typography>
          }
        ></CardHeader>
        <CardContent>
          <Stack direction="row" justifyContent="space-between">
            <Stack gap={2}>
              <Flight
                inbound={true}
                departureDatetime={offer.outboundDepartureDatetime}
                departureAirport={offer.outboundDepartureAirport}
                arrivalDatetime={offer.outboundArrivalDatetime}
                arrivalAirport={"PMI"}
              />
              <Flight
                inbound={false}
                departureDatetime={offer.inboundDepartureDatetime}
                departureAirport={"PMI"}
                arrivalDatetime={offer.inboundArrivalDatetime}
                arrivalAirport={offer.outboundDepartureAirport}
              />
            </Stack>
            <Stack gap={2}>
              <Stack direction="row" alignItems="center">
                <Hotel />
                <Typography ml={1} variant="body1">
                  {getTravelDurationString(
                    offer.outboundDepartureDatetime,
                    offer.inboundArrivalDatetime
                  ) + " Nights"}
                </Typography>
              </Stack>
              <Stack direction="row" alignItems="center">
                <RestaurantMenu />
                <Typography ml={1} variant="body1">
                  {offer.mealType}
                </Typography>
              </Stack>
              <Stack direction="row" alignItems="center">
                <Bed />
                <Typography ml={1} variant="body1">
                  {offer.roomType}
                </Typography>
              </Stack>
              {offer.oceanView && (
                <Stack direction="row" alignItems="center">
                  <Water />
                  <Typography ml={1} variant="body1">
                    Oceanview
                  </Typography>
                </Stack>
              )}
            </Stack>
            <Stack justifyContent="end" gap={2}>
              <Stack
                m={0}
                direction="row"
                divider={<Divider orientation="vertical" flexItem />}
                spacing={1}
              >
                <Typography variant="body1">
                  {getTravelDurationString(
                    offer.outboundDepartureDatetime,
                    offer.inboundArrivalDatetime
                  )}{" "}
                  Days
                </Typography>
                <Typography variant="body1">
                  {offer.countAdults} Adults
                </Typography>
                <Typography variant="body1">
                  {offer.countChildren} Children
                </Typography>
              </Stack>
              <Typography variant="h5" textAlign="right">
                {offer.price} €
              </Typography>
              <Button
                variant="contained"
                color={button_color}
                onClick={() => {
                  if (is_order) {
                    handleDelete(offer.offerId);
                  } else {
                    handleAdd(offer.offerId);
                  }
                }}
              >
                {button}
              </Button>
            </Stack>
          </Stack>
        </CardContent>
      </Card>
    </>
  );
};

export default Offer;
