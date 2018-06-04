package com.isc.application.startup.service.impl;

import com.isc.application.startup.dal.emailMyISAM.EmailMyISAMEntity;
import com.isc.application.startup.dal.emailMyISAM.EmailMyISAMMapper;
import com.isc.application.startup.data.request.PagingRequest;
import com.isc.application.startup.dto.email.EmailDto;
import com.isc.application.startup.service.EmailMyISAMService;
import com.isc.application.startup.util.LogUtil;
import org.springframework.stereotype.Service;
import com.isc.application.startup.data.result.PagingResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class EmailMyISAMServiceImpl implements EmailMyISAMService {


    @Resource
    EmailMyISAMMapper emailMyISAMMapper;

    @Override
    public PagingResult<EmailDto> selectByPaging(PagingRequest request) {

        PagingResult<EmailDto> result = new PagingResult<>();
        List<EmailDto> list = emailMyISAMMapper.selectByPaging(request);
        Integer total = emailMyISAMMapper.count();
        result.setPage(request.getPage());
        result.setPage_size(request.getPageSize());
        result.setTotal_records(total);
        result.setList(list);
        result.calc();
        return result;

    }

    @Override
    public void generateEmails(int count) {
        EmailMyISAMEntity record = new EmailMyISAMEntity();
        String email;



        long startTimes,span;
        while (count-- > 0) {

             startTimes = System.currentTimeMillis();
            record.setEmail_address(randomEmail());
            emailMyISAMMapper.insert(record);

             span = System.currentTimeMillis()-startTimes;
            LogUtil.log("escape:%s millisSeconds", span);
        }


    }


    private String randomEmail(){
        return String.format("%s@email.com",UUID.randomUUID().toString());
    }


}
