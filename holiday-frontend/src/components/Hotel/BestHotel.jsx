import { Box, Card, Rating, Stack, Typography } from "@mui/material";
import Carousel from "react-material-ui-carousel";

<Box
  sx={{
    backgroundImage: `/hotels/" + (hotel.id % 40) + ".jpg`,
    width: "355.5px",
    height: "200px",
    backgroundSize: "cover",
  }}
/>;

export default function BestHotel({ hotel }) {
  return (
    <Card variant="outlined" sx={{ display: "flex", mt: 3 }}>
      <Box
        sx={{
          backgroundImage: `url("/hotels/${hotel.id % 40}.jpg")`,
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
        </Stack>
        <Stack
          direction="column"
          justifyContent="space-between"
          alignItems="flex-end"
        >
          <Rating value={hotel.stars} readOnly />
        </Stack>
      </Box>
    </Card>
  );
}
