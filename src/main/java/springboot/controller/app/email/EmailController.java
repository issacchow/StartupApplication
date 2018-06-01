package springboot.controller.app.email;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springboot.data.request.PagingRequest;
import springboot.service.EmailMyISAMService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/app/email")
public class EmailController {

    @Resource
    EmailMyISAMService emailMyISAMService;

    @RequestMapping(value = {"/","/list"})
    public Object list(
         @RequestParam(required = true) Integer page
    ){
        PagingRequest request = new PagingRequest();
        request.setPage(page);
        return emailMyISAMService.selectByPaging(request);
    }

}
