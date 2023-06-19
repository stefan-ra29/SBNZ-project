package sbnz.integracija.example.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserCreateDTO {
    private List<Integer> genres;

    private String name;
    private String username;
    private String password;

}
