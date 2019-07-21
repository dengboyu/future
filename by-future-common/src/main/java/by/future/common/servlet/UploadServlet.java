package by.future.common.servlet;

import by.future.common.constant.Const;
import by.future.common.commonenum.ResultCodeEnum;
import by.future.common.exception.ByException;
import by.future.common.utils.FileUtils;
import by.future.common.utils.UUIDUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 上传文件与下载
 *
 * @author by@Deng
 * @create 2017-10-12 21:39
 */
@WebServlet(name = "UploadServlet", urlPatterns = "/UploadServlet")
public class UploadServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            //保存文件地址
            String savePath = this.getServletContext().getRealPath("/WEB-INF/download");

            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //超过缓冲区大小是，保存临时文件的文件夹
            diskFileItemFactory.setRepository(new File(this.getServletContext().getRealPath("/WEB-INF/temp")));

            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            servletFileUpload.setHeaderEncoding(Const.CHARSET_UTF8);  //解决上传文件名的中文乱码问题
            servletFileUpload.setProgressListener(new ProgressListener() {
                @Override
                public void update(long pBytesRead, long pContentLength, int i) {
                    System.out.println("当前文件大小为:"+pContentLength+";进度为:"+pBytesRead);
                }
            });    //上传进度监听器

            servletFileUpload.setFileSizeMax(10*1024);   //设置单个文件最大值
            servletFileUpload.setSizeMax(100*1024);      //设置总量文件最大值
            List<FileItem> fileItemList = servletFileUpload.parseRequest(request);
            for(FileItem fileItem :fileItemList){
                if(fileItem.isFormField()){
                    //普通输入项
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString();
                    value = new String(value.getBytes("ISO-8859-1"),Const.CHARSET_UTF8);  //中文乱码问题
                    System.out.println("key="+name+",value="+value);
                }else{
                    String name = fileItem.getName();   //上传文件名称(不同浏览器上传名称不同)
                    if(StringUtils.isEmpty(name)) continue;

                    name = name.substring(name.lastIndexOf(File.separator)+1);
                    name = makeFileName(name);
                    String finalSavePath = makeDirectory(name,savePath);    //最终文件保存目录

                    InputStream inputStream = fileItem.getInputStream();
                    FileOutputStream outputStream = new FileOutputStream(finalSavePath+File.separator+name);

                    //读取流文件
                    byte[] buffer = new byte[1024];
                    int len =0;
                    while ((len = inputStream.read(buffer))>0){
                        outputStream.write(buffer,0,len);
                    }

                    outputStream.close();
                    inputStream.close();
                    fileItem.delete();  //删除临时文件(必须在关闭流后边)

                }
            }

        }catch (FileUploadBase.FileSizeLimitExceededException e){
            response.getWriter().write("文件大小超出范围"); //超出文件大小报异常
        }catch (Exception e) {
            throw new ByException(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doGet(request, response);
    }


    /**
     * 产生文件夹
     * @author by@Deng
     * @date 2017/10/12 下午10:58
     */
    public String makeDirectory(String filename,String savePath){
        //哈希打散算法

        int hashcode = filename.hashCode(); //哈希地址
        int dir1 =hashcode&0xf; //第4位
        int dir2 = (hashcode&0xf0)>>4;   //5-8位

        String dir =savePath +File.separator + dir1 + File.separator + dir2 ;

        FileUtils.createDirectory(dir); //创建目录

        return dir;
    }

    /**
     * 产生唯一文件名
     * @author by@Deng
     * @date 2017/10/12 下午10:49
     */
    public String makeFileName(String filename){
        return UUIDUtils.getUUID()+"_"+filename;
    }






    /**
     * 下载文件
     * @author by@Deng
     * @date 2018/1/30 上午11:04
     */
    /*@GetMapping("downLoadFile")
    public void downLoadFile(@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {


        fileName = new String(fileName.getBytes("iso8859-1"),"utf-8");
        String path = makeDirectory(fileName,request.getServletContext().getRealPath("/WEB-INF/download"));

        File file = new File(path+File.separator+fileName);
        if(!file.exists()){
            throw new ByException("文件不存在");
        }

        String realName =fileName.substring(fileName.indexOf("_")+1);
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(realName,"UTF-8"));

        FileInputStream fileInputStream = new FileInputStream(path+File.separator+fileName);

        OutputStream outputStream = response.getOutputStream();
        byte buffer[] = new byte[1024];
        int len = 0;
        while((len = fileInputStream.read(buffer))>0){
            outputStream.write(buffer,0,len);
        }

        fileInputStream.close();
        outputStream.close();
    }*/
}
