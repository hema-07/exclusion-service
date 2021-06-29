package com.gamesys.exclusion.controller;

import com.gamesys.exclusion.entity.User;
import com.gamesys.exclusion.modal.ErrorResponse;
import com.gamesys.exclusion.modal.ExclusionServiceResponse;
import com.gamesys.exclusion.service.ExclusionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gamesys.exclusion.util.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * This component is a interface for Registration Service which needs a user to be validated against blacklisted users.
 */
@RestController
public class ExclusionController {


    @Autowired
    private ExclusionService exclusionService;

    private static final Logger logger = LogManager.getLogger(ExclusionController.class);

    @RequestMapping(value = "/user", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> saveExcludedUsersList() {

        exclusionService.saveBlackListUsers();

        return new ResponseEntity<>(HttpStatus.OK);
    }

        /**
         *This method validates the request and find it from db. if it is new valid user detail, it will store it in DB.
         * it has 6 params : user id, first / last name, date of birth, email, country
         * @return blacklisted users return as response, invalid request will throw an error response.
         */
    @RequestMapping(value = "/user", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> excludedUsersList()  {

        List<User> blacklistedUser = exclusionService.getAllBlacklistedUser();

        if (blacklistedUser.size() != 0) {
            ExclusionServiceResponse allBlacklistedUser = ExclusionServiceResponse.builder()
                    .users(blacklistedUser)
                    .build();
            return new ResponseEntity<>(allBlacklistedUser, HttpStatus.OK);
        } else {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .errorCode(noUserFromDB)
                    .errorDescription(noUserFromDBDetails)
                    .build();

            return new ResponseEntity<>( errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
