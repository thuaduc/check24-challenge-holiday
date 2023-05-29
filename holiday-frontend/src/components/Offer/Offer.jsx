import {
  Button,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Typography,
  Stack,
} from "@mui/material";
import { Bed, RestaurantMenu, Water, Hotel } from "@mui/icons-material";
import Flight from "../Flight/Flight";
import { API_CART_DELETE, API_CART_POST } from "../../Api";
import { getMealType, getRoomType, getOceanView } from "../Mro";

const handleAdd = async (offerId) => {
  const query = API_CART_POST + offerId;
  console.log(query);
  await fetch(query, {
    method: "POST",
  }).catch((error) => {
    console.log(error);
  });
  //window.location.reload();
};

const handleDelete = async (offerId) => {
  const query = API_CART_DELETE + offerId;
  console.log(query);
  await fetch(query, {
    method: "DELETE",
  }).catch((error) => {
    console.log(error);
  });
  window.location.reload();
};

const getTravelDurationString = (departure, arrival) => {
  const date1 = new Date(departure);
  const date2 = new Date(arrival);
  const difference = Math.abs(date1.getTime() - date2.getTime());
  return Math.ceil(difference / (1000 * 3600 * 24)).toString();
};

const Offer = ({ parsed_offer, is_order }) => {
  const offer = parsed_offer;
  const button = is_order ? "Delete" : "Add to Cart";
  const button_color = is_order ? "error" : "primary";

  return (
    <>
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
                  ) + " Days"}
                </Typography>
              </Stack>
              <Stack direction="row" alignItems="center">
                <RestaurantMenu />
                <Typography ml={1} variant="body1">
                  {getMealType(offer.mro)}
                </Typography>
              </Stack>
              <Stack direction="row" alignItems="center">
                <Bed />
                <Typography ml={1} variant="body1">
                  {getRoomType(offer.mro)}
                </Typography>
              </Stack>
              {getOceanView(offer.mro) && (
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
