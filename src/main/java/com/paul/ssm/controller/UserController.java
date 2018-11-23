package com.paul.ssm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.paul.ssm.domain.DataResult;
import com.paul.ssm.domain.User;
import com.paul.ssm.service.IUserService;

/**
 * 用户控制器类
 * @author huangyun
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private IUserService userService;
    
    @RequestMapping(value="/getUserName", method=RequestMethod.GET)
    public String getUserName(@RequestParam("uid") int uid, HttpServletRequest request, Model model){
    	logger.info("accept request, uid:{}", uid);
        User user = userService.getUserById((long)uid);
        if(user != null){
            request.setAttribute("name", user.getUserName());
            model.addAttribute("name", user.getUserName());
            return "showName";
        }
        
        request.setAttribute("error", "没有找到该用户！");
        return "error";
    }
    
    @ResponseBody
    @RequestMapping(value="/getUserById", method=RequestMethod.POST)
    public DataResult<User> getUserById(HttpServletRequest request, @RequestBody User user) {
    	DataResult<User> result = new DataResult<>();
    	if (user == null || user.getId() == null) {
    		logger.info("uid is null");
    		result.setCode(1000);
    		result.setMsg("用户id为空");
    		return result;
    	}
    	
    	User targetUser = userService.getUserById(user.getId());
    	if (targetUser != null) {
    		result.setData(targetUser);
    	}
    	
    	return result;
    }
    //文件上传
    @RequestMapping(value="/upload")
    public String showUploadPage(){
        return "file";
    }
    
    @RequestMapping(value="/doUpload",method=RequestMethod.POST)
    public String doUploadFile(@RequestParam("file")MultipartFile file) throws IOException{
        if (file == null || file.isEmpty()) {
        	logger.info("file is empty");
        	return "uploadFailed";
        }
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File("C:/Users/huangyun/Desktop", fileName));
        return "uploadSuccessful";
    }
}
