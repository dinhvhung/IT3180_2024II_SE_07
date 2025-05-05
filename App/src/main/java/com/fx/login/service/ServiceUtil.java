package com.fx.login.service;

import com.fx.login.repo.UserRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Data
public class ServiceUtil {
    @Autowired
    UserRepo userRepo;
}
