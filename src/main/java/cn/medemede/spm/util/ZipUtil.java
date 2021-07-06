package cn.medemede.spm.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Saber
 */
public class ZipUtil {

    /**
     * 压缩并导出文件
     *
     * @param zipPath         压缩文件临时路径  路径最后不要有 /
     * @param zipName         压缩为文件名 **.zip
     * @param createFilesPath 需要压缩的文件列表
     * @param response
     */
    public static void downloadZip(String zipPath, String zipName, List<String> createFilesPath, HttpServletResponse response) {

        byte[] buffer = new byte[1024];
        String strZipPath = zipPath + "/" + zipName;
        try {
            File tmpZip = new File(zipPath);
            if (!tmpZip.exists()) {
                tmpZip.mkdirs();
            }
            File tmpZipFile = new File(strZipPath);
            if (!tmpZipFile.exists()) {
                tmpZipFile.createNewFile();
            }
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
            File[] file1 = new File[createFilesPath.size()];

            //读取需要压缩的文件
            for (int i = 0; i < createFilesPath.size(); i++) {
                file1[i] = new File(zipPath + "/" + createFilesPath.get(i));
            }


            for (File aFile1 : file1) {
                FileInputStream fis = new FileInputStream(aFile1);
                out.putNextEntry(new ZipEntry(aFile1.getName()));
                //设置压缩文件内的字符编码，不然会变成乱码 out.setEncoding("UTF-8");
                int len;
                // 读入需要下载的文件的内容，打包到zip文件
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.closeEntry();
                fis.close();
                //删除临时文件
                aFile1.delete();
            }
            out.close();

            //打包完成，开始下载
            downloadFile(zipPath, zipName, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 以压缩文件导出
     *
     * @param fileName
     * @param filePath
     * @param response
     */
    private static void downloadFile(String filePath, String fileName, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        // response.setContentType("application/octet-stream");

        try {
            File file = new File(filePath, fileName);
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            //删除临时文件
            file.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
