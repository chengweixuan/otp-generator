package com.govetech.OTP.otp;

import com.govetech.OTP.repository.OTP;
import com.govetech.OTP.repository.otpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class otpService {

    @Autowired
    private otpRepository otpRepository;

    public List<OTP> getOTPs() {
        return otpRepository.findAll();
    }

    public String getOTP(String user) {
        boolean found = false;
        Random rand = new Random();
        OTP newOtp = new OTP();

        while (!found) {
            long otp = rand.nextInt(999999);
            String otpString = String.format("%06d", otp);

            if (!otpRepository.existsById(otp)) {
                found = true;

                newOtp.setId(otp);
                newOtp.setOtp(otpString);
                newOtp.setTimestamp(new Timestamp(System.currentTimeMillis()));
                newOtp.setUser(user);

                otpRepository.save(newOtp);
            }
        }

        return newOtp.getOtp();
    }

    public boolean verifyOTP(String otpStr, String user) {
        long otpId = Long.parseLong(otpStr);

        if (otpRepository.existsById(otpId)) {
            OTP foundOtp = otpRepository.getById(otpId);
            return Objects.equals(foundOtp.getUser(), user);
        }

        return false;
    }
}
