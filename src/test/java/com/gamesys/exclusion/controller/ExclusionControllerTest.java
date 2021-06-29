package com.gamesys.exclusion.controller;

import com.gamesys.exclusion.entity.User;
import com.gamesys.exclusion.modal.ErrorResponse;
import com.gamesys.exclusion.modal.ExclusionServiceResponse;
import com.gamesys.exclusion.repository.ExclusionRepository;
import com.gamesys.exclusion.service.ExclusionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.gamesys.exclusion.util.Constants.noUserFromDB;
import static com.gamesys.exclusion.util.Constants.noUserFromDBDetails;

@SpringBootTest
public class ExclusionControllerTest {


    @Autowired
    ExclusionController exclusionController;

    @Mock
    ExclusionService exclusionService;

    @Autowired
    ExclusionRepository exclusionRepository;

    @Test
    public void blacklistUsers_success_scenario() {

        User user = User.builder()
                .userId("2")
                .userFirstName("Hemavathi")
                .userLastName("Tamil")
                .userDateOfBirth("30/6/95")
                .userCountry("UK")
                .userEmail("hema34@gmail.com")
                .userStatus("B")
                .build();
        List<User> list = new ArrayList<>();
        list.add(user);
        exclusionRepository.save(user);
        Mockito.when(exclusionService.getAllBlacklistedUser()).thenReturn(list);
        ExclusionServiceResponse expectedBlacklistedUser = ExclusionServiceResponse.builder()
                .users(list)
                .build();

        ResponseEntity<?> responseEntity = exclusionController.excludedUsersList();
        ExclusionServiceResponse actualExclusionServiceResponse = (ExclusionServiceResponse) responseEntity.getBody();
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(expectedBlacklistedUser.getUsers().size(), actualExclusionServiceResponse.getUsers().size());
    }

    @Test
    public void blacklistUsers_failure_scenario() {
        exclusionRepository.deleteAll();
        Mockito.when(exclusionService.getAllBlacklistedUser()).thenReturn(null);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(noUserFromDB)
                .errorDescription(noUserFromDBDetails)
                .build();

        ResponseEntity<?> responseEntity = exclusionController.excludedUsersList();
        ErrorResponse response = (ErrorResponse) responseEntity.getBody();

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Assert.assertEquals(errorResponse.getErrorCode(), response.getErrorCode());
        Assert.assertEquals(errorResponse.getErrorDescription(), response.getErrorDescription());
    }
}