package com.gamesys.exclusion.service;

import com.gamesys.exclusion.entity.User;
import com.gamesys.exclusion.modal.ExclusionServiceResponse;
import com.gamesys.exclusion.repository.ExclusionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ExclusionServiceTest {

    @Autowired
    ExclusionService exclusionService;

    @Autowired
    ExclusionRepository exclusionRepository;

    @Test
    public void getAllBlacklistedUser_success_scenario() {
        User user = User.builder()
                .userId("2")
                .userFirstName("Hemavathi")
                .userLastName("Tamil")
                .userDateOfBirth("30/6/95")
                .userCountry("UK")
                .userEmail("hema34@gmail.com")
                .userStatus("B")
                .build();
        List<User> list =  new ArrayList<>();
        list.add(user);
        exclusionRepository.save(user);

        List<User> blacklistedUser =  exclusionService.getAllBlacklistedUser();
        Assert.assertEquals(1,blacklistedUser.size());
    }

    @Test
    public void getAllBlacklistedUser_failure_scenario() {
        exclusionRepository.deleteAll();
        List<User> blacklistedUser =  exclusionService.getAllBlacklistedUser();
        Assert.assertEquals(0,blacklistedUser.size());
    }

}