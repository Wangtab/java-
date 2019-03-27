package com.lamp.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UploadFileUtils {

    /**
     * 批量上传文件 返回值为文件的新名字 UUID
     * @param multipartFile
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static String uploadFiles(HttpServletRequest request,MultipartFile multipartFile,String pathPic) throws IllegalStateException, IOException{
        String filePath = null;
        try {

            //文件的原始名称
            String originalFilename = multipartFile.getOriginalFilename();

            if (!multipartFile.equals(null) && !originalFilename.equals(null) && originalFilename.length() > 0) {
                //存储图片的物理路径
                String pic_path = request.getSession().getServletContext().getRealPath("/"+pathPic)+"/";
                //创建图片文件夹
                makeDirs(pic_path);
                //上传图片
                String newFileName1 =GetLocalTimes.timeMillis()+".jpg";
                //新图片路径
                File targetFile = new File(pic_path, newFileName1);
                //内存数据读入磁盘
                multipartFile.transferTo(targetFile);

                /*缩放图像*/
                //缩放后的图片路径
                String newFileName2 =GetLocalTimes.timeMillis()+"_lamp"+".jpg";
                //新图片路径
                File targetFile1 = new File(pic_path, newFileName2);

                //缩放图片
                scale2(targetFile.toString(),targetFile1.toString(),400,400,true);

                /*删除原图片*/
                deleteFile(targetFile.toString());

                /*新图片路径*/
                filePath =pathPic+"/"+newFileName2;

            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return filePath;
    }

    /*创建文件夹*/
    public static boolean makeDirs(String filePath) {
        File folder = new File(filePath);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    /**
     * 缩放图像（按高度和宽度缩放）
     * @param srcImageFile 源图像文件地址
     * @param result 缩放后的图像地址
     * @param height 缩放后的高度
     * @param width 缩放后的宽度
     * @param bb 比例不对时是否需要补白：true为补白; false为不补白;
     */
    public final static void scale2(String srcImageFile, String result, int height, int width, boolean bb) {
        try {
            double ratio = 0.0; // 缩放比例
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform
                        .getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除图片
     */

    public static void deleteFile(String sPath) {
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }
}
