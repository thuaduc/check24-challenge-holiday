# Check24 Holliday Challenge

## Table of Contents

- [Check24 Holliday Challenge](#check24-holliday-challenge)
  - [Table of Contents](#table-of-contents)
  - [Disclaimer](#disclaimer)
- [Getting started](#getting-started)
  - [Demo](#demo)
  - [Prerequisites](#prerequisites)
    - [For Data-cleaning (optional)](#for-data-cleaning-optional)
    - [For Backend:](#for-backend)
    - [For Frontend:](#for-frontend)
  - [How to start](#how-to-start)
    - [1. Clean data](#1-clean-data)
    - [2. Start Backend](#2-start-backend)
    - [3. Start Frontend](#3-start-frontend)
  - [Frameworks and Infrastructure.](#frameworks-and-infrastructure)
    - [Datasets](#datasets)
    - [Backend](#backend)
    - [Frontend](#frontend)
  - [Features](#features)

## Disclaimer

This repository is my project for the Check24 coding challenge. Copy right of the data (hotels.csv and offers.csv) belongs to Check24. More details could be found [here](https://github.com/check24-scholarships/holiday-challenge).

# Getting started

## Demo

![Screenshort 1](./screenshorts/1.png)
![Screenshort 2](./screenshorts/2.png)
![Screenshort 3](./screenshorts/3.png)
![Screenshort 4](./screenshorts/4.png)
![Screenshort 5](./screenshorts/5.png)
![Screenshort 6](./screenshorts/6.png)

## Prerequisites

To run my app, you'll need the following:

#### For Data-cleaning (optional)

- Python3/Jupter-Notebook
- Pandas

#### For Backend:

- Docker
- Java 17
- Maven
  **Note that the java version when you run `mvn -v` should also be 17 or later version**

#### For Frontend:

- Node js

## How to start

### 1. Clean data

I have cleaned and adapted the given data to optimize the performance of my code. See [Data](#data) for more Details. The actual **data.csv** file in **holiday-backend/src/main/resources/data/** contains only 700000 rows. For starters, you can just run the program without changing anything. Or clean your data like the following:

- Use the given jupyter-notebook script to clean your data.

- Alternatively, you can download the cleaned version [here]().

Replace **data.csv** file in **holiday-backend/src/main/resources/data/**

### 2. Start Backend

In holiday-backend folder, run:

1. `mvn clean install` to install the jar file.

2. `docker-compose up --build` to build docker container and run backend.

If you made any changes inside backend or replace the data, rerun those commands.

### 3. Start Frontend

In holiday-frontend folder, run:

- `npm i` to install all packages.
- `npm run dev` to start the project.

## Frameworks and Infrastructure.

### Datasets

The original offer dataset looks like so:

| hotelid | outbounddeparturedatetime | inbounddeparturedatetime  | countadults | countchildren | price | inbounddepartureairport | inboundarrivalairport | inboundarrivaldatetime    | outbounddepartureairport | outboundarrivalairport | outboundarrivaldatetime   | mealtype  | oceanview | roomtype    |
| ------- | ------------------------- | ------------------------- | ----------- | ------------- | ----- | ----------------------- | --------------------- | ------------------------- | ------------------------ | ---------------------- | ------------------------- | --------- | --------- | ----------- |
| 90      | 2022-10-05T09:30:00+02:00 | 2022-10-12T08:35:00+02:00 | 1           | 1             | 1243  | PMI                     | DUS                   | 2022-10-12T14:40:00+02:00 | DUS                      | PMI                    | 2022-10-05T14:25:00+02:00 | halfboard | FALSE     | double      |
| 1096    | 2022-07-01T11:00:00+02:00 | 2022-07-08T07:30:00+02:00 | 2           | 0             | 1710  | PMI                     | LEJ                   | 2022-07-08T10:00:00+02:00 | LEJ                      | PMI                    | 2022-07-01T13:30:00+02:00 | none      | FALSE     | apartment   |
| 656     | 2022-09-13T15:50:00+02:00 | 2022-09-21T07:50:00+02:00 | 2           | 0             | 2093  | PMI                     | FRA                   | 2022-09-21T10:10:00+02:00 | FRA                      | PMI                    | 2022-09-13T17:55:00+02:00 | breakfast | FALSE     | double      |
| 1880    | 2022-08-07T18:00:00+02:00 | 2022-08-12T08:20:00+02:00 | 2           | 0             | 1707  | PMI                     | MUC                   | 2022-08-12T10:35:00+02:00 | MUC                      | PMI                    | 2022-08-07T20:15:00+02:00 | none      | FALSE     | double      |
| 1190    | 2022-09-20T17:35:00+02:00 | 2022-09-26T07:20:00+02:00 | 2           | 0             | 1866  | PMI                     | SCN                   | 2022-09-26T09:30:00+02:00 | SCN                      | PMI                    | 2022-09-20T19:40:00+02:00 | breakfast | FALSE     | juniorsuite |

Since all the flights are one-way to Mallorca (PMI), I found the following three columns redundant and removed them:

**1. inbounddepartureairport**
**2. outboundarrivalairport**
**3. inboundarrivalairport**

Additionally, I merged the following three columns into a single column to reduce file size and improve performance:

**1. mealtype**
**2. roomtype**
**3. oceanview**

| mealtype             | decoded-value | roomtype             | decoded-value | oceanview | decoded-value |
| -------------------- | ------------- | -------------------- | ------------- | --------- | ------------- |
| ACCORDINGDESCRIPTION | A             | ACCORDINGDESCRIPTION | A             | FALSE     | F             |
| ALLINCLUSIVE         | B             | APARTMENT            | B             | TRUE      | T             |
| ALLINCLUSIVEPLUS     | C             | BUNGALOW             | C             |           |               |
| BREAKFAST            | D             | DELUXE               | D             |           |               |
| FULLBOARD            | E             | DOUBLE               | E             |           |               |
| FULLBOARDPLUS        | F             | ECONOMY              | F             |           |               |
| HALFBOARD            | G             | FAMILY               | G             |           |               |
| HALFBOARDPLUS        | H             | FOURBEDROOM          | H             |           |               |
| NONE                 | I             | HOLIDAYHOUSE         | I             |           |               |
|                      |               | JUNIORSUITE          | K             |           |               |
|                      |               | MULTISHARE           | L             |           |               |
|                      |               | SINGLE               | M             |           |               |
|                      |               | STUDIO               | N             |           |               |
|                      |               | SUITE                | O             |           |               |
|                      |               | SUPERIOR             | P             |           |               |
|                      |               | TRIPLE               | Q             |           |               |
|                      |               | TWINROOM             | R             |           |               |
|                      |               | VILLA                | S             |           |               |

The column called **mtr**, which now contains the decoded value that was previously stored across the three columns: **mealtype**, **roomtype**, and **oceanview**. By consolidating this information into a single column, we have reduced the file size and improved overall performance.

| mtr | example                                 |
| --- | --------------------------------------- |
| AAT | ACCORDINGDESCRIPTION + APARTMENT + TRUE |
| ... | ...                                     |

By merging redundant columns and optimizing the data structure, the size of the CSV file has been significantly reduced. The file size has been reduced from around 20GB to 14GB, while preserving all the necessary information. This reduction in file size helps to save storage space and potentially improves the performance of data operations.

### Backend

I used Spring Boot and the Model-View-Controller (MVC) architectural pattern to build an API service that supports HTTP methods such as GET, POST, and DELETE. The frontend client interacts with the Controller, which handles the user requests and responses. The Service layer contains all the business logic of the backend application, while the Repository layer interacts directly with the database to perform data operations.

### Frontend

Inspired by the default frontend, I developed a ReactJS application. By utilizing Material-UI (MUI), I was able to create a modern and responsive user interface (UI). I took advantage of the existing codebase and reused certain components and functions. Additionally, I enhanced these components by incorporating attributes such as BorderRadius and BoxShadow, which improved the visual appeal and added visual effects to elements within the UI.

## Features

My web application has the following features:

- Users can search for hotels that match their query.
- Users can see the offers available at a - chosen hotel.
- Users can add offers to their cart.
- Users can view and remove offers from their cart.
