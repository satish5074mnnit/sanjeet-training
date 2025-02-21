package com.example.demo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateConverter {
    public static void main(String[] args) {
        String input = "I24.12.0"; // You can change this to test different inputs

        // Define a regular expression pattern to match digit sequences
        Long minVersion = Long.parseLong("241200");

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);

        StringBuilder digitSequence = new StringBuilder();

        // Find all digit sequences and append them to the StringBuilder
        int i = 1;
        while (matcher.find()) {
            String match = matcher.group();
            // Append the matched digits and then append '0'
            if (match.length() == 1) {
                if (i == 2) digitSequence.append('0').append(match);
                else digitSequence.append(match).append('0');


            } else digitSequence.append(match);
            i++;
        }


        // Convert the resulting string to an integer
        Long number = Long.parseLong(digitSequence.toString());
        System.out.println(number);

        System.out.println(number.compareTo(minVersion));
    }
}