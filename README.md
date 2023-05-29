# Check24 Holliday Challenge

## Table of Contents

- [Check24 Holliday Challenge](#check24-holliday-challenge)
  - [Table of Contents](#table-of-contents)
  - [How to start](#how-to-start)
  - [Disclaimer](#disclaimer)
  - [Features](#features)
  - [Requirements](#requirements)
  - [Usage](#usage)
  - [License](#license)

## How to start

I have cleaned and adapted the given data to optimize the performance of my code. The actuall data.csv file in holiday-backend/src/main/recources/ contains only 100000 rows.

Use this jupyter-notebook script to clean your data.

Alternatively you can download the cleaned version here.

Replace data.csv file in holiday-backend/src/main/recources/data

In backend folder, run: docker-compose up --build

In frontend folder, run:

- npm i to install all packages
- npm run dev so start the project
- click on the link on console

If you want to make a change in backend, run the following commands in backend folder:

- mvn clean install to rebuild jar file
- docker-compose up --build

## Disclaimer

This project is my personal project for check24 coding challange. Copy right of the data (hotels.csv and offers.csv) belongs to Check24. More detail could be found here. {link}

## Features

My web application includes the following features:

- User can search for list of all hotels that match the search query.
- User can view all offers from the chosen hotel.
- User can see the weather in Mallorca.

## Requirements

To use our app, you'll need the following:

For backend:

- Docker to run the container
- Java 20

For fontend:

- Node js

## Usage

To use our app, follow these steps:

1. [Step 1]
2. [Step 2]
3. [Step 3]
4. ...

## License

Our app is licensed under the [insert license here] license. See the `LICENSE` file for more details.
