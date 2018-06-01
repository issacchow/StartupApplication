package springboot.service;

import springboot.dto.email.EmailDto;
import springboot.data.request.PagingRequest;
import springboot.data.result.PagingResult;

public interface EmailMyISAMService {

    PagingResult<EmailDto> selectByPaging(PagingRequest request);

    void generateEmails(int count);
}
