package com.example.NykaaAppAPI.service;
import com.example.NykaaAppAPI.exception.ResourceNotFoundException;
import com.example.NykaaAppAPI.model.NykaaUser;
import com.example.NykaaAppAPI.model.Role;
import com.example.NykaaAppAPI.repository.RoleRepository;
import com.example.NykaaAppAPI.repository.UserRepository;
import com.example.NykaaAppAPI.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public NykaaUser registerAsCustomer(NykaaUser nykaaUser) {
//        NykaaUser registerUser = userRepository.findByMailId(nykaaUser.getMailId());
        Role role = roleRepository.findByName(Role.USER);
        nykaaUser.setRoles(Set.of(role));
        nykaaUser.setPassword(bCryptPasswordEncoder.encode(nykaaUser.getPassword()));
        return userRepository.save(nykaaUser);
    }


    public AuthResponse loginAsCustomer(NykaaUser nykaaUser) {
        NykaaUser user = userRepository.findByMailId(nykaaUser.getMailId());
        if (user != null && bCryptPasswordEncoder.matches(nykaaUser.getPassword(), user.getPassword())) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setUserName(user.getName());
            authResponse.setRole(user.getRoles().iterator().next().getName());
            authResponse.setId(user.getUserId());
            authResponse.setMailId(user.getMailId());
            return authResponse;
        }
        throw new ResourceNotFoundException("USer does not exists ");
    }

    public List<NykaaUser> getAllUsers() {

        return userRepository.findAll();
    }

}


