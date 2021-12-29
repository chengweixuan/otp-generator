package com.govetech.OTP.otp;

import com.govetech.OTP.repository.OTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class otpController {

    @Autowired
    private otpService otpService;

    @GetMapping("/getAll")
    public List<OTP> getOTPs() {
        return otpService.getOTPs();
    }

    @PostMapping("/getOTP")
    public String getOTP(@RequestParam String user) {
        return otpService.getOTP(user);
    }

    @PostMapping("/verifyOTP")
    public boolean verifyOTP(@RequestParam String otp, @RequestParam String user) {
        return otpService.verifyOTP(otp, user);
    }
}
