package com.example.breadscrumbs.donation_tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import Model.SQLiteDatabaseHandler;
import Model.User;
import Model.DonationDatabaseHandler;
import Model.Donation;
import Model.Location;
import java.util.Arrays;

import static org.mockito.Mockito.*;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testAllUsersInDatabase() {

        SQLiteDatabaseHandler db = mock(SQLiteDatabaseHandler.class);
        List<User> testList = new ArrayList<>();

        User john = new User("John", "john@email.com", "123456789", User.UserType.USER);
        User alice = new User("Alice", "alice@email.com", "helloworld", User.UserType.MANAGER);
        User bob = new User("Bob", "bob@email.com", "@objectsDesign!", User.UserType.USER);
        User carol = new User("Carol", "carol@email.com", "@ObjectSdESIGN!", User.UserType.ADMIN);

        testList.add(john);
        testList.add(alice);
        testList.add(bob);
        testList.add(carol);

        when(db.allUsers()).thenReturn(testList);

        db.addUser(john);
        db.addUser(alice);
        db.addUser(bob);
        db.addUser(carol);
        assertEquals(db.allUsers(), testList);

        //Tests adding a duplicate email to the database. All Users should remain the same
        when(db.allUsers()).thenReturn(testList);
        User james = new User("James", "john@email.com", "987654321",User.UserType.USER);
        db.addUser(james);
        assertEquals(db.allUsers(), testList);

        when(db.allUsers()).thenReturn(testList);
        List<User> appendedList = new ArrayList<User>();
        for (User u : testList) {
            appendedList.add(u);
        }
        appendedList.add(james);
        assertNotEquals(db.allUsers(), appendedList);
    }

    @Test
    public void testNoUsersInDatabase() {
        SQLiteDatabaseHandler db = mock(SQLiteDatabaseHandler.class);
        when(db.allUsers()).thenReturn(null);
        assertNull(db.allUsers());
    }

    @Test
    /* Author: Sophia Yan */
    /* Method tested: getCategoryItems(String item, Location location) in DonationDatabaseHandler
       This method returns all items in a particular category at a certain location. If location is
       null, then all items of that category are returned across all locations.
       Branch coverage: -Returns all 2 hats at location 1 when passed in parameters Hat and location1
                        -Returns all 3 hats at location 1 and 2 when passed in Hat and null
                        -Returns sweater at location 2  when Clothing and location2 are passed in
                        -Returns an empty array when Appliances and location 1 are passed in, because
                         there are no appliances in location 1
                         -Returns an empty array when Appliances and null are passed in, because there
                         are no appliances in any of the locations
     */
    public void testDatabase() {
        //Location instances
        Location testLocation1 = new Location("1", "Location1", "30", "30",
                "Main street", "Springfield","GA", "12345",
                "building","123", "google.com");
        Location testLocation2 = new Location("2", "Location2", "30", "30",
                "Main street", "Springfield","GA", "12345",
                "building","123", "google.com");
        //Donation instances
        Donation hat1 = new Donation("Hat1", "Hat", "11/2/18",
                "4", testLocation1, "Hat", "No comment");
        Donation hat2 = new Donation("Hat2", "Hat", "11/2/18",
                "4", testLocation1, "Hat", "No comment");
        Donation hat3 = new Donation("Hat3", "Hat", "11/2/18",
                "7", testLocation2, "Hat", "No comment");
        Donation sweater1 = new Donation("Sweater1", "Sweater", "11/2/18",
                "7", testLocation2, "Clothing", "No comment");
        //Database setup
        DonationDatabaseHandler db = mock(DonationDatabaseHandler.class);
        db.addDonation(hat1, "1");
        db.addDonation(hat2, "1");
        db.addDonation(hat3, "2");

        when(db.getCategoryItems("Hat", testLocation1)).thenReturn(
                new ArrayList<Donation>(Arrays.asList(new Donation[]{hat1, hat2})));
        when(db.getCategoryItems("Hat", null)).thenReturn(
                new ArrayList<Donation>(Arrays.asList(new Donation[]{hat1, hat2, hat3})));
        when(db.getCategoryItems("Clothing", testLocation2)).thenReturn(
                new ArrayList<Donation>(Arrays.asList(new Donation[]{sweater1})));
        when(db.getCategoryItems("Appliances", testLocation1)).thenReturn(
                new ArrayList<Donation>(Arrays.asList(new Donation[]{})));
        when(db.getCategoryItems("Appliances", null)).thenReturn(
                new ArrayList<Donation>(Arrays.asList(new Donation[]{})));

        assertEquals(Arrays.asList(new Donation[]{hat1, hat2}), db.getCategoryItems("Hat", testLocation1));
        assertEquals(Arrays.asList(new Donation[]{hat1, hat2, hat3}), db.getCategoryItems("Hat", null));
        assertEquals(Arrays.asList(new Donation[]{sweater1}), db.getCategoryItems("Clothing", testLocation2));
        assertEquals(Arrays.asList(new Donation[]{}), db.getCategoryItems("Appliances", testLocation1));
        assertEquals(Arrays.asList(new Donation[]{}), db.getCategoryItems("Appliances", null));
    }


}