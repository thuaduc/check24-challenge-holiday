import {
  Box,
  Container,
  Typography,
  ImageList,
  Rating,
  Card,
  CardMedia,
  CardContent,
} from "@mui/material";
import { best_hotels } from "./BestHotelsData";

export const BestHotels = () => {
  return (
    <Box sx={{ mt: -10, pb: 15, bgcolor: "secondary.main" }}>
      <Container sx={{ borderRadius: "15px" }}>
        <Typography variant="h4" fontWeight={"bold"} sx={{ mt: 10, pt: 10 }}>
          Discover the best hotels in Mallorca!
        </Typography>
        <ImageList
          sx={{
            pt: 5,
            gridAutoFlow: "column",
            gridTemplateColumns:
              "repeat(auto-fill(minmax(200px,1fr)) !improtant",
            gridAutoColumns: "minmax(200px,1fr)",
            overflow: "hidden",
          }}
          rowHeight={350}
          cols={3}
          gap={5}
          variant="standart"
        >
          {best_hotels.map((hotel) => (
            <Card
              sx={{
                width: 370,
                height: 350,
                boxShadow: 5,
                mb: 3,
                ml: 1,
                borderRadius: 5,
                bgcolor: "secondary.main",
                ":hover": {
                  boxShadow: 10,
                },
              }}
            >
              <CardMedia
                sx={{ height: 270 }}
                image={`/hotels/${hotel.id % 40}.jpg`}
              />
              <CardContent>
                <Typography
                  variant="subtitel1"
                  fontWeight="bold"
                  component="div"
                >
                  {hotel.name}
                </Typography>
                <Typography variant="body1">
                  {<Rating value={hotel.stars} readOnly />}
                </Typography>
              </CardContent>
            </Card>
          ))}
        </ImageList>
      </Container>
    </Box>
  );
};
