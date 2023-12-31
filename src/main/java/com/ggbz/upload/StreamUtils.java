package com.ggbz.upload;

import java.io.*;

public class StreamUtils {
    /**
     * 将输入流转换为字节数组
     * @param is
     * @return
     * @throws IOException
     */
    public static byte[] streamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();//创建输出流对象
        byte[] b = new byte[1024];//字节数组
        int len;
        while((len =  is.read(b)) != -1){
            bos.write(b,0,len);//把读取到是数据，写入bos
        }
        byte[] array = bos.toByteArray();//然后将bos 转成字节数组
        bos.close();
        return array;
    }

    /**
     * 将InputStream转换成String
     * @param is
     * @return
     * @throws IOException
     */
    public static String streamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while((line=reader.readLine()) != null){
            builder.append(line+"\r\n");
        }
        return builder.toString();
    }

}
