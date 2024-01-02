package com.safestreets.model.repository;

import com.safestreets.model.Favorite;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.annotation.Query;
import java.util.List;

@JdbcRepository(dialect = Dialect.H2)
public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

    @Query("SELECT * FROM Favorite WHERE user_ID = :userId")
    List<Favorite> findByUserId(String userId);

    @Query("DELETE FROM Favorite WHERE user_ID = :userId")
    void deleteFavoritesByUserId(String userId);

    @Query("DELETE FROM Favorite WHERE user_ID = :userId AND product_ID = :productId")
    void deleteFavoritesByUserIdAndByProductId(String userId, String productId);
}
