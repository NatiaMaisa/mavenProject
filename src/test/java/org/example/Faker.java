package org.example;

public class Faker {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count ++;
            System.out.println("User " + count);
            userInfo();
            System.out.println("*************************");
        }
      for(int i = 1; i <= 10; i ++){
          System.out.println(i + ") ");
          userInfo();
          System.out.println("***************************");
      }
    }
    public static void userInfo(){
        com.github.javafaker.Faker faker = new com.github.javafaker.Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String address = faker.address().fullAddress();
        String phoneNumber = faker.phoneNumber().cellPhone();

        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
    }
    }
