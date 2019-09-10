package com.example.demo.Algorithms;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
public class imagecoder {
    public static String Encoder(File f){
        String encodstring = encodeFileToBase64Binary(f);
        return encodstring;
    }

    public static File Decoder(String encodstring,String name) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] imageByte = decoder.decodeBuffer(encodstring);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        File Output = new File(System.getProperty("user.dir")+ "/static/" + name+".jpg");
        BufferedImage image = ImageIO.read(bis);
        ImageIO.write(image,"jpg",Output);
        return Output;
    }

    public static void MaskDisplay() throws IOException {
        File Maskf = new File(System.getProperty("user.dir")+ "/static/mask.jpg");
        BufferedImage Mask = ImageIO.read(Maskf);
        int widthM = Mask.getWidth();
        int heightM = Mask.getHeight();
        File Targetf = new File(System.getProperty("user.dir")+ "/static/insertimage.jpg");
        BufferedImage Target = ImageIO.read(Targetf);
        BufferedImage maskDisplay = new BufferedImage(widthM, heightM, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < heightM ; i++) {
            for (int j = 0; j < widthM; j++) {
                Color c = new Color(Mask.getRGB(j, i));
                int r = c.getRed();
                int b = c.getBlue();
                int g = c.getGreen();
                if ((r > 230 && b > 230 && g > 230)) {
                    maskDisplay.setRGB(j, i, Target.getRGB(j,i));
                }
                else{
                    maskDisplay.setRGB(j, i, Mask.getRGB(j,i) & 0x00FFFFFF);
                }
            }
        }
        ImageIO.write(maskDisplay, "PNG", new File(System.getProperty("user.dir")+ "/static/maskDisplay.png"));
    }

    private static String encodeFileToBase64Binary(File file){
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            BASE64Encoder encoder = new BASE64Encoder();
            encodedfile = encoder.encode(bytes);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }



}
