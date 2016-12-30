<%@ page import="org.pyn.dao.ZoneDao" %>
<%@ page import="com.sun.scenario.effect.impl.state.LinearConvolveKernel" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="org.pyn.bean.Zone" %>
<%--
  Created by IntelliJ IDEA.
  User: pyn
  Date: 2016/12/15
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="robots" content="noindex">

    <title>friendsCircle</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link href="friendsCircle.css" rel="stylesheet">
    <link href="css/messenger.css" rel="stylesheet">
    <link href="css/messenger-theme-future.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/messenger.min.js"></script>
</head>
<body>
<div class="chat_window">

    <div class="top_menu">
        <div class="buttons">
            <div class="button close"></div>
            <div class="button minimize"></div>
            <div class="button maximize"></div>
        </div>
        <div class="title">朋友圈</div>
        <%--<div class="friendsCircle"></div>--%>
        <div class="page">
            <%--<input type="button" class="prepage" value="上一页" onclick="doPrepage()">--%>
            <%--<input type="button" class="nextpage" value="下一页" onclick="doNextpage()">--%>
            <button type="button" class="btn btn-info" onclick="doPrepage()">previous</button>
            <button type="button" class="btn btn-info" onclick="doNextpage()">next</button>
        </div>
    </div>

    <ul class="messages">
        <%
            String p = request.getParameter("page");
            ZoneDao zoneDao = new ZoneDao();
            LinkedList<Zone> res = zoneDao.select();
            for(int i = res.size()-1; ; i--) {
                out.println("<li class=\"message left appeared\">");
                out.print("<div class=\"avatar\">");
                out.println("<div class=\"avatar_text\">");
                out.println(res.get(i).getAccount() + "</div></div>");
                out.println("<div class=\"text_wrapper\">");
                out.println("<div class=\"text\">" + res.get(i).getShuoshuo() + "</div></div>");
                out.println("<div><input type=\"button\" style=\"float:right;\" value=\"评论\" onclick=\"eval()\"/></div>");
                out.println("<div class=\"time\">"+ res.get(i).getDate()+"</div>");
                out.println("<hr width=\"100%\" color=\"#FF0000\" size=\"5\" style=\"border-top-width: 2px;\">");
                out.println("</li>");
                if(i == 0 || res.size()-i == 3) {
                    break;
                }
            }
        %>
    </ul>

    <div class="bottom_wrapper clearfix">
        <div class="message_input_wrapper">
            <input class="message_input" placeholder="Type your message here..." />
        </div>
        <div class="send_message">
            <div class="icon"></div>
            <div class="text">发表</div>
        </div>
    </div>
</div>

<div class="message_template">
    <li class="message left appeared">
        <div class="avatar">
            <div class="avatar_text"></div>
        </div>
        <div class="text_wrapper">
            <div class="text"></div>
        </div>
        <div class="time"></div>
        <div><input type="button" style="float:right;" value="评论" onclick="eval()"/></div>
        <hr width="100%" color="#FF0000" size="5" style="border-top-width: 2px;">
    </li>
</div>
<div class="time_template">
    <li>
        <div class="time"></div>
    </li>
</div>

<script src="friendsCircle.js"></script>
</body>
</html>

