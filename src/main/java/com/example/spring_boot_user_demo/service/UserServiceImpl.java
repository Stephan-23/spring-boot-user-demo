package com.example.spring_boot_user_demo.service;

import com.example.demo.repo.FakeRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final FakeRepoInterface fakeRepo;
    private static long idCounter = 1; // Simulate auto-increment ID

    @Autowired
    public UserServiceImpl(FakeRepoInterface fakeRepo) {
        this.fakeRepo = fakeRepo;
    }

    @Override
    public String addUser(String name, String surname) {
        String addedName = fakeRepo.insertUser(idCounter++, name, surname);
        System.out.println(addedName + " added");
        return addedName;
    }

    @Override
    public String getUser(long id) {
        String name = fakeRepo.findUserById(id);
        System.out.println("hello " + name);
        return name;
    }

    @Override
    public String removeUser(long id) {
        String name = fakeRepo.deleteUser(id);
        System.out.println(name + " removed");
        return name;
    }
}
