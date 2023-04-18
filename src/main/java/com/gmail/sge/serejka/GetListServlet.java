package com.gmail.sge.serejka;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetListServlet extends HttpServlet {

    private MessageList messageList = MessageList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fromStr = req.getParameter("from");
        String user = req.getParameter("user");
        int from = 0;
        try {
            from = Integer.parseInt(fromStr);
        } catch (Exception e){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }


        String json = messageList.toJSON(from, user);
        if (json != null){
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}