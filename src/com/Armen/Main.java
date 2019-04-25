package com.Armen;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.*;

public class Main {

    public static void main(String[] args) {

        printBirthYear();

        printNearestFridays();

        isLeap();

        calculateDeltaDate();
    }

    private static void printBirthYear() {
        System.out.print("\nPlease input your age: ");

        Scanner scanner = new Scanner(System.in);
        int age = 0;
        boolean incorrectInput = true;

        do {
            try {
                age = Integer.parseInt(scanner.next());
                if (age <= 0)
                    throw new Exception();
                incorrectInput = false;
            } catch (Exception exc) {
                System.out.print("Please enter a positive integer as your age: ");
            }
        } while (incorrectInput);


        System.out.println(LocalDate.now().getYear() - age);
    }

    private static void printNearestFridays() {
        System.out.println("\nNow, let's see the days of the previous and next Fridays. They are");

        LocalDate localDate = LocalDate.now();
        int delta = 0;

        switch (localDate.getDayOfWeek()) {
            case FRIDAY:
                delta = 7;
                break;
            case SATURDAY:
                delta = 6;
                break;
            case SUNDAY:
                delta = 5;
                break;
            case MONDAY:
                delta = 4;
                break;
            case TUESDAY:
                delta = 3;
                break;
            case WEDNESDAY:
                delta = 2;
                break;
            case THURSDAY:
                delta = 1;
                break;
            default:
                break;
        }

        LocalDate previousFriday = (7 - delta == 0) ? localDate.minusDays(7) : localDate.minusDays(7 - delta);
        LocalDate nextFriday = localDate.plusDays(delta);

        System.out.println("\tthe previous Friday is on: " + previousFriday);
        System.out.println("\tthe next Friday is on: " + nextFriday);
    }

    private static void isLeap() {
        System.out.print("\nEnter the year to check if it is leap or not: ");

        Scanner scanner = new Scanner(System.in);
        int year = 0;
        boolean incorrectInput = true;

        do {
            try {
                year = Integer.parseInt(scanner.next());
                if (year <= 0)
                    throw new Exception();
                incorrectInput = false;
            } catch (Exception exc) {
                System.out.println("Please enter an integer as a year to check if it is leap or not: ");
            }
        } while (incorrectInput);

        LocalDate Date = LocalDate.of(year, 1, 1);

        if (Date.isLeapYear()) {
            System.out.println(Date.getYear() + " is leap year");
        } else {
            System.out.println(Date.getYear() + " is not leap year");
        }
    }

    private static void calculateDeltaDate() {
        System.out.print("\nPlease input a format for date: ");
        Scanner scanner = new Scanner(System.in);
        String format = scanner.nextLine();
        DateTimeFormatter formatter;

        try {
            formatter = DateTimeFormatter.ofPattern(format);
        } catch (Exception exc) {
            System.out.print(exc + "\nYou can try it again later.\nNow, though, current time is being printed in BASIC_ISO_DATE format - yyyy-MM-dd: ");
            System.out.println(LocalDate.now());
            return;
        }

        System.out.print("Now, please input a date in the same format - " + format + " : ");

        String dateString = null;

        boolean incorrectFormat = true;
        LocalDate date = null;
        do {
            try {
                dateString = scanner.nextLine();
                date = LocalDate.parse(dateString, formatter);
                incorrectFormat = false;
            } catch (Exception exc) {
                System.out.print("The format differs from " + format + ". Please follow the pattern or type 'q' to quite: ");
            }
        } while (incorrectFormat && !dateString.equals("q"));

        if(incorrectFormat) {
            return;
        }

        System.out.println(LocalDate.now().toEpochDay() - date.toEpochDay() + " days");
    }
}
