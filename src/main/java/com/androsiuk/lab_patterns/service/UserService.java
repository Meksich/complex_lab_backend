package com.androsiuk.lab_patterns.service;

import com.androsiuk.lab_patterns.DTO.UserDTO;
import com.androsiuk.lab_patterns.entity.User;
import com.androsiuk.lab_patterns.repository.UserCatalogRepository;
import com.androsiuk.lab_patterns.repository.UserCategoryRepository;
import com.androsiuk.lab_patterns.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService extends com.androsiuk.lab_patterns.service.Service<User> {
    private UserRepository userRepository;
    private UserCatalogRepository userCatalogRepository;
    private UserCategoryRepository userCategoryRepository;

    @Override
    public JpaRepository<User, Integer> getRepository() {
        return userRepository;
    }

    public User transformDTO(UserDTO userDTO){
        User freshUser = new User();
        freshUser.setBalanceUah(userDTO.getBalanceUah());
        freshUser.setUserCatalog(userCatalogRepository.getOne(userDTO.getUserCatalogId()));
        freshUser.setUserCategory(userCategoryRepository.getOne(userDTO.getUserCategoryId()));
        freshUser.setEmail(userDTO.getEmail());
        freshUser.setExpirationDate(userDTO.getExpirationDate());
        freshUser.setFullName(userDTO.getFullName());
        freshUser.setPassword(userDTO.getPassword());
        create(freshUser);
        return freshUser;
    }
}
