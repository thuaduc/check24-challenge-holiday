import {
  Box,
  Container,
  Grid,
  Typography,
  Card,
  CardContent,
} from "@mui/material";

export const Features = () => {
  const card_style = {
    width: 330,
    height: 190,
    m: 3,
    bgcolor: "#ffffff",
    boxShadow: 5,
    borderRadius: 3,
  };

  return (
    <Box sx={{ bgcolor: "#ffffff", pt: 15, pb: 15 }}>
      <Container>
        <Typography variant="h4" fontWeight={"bold"} sx={{ mb: 2 }}>
          Check out the greatest features from Check24 Holiday!
        </Typography>
        <Grid
          container
          direction="row"
          spacing={{ xl: 5, sm: 1 }}
          justifyContent="center"
        >
          <Grid item xl={3}>
            <Card sx={card_style}>
              <CardContent>
                <Typography
                  variant="h6"
                  fontWeight="bold"
                  component="div"
                  sx={{ mb: 1 }}
                >
                  Extensive Hotel Selection
                </Typography>
                <Typography variant="body1">
                  Check24 offers a wide range of hotels from various locations
                  in Mallorca, catering to diverse preferences and budgets.
                </Typography>
              </CardContent>
            </Card>
          </Grid>
          <Grid item xl={3}>
            <Card sx={{ ...card_style, mt: 10, height: 230 }}>
              <CardContent>
                <Typography
                  variant="h6"
                  fontWeight="bold"
                  component="div"
                  sx={{ mb: 1 }}
                >
                  User-Friendly Interface
                </Typography>
                <Typography variant="body1">
                  With an intuitive interface, Check24 allows users to easily
                  navigate, refine search results using filters, and access
                  hotel information, including reviews and images, to make
                  informed decisions swiftly.
                </Typography>
              </CardContent>
            </Card>
          </Grid>
          <Grid item xl={3}>
            <Card sx={card_style}>
              <CardContent>
                <Typography
                  variant="h6"
                  fontWeight="bold"
                  component="div"
                  sx={{ mb: 1 }}
                >
                  Competitive Prices and Deals
                </Typography>
                <Typography variant="body1">
                  Check24 provides competitive rates on hotel bookings,
                  comparing prices from multiple providers and featuring
                  exclusive discounts and promotions.
                </Typography>
              </CardContent>
            </Card>
          </Grid>
        </Grid>
      </Container>
    </Box>
  );
};
