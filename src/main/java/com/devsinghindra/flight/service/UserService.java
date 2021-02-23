package com.devsinghindra.flight.service;

import com.devsinghindra.flight.dao.UsersRepo;
import com.devsinghindra.flight.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersRepo usersRepo;

    /**
     * create new user
     */
    public void createUser(User user) {
        usersRepo.save(user);
    }

    /**
     * get all users in db
     */
    public List<User> getAllUsers() {
        return usersRepo.findAll();
    }

    /**
     * get user by id i.e aadhaar number
     */
    public User getUser(String aadhaarNo) {
        return usersRepo.findById(aadhaarNo).get();
    }

    /**
     * update user if present if not create user
     * @param user User
     */
    public void updateUser(User user){
        usersRepo.save(user);
    }

    /**
     * delete user by id
     * @param id User ID
     */
    public void deleteUserById(String id){
        usersRepo.deleteById(id);
    }

}
