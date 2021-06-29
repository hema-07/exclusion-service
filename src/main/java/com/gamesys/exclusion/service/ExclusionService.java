package com.gamesys.exclusion.service;

import com.gamesys.exclusion.entity.User;
import com.gamesys.exclusion.modal.ExclusionServiceResponse;
import com.gamesys.exclusion.repository.ExclusionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExclusionService {

    @Autowired
    private ExclusionRepository userRepository;

    public List<User> getAllBlacklistedUser() {

        List<User> blacklist = userRepository.findByStatus();
        if (!blacklist.isEmpty()) {
            return blacklist;
        } else {
            return new ArrayList<>();
        }
    }

    public void saveBlackListUsers() {
        User user1 = User.builder()
                .userId("1")
                .userFirstName("Mark")
                .userLastName("Josh")
                .userDateOfBirth("30/3/95")
                .userCountry("UK")
                .userEmail("mark@gmail.com")
                .userStatus("B")
                .build();
        User user2 = User.builder()
                .userId("2")
                .userFirstName("Hema")
                .userLastName("Tamil")
                .userDateOfBirth("30/2/95")
                .userCountry("UK")
                .userEmail("hema64@gmail.com")
                .userStatus("B")
                .build();
        userRepository.save(user1);
        userRepository.save(user2);
    }

}


