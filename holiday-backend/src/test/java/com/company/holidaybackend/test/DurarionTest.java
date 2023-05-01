package com.company.holidaybackend.test;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DurarionTest {


    public static void main(String[] args) {
        String inbound = "2022-10-05T09:30:00+02:00";
        String outbound = "2022-10-06T09:30:00+03:00";

        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        OffsetDateTime out = OffsetDateTime.parse(outbound, formatter);

        OffsetDateTime in = OffsetDateTime.parse(inbound, formatter);

        Long duration = Duration.between(out, in).toDays();


        String text = "age>25,name:John,gender:Male,age:30,occupation:Developer,";
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(text);

    }
}
