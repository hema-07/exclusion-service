package com.gamesys.exclusion.modal;

import com.gamesys.exclusion.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ExclusionServiceResponse {

    private List<User> users;

}
