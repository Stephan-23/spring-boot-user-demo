package com.example.spring_boot_user_demo.repo;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeRepo implements FakeRepoInterface {
    private final List<User> users = new ArrayList<>();

    @Override
    public String insertUser(long id, String name, String surname) {
        // Check for duplicate ID
        if (users.stream().anyMatch(user -> user.getId() == id)) {
            throw new IllegalArgumentException("User with ID " + id + " already exists");
        }
        User user = new User(id, name, surname);
        users.add(user);
        return name;
    }

    @Test
    void testGetUser() {
        long id = 1L;
        String name = "John";
        when(fakeRepo.findUserById(id)).thenReturn(name);

        String result = userService.getUser(id);

        assertEquals(name, result);
        verify(fakeRepo, times(1)).findUserById(id);
    }

    @Test
    void testGetUserNotFound() {
        long id = 999L;
        when(fakeRepo.findUserById(id)).thenThrow(new IllegalArgumentException("User with ID " + id + " not found"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getUser(id);
        });

        assertEquals("User with ID " + id + " not found", exception.getMessage());
        verify(fakeRepo, times(1)).findUserById(id);
    }


}