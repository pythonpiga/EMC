package com.broad.emc.module.service.impl;


import com.broad.emc.module.dao.HtLogDao;
import com.broad.emc.module.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private HtLogDao htLogDao;

//    public int insertLog(HtLog log) {
//       return htLogDao.addLogRecord(log);
//    }
}
