const roomtype = {
  A: "ACCORDINGDESCRIPTION",
  B: "APARTMENT",
  C: "BUNGALOW",
  D: "DELUXE",
  E: "DOUBLE",
  F: "ECONOMY",
  G: "FAMILY",
  H: "FOURBEDROOM",
  I: "HOLIDAYHOUSE",
  K: "JUNIORSUITE",
  L: "MULTISHARE",
  M: "SINGLE",
  N: "STUDIO",
  O: "SUITE",
  P: "SUPERIOR",
  Q: "TRIPLE",
  R: "TWINROOM",
  S: "VILLA",
};

const mealtype = {
  A: "ACCORDINGDESCRIPTION",
  B: "ALLINCLUSIVE",
  C: "ALLINCLUSIVEPLUS",
  D: "BREAKFAST",
  E: "FULLBOARD",
  F: "FULLBOARDPLUS",
  G: "HALFBOARD",
  H: "HALFBOARDPLUS",
  I: "NONE",
};

const oceanview = {
  T: true,
  F: false,
};

const getMealType = (mro) => {
  const m = mro[0];
  return mealtype[m];
};

const getRoomType = (mro) => {
  const r = mro[1];
  return roomtype[r];
};

const getOceanView = (mro) => {
  const o = mro[2];
  return oceanview[o];
};

export { getMealType, getRoomType, getOceanView };
