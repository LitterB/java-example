package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/10/10 14:30
 */
public class CopyFile {

    public static void copy(String srcFile, String destFile) throws IOException {
        long begin = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        FileChannel fileChannelIn = null;
        FileChannel fileChannelOut = null;
        try (FileInputStream fileInputStream = new FileInputStream(srcFile);
             FileOutputStream fileOutputStream= new FileOutputStream(destFile)) {
            fileChannelIn = fileInputStream.getChannel();
            fileChannelOut = fileOutputStream.getChannel();
            int len = -1;
            while ((len = fileChannelIn.read(buffer)) !=  -1){
                buffer.flip();
                fileChannelOut.write(buffer);
                buffer.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("copy file used " + (end -begin) + "ms");
    }

    public static void main(String[] args) throws Exception{
//        CopyFile.copy("F:/德联.txt","F:/德联1.txt");
        String pathname = "F:/1.jpg";
        File file = new File(pathname);
        System.out.println(file.length() / 1024.0 + "kb");
        CopyFile.copy("F:/1.jpg","F:/2.jpg");
    }
}
