package com.example.breadscrumbs.donation_tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import Model.SQLiteDatabaseHandler;
import Model.User;

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

        db.addUser(john);
        db.addUser(alice);
        db.addUser(bob);
        db.addUser(carol);

        testList.add(john);
        testList.add(alice);
        testList.add(bob);
        testList.add(carol);

        when(db.allUsers()).thenReturn(testList);

        assertEquals(db.allUsers(), testList);
    }

    @Test
    public void testNoUsersInDatabase() {
        SQLiteDatabaseHandler db = mock(SQLiteDatabaseHandler.class);
        when(db.allUsers()).thenReturn(null);

        assertNull(db.allUsers());
    }


}