package com.gmail.sge.serejka;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetUserServlet extends HttpServlet {
    private UserList userList = UserList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String type = req.getParameter("type");
        String json = null;
        if (type.equals("active")){
            List<String> list = userList.activeUsers();
            json = "Active users are: " + list.toString();
        } else if (type.equals("current")){
            String name = req.getParameter("name");
            json = userList.getUserByNameToJSON(name);
        } else if (type.equals("exit")){
            String name = req.getParameter("name");
            User user = userList.getUserByName(name);
            user.setActive(false);
            System.out.println("EXIT for " + name);
        } else if (type.equals("check")){
            String name = req.getParameter("name");
            json = "" + userList.getUserByName(name).isActive();
        }

        if (json != null){
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);
        userList.authorizedUser(User.fromJson(bufStr));

    }

    private byte []requestBodyToArray(HttpServletRequest request) throws IOException {
        InputStream is = request.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0){
                bos.write(buf,0,r);
            }
        } while (r != -1);

        return bos.toByteArray();
    }
}
