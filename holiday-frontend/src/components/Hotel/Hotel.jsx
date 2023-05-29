import {
  Box,
  Button,
  Card,
  Divider,
  Rating,
  Stack,
  Typography,
} from "@mui/material";

export default function Hotel({ hotel, callback_function, callback_query }) {
  return (
    <Card
      variant="outlined"
      sx={{
        display: "flex",
        mt: 3,
        boxShadow: 5,
        borderRadius: 3,
      }}
    >
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
          <Typography sx={{ mr: 2 }} variant="h6" fontWeight="bold">
            {hotel.name}
          </Typography>
          <Button
            variant="contained"
            sx={{ width: "140px", height: "40px" }}
            onClick={() => {
              console.log(hotel.id);
              console.log(callback_query);
              console.log(callback_function);
              callback_function(hotel.id, callback_query);
            }}
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
