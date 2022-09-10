package com.example.NykaaAppAPI.dataloader;

import com.example.NykaaAppAPI.model.NykaaUser;
import com.example.NykaaAppAPI.model.Role;
import com.example.NykaaAppAPI.repository.RoleRepository;
import com.example.NykaaAppAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

//         Create user roles
        Role userRole = createRoleIfNotFound(Role.USER);
        Role adminRole = createRoleIfNotFound(Role.ADMIN);

        // Create users
        createUserIfNotFound("admin", adminRole);


        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    private NykaaUser createUserIfNotFound(final String mailId, final Role role) {
        NykaaUser user = userRepository.findByMailId(mailId);
        if (user == null) {
            user = new NykaaUser(mailId, bCryptPasswordEncoder.encode("admin"));
            user.setRoles(Set.of(role));
            user.setName("admin");
            user.setPhoneNumber(8765432345678L);
            user = userRepository.save(user);
        }
        return user;
    }
}
