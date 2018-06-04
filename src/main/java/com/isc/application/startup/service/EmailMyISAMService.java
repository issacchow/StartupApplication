package com.isc.application.startup.service;

import com.isc.application.startup.data.request.PagingRequest;
import com.isc.application.startup.dto.email.EmailDto;
import com.isc.application.startup.data.result.PagingResult;

public interface EmailMyISAMService {

    PagingResult<EmailDto> selectByPaging(PagingRequest request);

    void generateEmails(int count);
}
