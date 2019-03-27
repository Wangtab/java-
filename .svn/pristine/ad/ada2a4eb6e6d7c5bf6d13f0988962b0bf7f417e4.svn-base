package com.lamp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2017/11/24.
 */
@Controller
public class AzssessmentController extends  BaseController {
    //文件上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestParam(value = "filename", required = false) MultipartFile filename,
                       HttpServletResponse response)
            throws IOException {

        if (!filename.isEmpty()) {

            String url = "D:\\upload";

            File file = new File(url);

            if (!file.isDirectory() && !file.exists()) {
                file.mkdir();
            }

            InputStream is = filename.getInputStream();

            String name = filename.getOriginalFilename();

            FileOutputStream fileOutputStream = new FileOutputStream(url + "/" + name);

            byte[] b = new byte[is.available()];

            is.read(b);

            fileOutputStream.write(b);

        } else {
            response.getWriter().print("n");
        }

        response.getWriter().print("Y");
    }


    /**
     * 列出所有的文件
     */
    @RequestMapping("listFile")
    public String listFile(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        // 获取上传文件的目录
        /*		ServletContext sc = request.getSession().getServletContext();*/
        // 上传位置
        String uploadFilePath = "D:/upload/" + "/"; // 设定文件保存的目录
        // 存储要下载的文件名
        Map<String, String> fileNameMap = new HashMap<String, String>();

        // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        listfile(new File(uploadFilePath), fileNameMap);// File既可以代表一个文件也可以代表一个目录
        // 将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        System.out.println("fileNameMap"+fileNameMap);
        return "listFile";
    }

    public void listfile(File file, Map<String, String> map) {
        // 如果file代表的不是一个文件，而是一个目录
        if (!file.isFile()) {
            // 列出该目录下的所有文件和目录
            File files[] = file.listFiles();
            // 遍历files[]数组
            for (File f : files) {
                // 递归
                listfile(f, map);
            }
        } else {
            /**
             * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
             * file.getName().indexOf
             * ("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：9349249849-88343-8344_阿_凡_达.avi
             * 那么file.getName().substring(file.getName().indexOf("_")+1)
             * 处理之后就可以得到
             */
            String realName = file.getName().substring(
                    file.getName().indexOf("_") + 1);
            // file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称，有可能会重复
            map.put(file.getName(), realName);
        }
    }


  /*下载文件*/
    @RequestMapping("/downFile")
    public void downFile(HttpServletRequest request,
                         HttpServletResponse response) {
        System.out.println("1");
        // 得到要下载的文件名
        String fileName = request.getParameter("filename");
        System.out.println("2");
        try {
            fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
            System.out.println("3");
            // 获取上传文件的目录
            ServletContext sc = request.getSession().getServletContext();
            System.out.println("4");
            // 上传位置
            String fileSaveRootPath =  "D:/upload/" + "/";

            System.out.println(fileSaveRootPath + "\\" + fileName);
            // 得到要下载的文件
            File file = new File(fileSaveRootPath + "\\" + fileName);

            // 如果文件不存在
            if (!file.exists()) {
                request.setAttribute("message", "您要下载的资源已被删除！！");
                System.out.println("您要下载的资源已被删除！！");
                return;
            }
            // 处理文件名
            String realname = fileName.substring(fileName.indexOf("_") + 1);
            // 设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(realname, "UTF-8"));
            // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(fileSaveRootPath + "\\" + fileName);
            // 创建输出流
            OutputStream out = response.getOutputStream();
            // 创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            // 关闭文件输入流
            in.close();
            // 关闭输出流
            out.close();
        } catch (Exception e) {

        }
    }
}





