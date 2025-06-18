package com.beer.beer_forecast.account.repository;

import com.beer.beer_forecast.account.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface LoginRepository extends JpaRepository<Login,Integer>{
    Optional<Login> findByEmail(String email);
}