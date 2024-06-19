package com.example.alltickets.models;

import com.example.alltickets.models.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
    }

    @Test
    public void testUserCreation() {
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPhoneNumber("123456789");
        user.setName("Test User");
        user.setActive(true);
        Image avatar = new Image();
        avatar.setId(1L);
        user.setAvatar(avatar);
        user.setPassword("password");

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);

        LocalDateTime now = LocalDateTime.now();
        user.init();

        assertEquals(1L, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("123456789", user.getPhoneNumber());
        assertEquals("Test User", user.getName());
        assertTrue(user.isActive());
        assertEquals(avatar, user.getAvatar());
        assertEquals("password", user.getPassword());
        assertEquals(roles, user.getRoles());
        assertNotNull(user.getDateOfCreated());
        assertTrue(user.getDateOfCreated().isBefore(now.plusSeconds(1)));
        assertTrue(user.getDateOfCreated().isAfter(now.minusSeconds(1)));
    }

    @Test
    public void testGetAuthorities() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        roles.add(Role.ROLE_ADMIN);
        user.setRoles(roles);

        assertEquals(2, user.getAuthorities().size());
        assertTrue(user.getAuthorities().contains(Role.ROLE_USER));
        assertTrue(user.getAuthorities().contains(Role.ROLE_ADMIN));
    }

    @Test
    public void testGetUsername() {
        user.setEmail("test@example.com");

        assertEquals("test@example.com", user.getUsername());
    }

    @Test
    public void testAccountNonExpired() {
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void testAccountNonLocked() {
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void testCredentialsNonExpired() {
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        user.setActive(true);
        assertTrue(user.isEnabled());

        user.setActive(false);
        assertFalse(user.isEnabled());
    }
}
