package com.predictmatch.userinfo.service;

import com.predictmatch.userinfo.proxy.TeamProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamProxy teamProxy;


}
