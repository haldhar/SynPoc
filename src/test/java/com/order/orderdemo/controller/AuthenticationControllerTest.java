package com.order.orderdemo.controller;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.order.orderdemo.controller.model.AuthenticationRequest;
import com.order.orderdemo.controller.model.AuthenticationResponse;
import com.order.orderdemo.service.AuthenticationService;
import com.order.orderdemo.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    public void testAuthenticate() throws Exception {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setUserName("admin");
        authenticationResponse.setGender("M");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUserName("admin");
        when(authenticationService.authenticate(authenticationRequest)).thenReturn(authenticationResponse);
        mockMvc.perform(post("/authenticate", authenticationRequest).contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.asJsonString(authenticationRequest))).andExpect(status().isOk());
		verify(authenticationService,times(1)).authenticate(refEq(authenticationRequest,""));
		verifyNoMoreInteractions(authenticationService);
    }
}