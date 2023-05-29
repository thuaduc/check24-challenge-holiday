# Check24 Holliday Challenge

## Disclaimer

This project is my personal project for check24 coding challange. Copy right of the data (hotels.csv and offers.csv) belongs to Check24. More detail could be found here. {link}

## Table of Contents

<details>
- [Check24 Holliday Challenge](#check24-holliday-challenge)
  - [Table of Contents](#table-of-contents)
  - [Disclaimer](#disclaimer)
  - [Requirements](#requirements)
  - [How to start](#how-to-start)
  - [Data](#data)
  - [Backend](#backend)
  - [Frontend](#frontend)
  - [Features](#features)
  - [License](#license)
</details>

# Getting started

## Requirements

To use our app, you'll need the following:

### For backend:

- Docker to run the container
- Java 20

### For frontend:

- Node js

## How to start

### Clean data

I have cleaned and adapted the given data to optimize the performance of my code. See [Data](#data) for more Details. The actuall **data.csv** file in **holiday-backend/src/main/recources/data/** contains only 100000 rows. For starter you can just use the given data, or clean your data like following:

- Use this jupyter-notebook script to clean your data.

- Alternatively you can download the cleaned version [here](https://www.google.com).

Replace **data.csv*+* file in **holiday-backend/src/main/recources/data\*\*

### Start Backend

In backend folder, run:

- `mvn clean install`
- `docker-compose up --build`

If you made any change in backend, rerun those command

### Start Frontend

In frontend folder, run:

- `npm i` to install all packages
- `npm run dev` so start the project

## Data

## Backend

## Frontend

## Features

My web application includes the following features:

- User can search for list of all hotels that match the search query.
- User can view all offers from the chosen hotel.
- User can see the weather in Mallorca.

## License

Our app is licensed under the [insert license here] license. See the `LICENSE` file for more details.
