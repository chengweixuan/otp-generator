package com.govetech.OTP;

import com.govetech.OTP.otp.otpService;
import com.govetech.OTP.repository.OTP;
import com.govetech.OTP.repository.otpRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;



@RunWith(MockitoJUnitRunner.class)
public class getOtpTest {

    @Mock
    otpRepository otpRepository;

    @InjectMocks
    otpService otpService = new otpService();

    @Test
    public void otpLengthIsSix() {
        String otp = otpService.getOTP("user");
        assertEquals(otp.length(), 6);
    }

    @Test
    public void checkCreatedOtpIsValid() {
        when(otpService.getOTP(any(String.class))).thenReturn("333333");
        OTP testOTP = new OTP();
        testOTP.setUser("user");

        when(otpRepository.existsById(any(Long.class))).thenReturn(true);
        when(otpRepository.getById(any(Long.class))).thenReturn(testOTP);


        boolean isValid = otpService.verifyOTP("333333", "user");
        assertTrue(isValid);
    }


}
