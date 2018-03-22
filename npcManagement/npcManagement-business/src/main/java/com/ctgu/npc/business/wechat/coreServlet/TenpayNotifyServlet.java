package com.ctgu.npc.business.wechat.coreServlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 财付通后台支付通知处理
 */
public class TenpayNotifyServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("http://219.139.128.22:11111/Hos-Pay/tenpayNotify");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
