package main.java.com.example.spring_boot_user_demo.service;

import com.example.spring_boot_user_demo.repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private FakeRepoInterface fakeRepo;

    @BeforeEach
    void setUp() {
        // Reset mock interactions before each test
        reset(fakeRepo);
    }

    @Test
    void testAddUser() {
        String name = "John";
        String surname = "Doe";
        when(fakeRepo.insertUser(anyLong(), eq(name), eq(surname))).thenReturn(name);

        String result = userService.addUser(name, surname);

        assertEquals(name, result);
        verify(fakeRepo, times(1)).insertUser(anyLong(), eq(name), eq(surname));
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

    @Test
    void testRemoveUser() {
        long id = 1L;
        String name = "John";
        when(fakeRepo.deleteUser(id)).thenReturn(name);

        String result = userService.removeUser(id);

        assertEquals(name, result);
        verify(fakeRepo, times(1)).deleteUser(id);
    }

    @Test
    void testRemoveUserNotFound() {
        long id = 999L;
        when(fakeRepo.deleteUser(id)).thenThrow(new IllegalArgumentException("User with ID " + id + " not found"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.removeUser(id);
        });

        assertEquals("User with ID " + id + " not found", exception.getMessage());
        verify(fakeRepo, times(1)).deleteUser(id);
    }
}
