package com.example.NykaaAppAPI.service;
import com.example.NykaaAppAPI.model.NykaaUser;
import com.example.NykaaAppAPI.model.Role;
import com.example.NykaaAppAPI.repository.RoleRepository;
import com.example.NykaaAppAPI.repository.UserRepository;
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


    public NykaaUser loginAsCustomer(NykaaUser nykaaUser) {
        NykaaUser user = userRepository.findByMailId(nykaaUser.getMailId());
        if (user != null && bCryptPasswordEncoder.matches(nykaaUser.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }

    public List<NykaaUser> getAllUsers() {

        return userRepository.findAll();
    }

}


