package com.dannyns.cms.backend.configurations;

import com.dannyns.cms.backend.business.entities.*;
import com.dannyns.cms.backend.business.repositories.LanguageRepository;
import com.dannyns.cms.backend.business.repositories.PageRepository;
import com.dannyns.cms.backend.business.repositories.RoleRepository;
import com.dannyns.cms.backend.business.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import javax.transaction.Transactional;
import java.util.*;

@Configuration
@Transactional
public class DbInitializerConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();

    @Autowired
    Set<CrudRepository> repositories;

    Collection<? extends BaseEntity> pages = Arrays.asList(
            Page.builder().uri("about").build(),
            Page.builder().uri("contact").build(),
            Page.builder().uri("portfolio").build()
    );

    Collection<? extends BaseEntity> languages = Arrays.asList(
            Language.builder().code("PL").name("Polski").build(),
            Language.builder().code("EN").name("Enlgish").build(),
            Language.builder().code("DE").name("Deutsch").build()
    );

    Collection<? extends BaseEntity> users = Arrays.asList(
            User.builder().username("admin").password(passwordEncoder.encodePassword("password", null)).build(),
            User.builder().username("user").password(passwordEncoder.encodePassword("password", null)).build()
    );

    Collection<? extends BaseEntity> roles = Arrays.asList(
            Role.builder().role("ADMIN").build(),
            Role.builder().role("USER").build()
    );

    Collection<Pair<Class<CrudRepository>, Collection>> mappings = new ArrayList(
            Arrays.asList(
                    Pair.of(LanguageRepository.class, languages),
                    Pair.of(PageRepository.class, pages),
                    Pair.of(RoleRepository.class, roles),
                    Pair.of(UserRepository.class, users)
            )
    );

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        mappings.forEach(pair -> getCrudRepository(pair.getFirst()).ifPresent(
                crudRepository -> crudRepository.save(pair.getSecond())
        ));

        addUserRelations();
    }

    private Optional<? extends CrudRepository> getCrudRepository(Class<? extends CrudRepository> clazz) {
        return repositories.stream()
                .filter(clazz::isInstance)
                .findFirst();
    }

    private void addUserRelations() {
        RoleRepository roleRepository = (RoleRepository) getCrudRepository(RoleRepository.class).get();
        UserRepository userRepository = (UserRepository) getCrudRepository(UserRepository.class).get();

        Role adminRole = roleRepository.findByRole("ADMIN").get();
        Role userRole = roleRepository.findByRole("USER").get();

        User adminUser = userRepository.findByUsername("admin").get();
        User userUser = userRepository.findByUsername("user").get();

        adminUser.getRoles().addAll(Arrays.asList(adminRole, userRole));
        userUser.getRoles().add(userRole);

        userRepository.save(adminUser);
        userRepository.save(userUser);
    }
}
