package com.predictmatch.userinfo.repository;

import com.predictmatch.userinfo.dao.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
