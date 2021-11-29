package com.predictmatch.userinfo.service;

import com.predictmatch.userinfo.dao.UserInfo;
import com.predictmatch.userinfo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationService {

    @Autowired
    UserInfoRepository userInfoRepository;

    public boolean checkIfUserAlreadyExists(String username) {
        UserInfo storedUser = null;

        try {
            storedUser = userInfoRepository.findByUsername( username );
        } catch (NullPointerException e) {
            e.printStackTrace();
        }







        return storedUser != null;
    }
}
