package com.safestreets.controller;

import com.safestreets.dto.UserDTO;
import com.safestreets.model.User;
import com.safestreets.services.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Post
    public User createProduct(@Body UserDTO user) {
        return userService.createNewUser(user.getName(),user.getEmail(),user.getEmail());
    }

    @Put("/{userId}")
    public HttpResponse<?> updateUser(@Body UserDTO userDTO, @PathVariable String userId) {
        long id = Long.parseLong(userId);
        if (!userService.userExists(id)) {
            return HttpResponse.notFound();
        }

        User user = userService.getUser(id);
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        User updatedUser = userService.updateUser(user, user.getId());
        return HttpResponse.ok(updatedUser);
    }

    @Get(uri = "/{userId}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> getProduct(@PathVariable String userId) {
        var user = userService.getUser(Long.parseLong(userId));
        if (user == null)
        {
            return HttpResponse.notFound();
        }
        else {
            return HttpResponse.ok(user);
        }
    }

    @Delete("/{userId}")
    @Status(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String userId)
    {
        long id = Long.parseLong(userId);
        userService.deleteUser(id);
    }
}