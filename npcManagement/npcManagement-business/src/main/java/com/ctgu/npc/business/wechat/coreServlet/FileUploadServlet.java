package com.ctgu.npc.business.wechat.coreServlet;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 54027 on 2018/1/20.
 */
public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public FileUploadServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> res = new HashMap<String, Object>();
        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String path = request.getRealPath("/upload");
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        System.out.println("path=" + path);

        request.setCharacterEncoding("utf-8");  //设置编码
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //如果没以下两行设置的话,上传大的文件会占用很多内存，
        //设置暂时存放的存储室,这个存储室可以和最终存储文件的目录不同
        /**
         * 原理: 它是先存到暂时存储室，然后再真正写到对应目录的硬盘上，
         * 按理来说当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
         * 然后再将其真正写到对应目录的硬盘上
         */
        factory.setRepository(dir);
        //设置缓存的大小，当上传文件的容量超过该缓存时，直接放到暂时存储室
        factory.setSizeThreshold(1024 * 1024);
        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);
            FileItem picture = null;
            for (FileItem item : list) {
                //获取表单的属性名字
                String name = item.getFieldName();
                //如果获取的表单信息是普通的 文本 信息
                if (item.isFormField()) {
                    //获取用户具体输入的字符串
                    String value = item.getString();
                    request.setAttribute(name, value);
                    System.out.println("name=" + name + ",value=" + value);
                } else {
                    picture = item;
                }
            }

            //自定义上传图片的名字为userId.jpg
            String orderId = request.getAttribute("orderId")+"";
            String photoNo = request.getAttribute("photoNo")+"";
            if (orderId.equals("null")||photoNo.equals("null")){
                res.put("Code", "-20000");
                res.put("Message", "orderId is null");
                printWriter.write(JSONObject.fromObject(res).toString());
                printWriter.flush();
            }
            String fileName = orderId + photoNo+".jpg";
            String destPath = path +"/"+ fileName;
            String pictureDir =  "https://n.weibaobao.net.cn"+destPath.split("webapps")[1].replace("\\","/");
            System.out.println("destPath=" + destPath);
            //真正写到磁盘上
            File file = new File(destPath);
            OutputStream out = new FileOutputStream(file);
            if (picture==null){
                res.put("Code", "-20000");
                res.put("Message", "picture not found");
                printWriter.write(JSONObject.fromObject(res).toString());
                printWriter.flush();
            }
            InputStream in = picture.getInputStream();
            int length = 0;
            byte[] buf = new byte[1024];
            // in.read(buf) 每次读到的数据存放在buf 数组中
            while ((length = in.read(buf)) != -1) {
                //在buf数组中取出数据写到（输出流）磁盘上
                out.write(buf, 0, length);
            }
            in.close();
            out.close();
           // String api = getLogApi("savePicture");
            String xml =  "<Req><Service>" +
                    "<pictureId>"+orderId+photoNo+"</pictureId>" +
                    "<orderId>"+orderId+"</orderId>" +
                    "<pictureDir>"+pictureDir+"</pictureDir>" +
                    "</Service>" +
                    "<TransactionCode>110022</TransactionCode>" +
                    "</Req>";
           // System.out.println(api+"\n----------\n"+xml);

        } catch (FileUploadException e1) {
            System.out.println(e1);
            res.put("Code", "-20000");
            res.put("Message", "FileUploadException");
            printWriter.write(JSONObject.fromObject(res).toString());
            printWriter.flush();
        } catch (Exception e) {
            System.out.println(e);
            res.put("Code", "-20000");
            res.put("Message", "Exception");
            printWriter.write(JSONObject.fromObject(res).toString());
            printWriter.flush();
        }
        res.put("Code", "10000");
        res.put("Message", "success");
        printWriter.write(JSONObject.fromObject(res).toString());
        printWriter.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}
