import {
  Box,
  Button,
  Card,
  Divider,
  Rating,
  Stack,
  Typography,
} from "@mui/material";

export default function Hotel({ hotel, callbackFunction, callbackQuery }) {
  return (
    <Card variant="outlined" sx={{ display: "flex", mt: 3 }}>
      <Box
        sx={{
          backgroundImage: `url("/hotels/${(hotel.id % 40) + 1}.jpg")`,
          width: "355.5px",
          height: "200px",
          backgroundSize: "cover",
        }}
      />
      <Box
        sx={{
          p: 1,
          pl: 2,
          display: "flex",
          width: "100%",
          flexDirection: "row",
          justifyContent: "space-between",
        }}
      >
        <Stack direction="column" justifyContent="space-between">
          <Typography sx={{ mr: 2 }} variant="h6">
            {hotel.name}
          </Typography>

          <Stack>
            <Typography variant="body1">Halfboard</Typography>
            <Typography variant="body1">Apartment</Typography>
          </Stack>

          <Button
            variant="contained"
            sx={{ width: "140px", height: "40px" }}
            onClick={() => callbackFunction(hotel.id, callbackQuery)}
          >
            View {hotel.count} {hotel.count > 1 ? "Offers" : "Offer"}
          </Button>
        </Stack>
        <Stack
          direction="column"
          justifyContent="space-between"
          alignItems="flex-end"
        >
          <Rating value={hotel.stars} readOnly />

          <Stack>
            <Stack
              m={0}
              direction="row"
              divider={<Divider orientation="vertical" flexItem />}
              spacing={1}
            ></Stack>
            <Typography variant="h6" textAlign="right">
              from {hotel.min_price} €
            </Typography>
          </Stack>
        </Stack>
      </Box>
    </Card>
  );
}
