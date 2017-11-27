package cn.medemede.j2ee.util;

import freemarker.cache.URLTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.Map;

public class ExportWord2 {

    private static Configuration freemarkerConfig;

    static {
        freemarkerConfig = new Configuration(Configuration.VERSION_2_3_23);
        freemarkerConfig.setEncoding(Locale.getDefault(), "UTF-8");
    }

    public static void exportWord(final String resourcePath, String templatePath, String fileName, Map<String, Object> data, HttpServletResponse response) throws IOException {
        freemarkerConfig.setTemplateLoader(new URLTemplateLoader() {
            protected URL getURL(String s) {
                return ExportWord2.class.getResource(resourcePath);
            }
        });
        File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        Template temp;
        try {
            temp = freemarkerConfig.getTemplate(templatePath);
            file = createDoc(data, temp);
            fin = new FileInputStream(file);
            response.setContentType("application/msword");
            // 设置浏览器以下载的方式处理该文件名
            response.setHeader("Content-Disposition", "attachment;filename="
                    .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));

            out = response.getOutputStream();
            // 缓冲区
            byte[] buffer = new byte[512];
            int bytesToRead = -1;
            // 通过循环将读入的Word文件的内容输出到浏览器中
            while ((bytesToRead = fin.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fin != null) fin.close();
            if (out != null) out.close();
            if (file != null) file.delete(); // 删除临时文件
        }
    }

    private static File createDoc(Map<String, Object> data, Template template) {
        File f = new File("demp.doc");
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            template.process(data, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return f;
    }

    public static boolean creatDoc(Map<String,Object> data,String resourcePath, String templatePath,String pathName) throws IOException {
        freemarkerConfig.setTemplateLoader(new URLTemplateLoader() {
            protected URL getURL(String s) {
                return ExportWord2.class.getResource(resourcePath);
            }
        });

        Template temp=freemarkerConfig.getTemplate(templatePath);
        File dir=new File("./doctemp");
        if (!dir.exists()||!dir.isDirectory()){
            dir.mkdir();
        }
        File f = new File("./doctemp/"+pathName+".doc");
        try {
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            temp.process(data, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

        return true;
    }

}
