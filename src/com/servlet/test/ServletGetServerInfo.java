package com.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletGetServerInfo extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=utf-8");
    PrintWriter out = response.getWriter();
    out.println("Server Infomation");
    printValues(request, out);

  }

  protected void printValues(HttpServletRequest request, PrintWriter out) throws IOException{
    out.println("服务器" + request.getServerName());
    out.println("服务器端口号" + request.getServerPort());
    out.println("协议名" + request.getScheme());
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }
}
