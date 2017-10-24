package com.login.controller;


import com.login.model.User;
import com.login.rsa.RSAmodel;
import com.login.rsa.ec.RsaEc;
import com.login.util.JsonUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(String k, PrintWriter out, HttpSession session){
        RSAmodel rsa = (RSAmodel) session.getAttribute("rsa");
        String res = "";
        if(rsa != null){
            rsa.setCiphertext(k);
            res = new RsaEc().decode(rsa);
            session.removeAttribute("res");
        }
//        System.out.println(res);
        JSONObject user = JSONObject.fromObject(res);
        User u = new User();
        u.setPwd(user.getString("pwd"));
        u.setUserName(user.getString("userName"));
        System.out.println("user = " + JsonUtil.object2json(u));
    }



}
