package springboot.service.impl;

import org.springframework.stereotype.Service;
import springboot.dal.emailMyISAM.EmailMyISAMEntity;
import springboot.dto.email.EmailDto;
import springboot.dal.emailMyISAM.EmailMyISAMMapper;
import springboot.data.request.PagingRequest;
import springboot.data.result.PagingResult;
import springboot.service.EmailMyISAMService;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static springboot.util.LogUtil.log;

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
            log("escape:%s millisSeconds",span);
        }


    }


    private String randomEmail(){
        return String.format("%s@email.com",UUID.randomUUID().toString());
    }


}
