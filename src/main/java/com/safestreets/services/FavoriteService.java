package com.safestreets.services;

import com.safestreets.model.Favorite;
import com.safestreets.model.User;
import com.safestreets.model.repository.FavoriteRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Singleton
@Slf4j
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final ProductService productService;

    public FavoriteService(FavoriteRepository favoriteRepository, UserService userService, ProductService productService) {
        this.favoriteRepository = favoriteRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public Favorite createNewUserFavorite(long userId, long productId) {
        Favorite favorite = new Favorite();
        var user = userService.getUser(userId);
        var product = productService.getProduct(productId);

        if (user == null || product == null) {
            return null;
        }

        favorite.setUser(user);
        favorite.setProduct(product);

        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getFavoritesByUserId(Long userId) {
//        User favoriteUser = userService.getUser(userId);
//        //userFavorite.setUser();
//        favoriteRepository.deleteFavoritesByUserId(String.valueOf(favoriteUser.getId()));

        return favoriteRepository.findByUserId(String.valueOf(userId));
    }

    public void deleteFavoriteByUserId(Long userId) {
        Favorite userFavorite = new Favorite();
        User favoriteUser = userService.getUser(userId);
        //userFavorite.setUser();
      favoriteRepository.deleteFavoritesByUserId(String.valueOf(favoriteUser.getId()));

      //  var userFavorites = favoriteRepository.findByUserId(String.valueOf(favoriteUser.getId()));
    }


    public void deleteFavoriteByUserIdAndByProductId(String userId, String productId) {
        favoriteRepository.deleteFavoritesByUserIdAndByProductId(userId, productId);
    }
}
