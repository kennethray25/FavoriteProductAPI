package com.safestreets.scheduled;

import com.safestreets.model.Product;
import com.safestreets.model.User;
import com.safestreets.services.FavoriteService;
import com.safestreets.services.UserService;
import com.safestreets.services.ProductService;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

/**
 * OnStart
 * Created By: dylanthompson
 * Created On: 12/28/23
 * Note: This class is executed on application start.
 * For the sake of the test any models that need to be pre-populated should be populated here.
 **/
@Slf4j
@Singleton
@Requires(notEnv = Environment.TEST) // Don't load data in tests.
public class OnStart implements ApplicationEventListener<StartupEvent> {

  private final UserService userService;
  private final ProductService productService;
  private final FavoriteService favoriteService;

  public OnStart(UserService userService, ProductService productService, FavoriteService favoriteService) {
    this.userService = userService;
      this.productService = productService;
      this.favoriteService = favoriteService;
  }

  @Async
  @Override
  public void onApplicationEvent(StartupEvent event) {
    // create test user, create two test products
    log.info("Executing startup job - creating a user.");
    User user = userService.createNewUser("Test User", "test@safestreets.com", "ADMIN");
    log.info("Created user (Id:" + user.getId() + ")");
    Product product = productService.createNewProduct("Game Genie", "It lets you enter codes for over 290 games in the codebook to create the special effects you want");
    log.info("Created test product (Id:" + product.getId() + ")");
    Product product2 = productService.createNewProduct("Air Guitar", "All the imaginary benefits of a real guitar without the physical weight burden");
    log.info("Created test product2 (Id:" + product2.getId() + ")");

    favoriteService.createNewUserFavorite(user.getId(),product.getId());
    favoriteService.createNewUserFavorite(user.getId(),product2.getId());

    // get userfavorties should be two, then delete user favorites, then check and there are none.
    //    var testFavorites = favoriteService.getFavoritesByUserId(user.getId());
    //    favoriteService.deleteFavoriteByUserId(user.getId());
    //    var nowTestFavorites = favoriteService.getFavoritesByUserId(user.getId());
  }
}
