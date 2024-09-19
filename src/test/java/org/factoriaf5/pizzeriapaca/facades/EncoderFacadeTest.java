package org.factoriaf5.pizzeriapaca.facades;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import org.factoriaf5.pizzeriapaca.encryptations.Base64Encoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

public class EncoderFacadeTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Base64Encoder base64Encoder;

    @InjectMocks
    private EncoderFacade facade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEncodeBcrypt() {
        String type = "bcrypt";
        String data = "password";

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(data);

        when(passwordEncoder.encode(data)).thenReturn(encoded);
        String passwordEncoded = facade.encode(type, data);

        assertThat(encoder.matches(data, passwordEncoded), is(true));
    }

    @Test
    void testBase64Encode() {
        String type = "base64";
        String data = "user";

        String expectedEncoded = Base64.getEncoder().encodeToString(data.getBytes());
        when(base64Encoder.encode(data)).thenReturn(expectedEncoded);

        String user = facade.encode(type, data);

        assertThat(user, is(expectedEncoded));
    }

    @Test
    void testDecodeBase64() {
        String type = "base64";
        String encodedData = "dXNlcg==";
        String decodedData = "user";

        when(base64Encoder.decode(encodedData)).thenReturn(decodedData);

        String result = facade.decode(type, encodedData);

        assertThat(result, is(decodedData));
    }

    @Test
    void testDecodeInvalidType() {
        String type = "invalidType";
        String encodedData = "dXNlcg==";
        String result = facade.decode(type, encodedData);

        assertThat(result, is(""));
    }
}
