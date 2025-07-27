package com.hotelbooking.springBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDataWithImageDto {
    private UserDto userDto;
    private UserImageDto userImageDto;

}
