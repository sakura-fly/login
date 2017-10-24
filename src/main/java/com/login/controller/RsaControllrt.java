package com.login.controller;


import com.login.rsa.RSAmodel;
import com.login.rsa.ec.RsaEc;
import com.login.rsa.th.Timeout;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
@RequestMapping(value = "/rsa")
public class RsaControllrt {

    @RequestMapping(value = "/getk", method = RequestMethod.POST)
    public void getK(HttpSession session, PrintWriter out){
        RsaEc re = new RsaEc();
        RSAmodel rsa = re.init();
        session.setAttribute("rsa",rsa);
        new Timeout().sessionTimeout(session);
        out.print(rsa.getPukStr());
    }


}
