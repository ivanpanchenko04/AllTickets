package com.example.alltickets.configurations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("username", "user")
                        .param("password", "password")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    @WithMockUser(username = "user", password = "password", roles = "USER")
    public void testProtectedEndpoints() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/protected"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCsrfProtection() throws Exception {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .param("username", "user")
                        .param("password", "password")
                        .with(SecurityMockMvcRequestPostProcessors.csrf().useInvalidToken())
                        .sessionAttr(HttpSessionCsrfTokenRepository.class.getName(), csrfTokenRepository))
                .andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(result -> {
                    if (!(result.getResolvedException() instanceof InvalidCsrfTokenException)) {
                        throw new AssertionError("Expected InvalidCsrfTokenException");
                    }
                });
    }
}

