package com.safestreets.controller;

import com.safestreets.model.Favorite;
import com.safestreets.services.FavoriteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @Post("/{userId}/{productId}")
    public Favorite createFavorite(@PathVariable long userId, @PathVariable long productId) {
        return favoriteService.createNewUserFavorite(userId,productId);
    }

    @Get(uri = "/{userId}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<?> getFavorite(@PathVariable long userId) {
        var favorites = favoriteService.getFavoritesByUserId(userId);

        if (favorites == null)
        {
            return HttpResponse.notFound();
        }
        else {
            return HttpResponse.ok(favorites);
        }
    }

    @Delete("/{userId}/{productId}")
    @Status(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String userId, @PathVariable String productId)
    {
        favoriteService.deleteFavoriteByUserIdAndByProductId(userId,productId);
    }
}