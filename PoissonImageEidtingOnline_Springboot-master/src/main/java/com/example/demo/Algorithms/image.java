package com.example.demo.Algorithms;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Clock;

public class image extends JFrame {
    public static long GaussianSeidel(int Xs, int Ys){
        long finishingTime = 0;
        BufferedImage Mask=null;
        File Maskf;
        BufferedImage Target=null;
        File Targetf;
        BufferedImage Source=null;
        File Sourcef;
        //read image
        try{
            Maskf = new File(System.getProperty("user.dir")+ "/static/mask.jpg");
            Mask = ImageIO.read(Maskf);
            Targetf = new File(System.getProperty("user.dir")+ "/static/insertimage.jpg");
            Target = ImageIO.read(Targetf);
            Sourcef = new File(System.getProperty("user.dir")+ "/static/background.jpg");
            Source = ImageIO.read(Sourcef);
        }catch(IOException e){
            System.out.println(e);
        }
        //get width and height of Mask
        int widthM = Mask.getWidth();
        int heightM = Mask.getHeight();
        //calculate the RGB values of source image and target image
        double[][][] targetRGB = imageTORGB(Target);
        double[][][] sourceRGB = imageTORGB(Source);
        double[][][] maskRGB = imageTORGB(Mask);
        //calculate the Laplacian of the target image
        double[][][] b = Laplacian(widthM,heightM,targetRGB);
        // initialize the sourceRGB
        for(int y=Ys;y<heightM+Ys;y++) {
            for (int x = Xs; x < widthM + Xs; x++) {
                if (maskRGB[x-Xs][y-Ys][0] == 255 && maskRGB[x-Xs][y-Ys][1] == 255 && maskRGB[x-Xs][y-Ys][2] == 255) {
                    for (int z = 0; z < 3; z++) {
                        sourceRGB[x][y][z] = targetRGB[x - Xs][y - Ys][z];
                    }
                }
            }
        }
        outputImage(Xs,Ys,widthM,heightM,maskRGB,Source,sourceRGB,"before.jpg");
        //Gaussian-Seidel iteration
        boolean c = true;
        if(c) {
            Clock clock = Clock.systemDefaultZone();
            long start = clock.millis();
            int MaxIter = 50000;
            double e0 = Math.pow(10,-11);
            for (int i = 0; i < MaxIter; i++) {
                double err = 0;
                for (int y = Ys; y < heightM + Ys; y++) {
                    for (int x = Xs; x < widthM + Xs; x++) {
                        if (maskRGB[x-Xs][y-Ys][0] == 255 && maskRGB[x-Xs][y-Ys][1] == 255 && maskRGB[x-Xs][y-Ys][2] == 255) {
                            for (int z = 0; z < 3; z++) {
                                double s = sourceRGB[x][y][z];
                                double news = ((sourceRGB[x - 1][y][z] + sourceRGB[x + 1][y][z] + sourceRGB[x][y - 1][z] + sourceRGB[x][y + 1][z])
                                        -b[x - Xs][y - Ys][z]) / (4.0);
                                sourceRGB[x][y][z] = news;
                                err += (news - s) * (news - s);
                            }
                        }
                    }
                }
                //outputImage(Xs,Ys,widthM,heightM,maskRGB,Source,sourceRGB,"output.jpg");
                System.out.println(err);
                if (err < e0) {
                    long end = clock.millis();
                    finishingTime = end-start;
                    System.out.println(finishingTime);
                    break;
                }
            }
        }

        // convert RGB back to image
        outputImage(Xs,Ys,widthM,heightM,maskRGB,Source,sourceRGB,"output.jpg");
        return finishingTime;

    }

    public static long Multigrid(int Xs, int Ys){
        long finishingTime = 0;
        BufferedImage Mask=null;
        File Maskf;
        BufferedImage Target=null;
        File Targetf;
        BufferedImage Source=null;
        File Sourcef;
        //read image
        try{
            Maskf = new File(System.getProperty("user.dir")+ "/static/mask.jpg");
            Mask = ImageIO.read(Maskf);
            Targetf = new File(System.getProperty("user.dir")+ "/static/insertimage.jpg");
            Target = ImageIO.read(Targetf);
            Sourcef = new File(System.getProperty("user.dir")+ "/static/background.jpg");
            Source = ImageIO.read(Sourcef);
        }catch(IOException e){
            System.out.println(e);
        }
        //get width and height of Mask
        int widthM = Mask.getWidth();
        int heightM = Mask.getHeight();
        //calculate the RGB values of source image and target image
        double[][][] targetRGB = imageTORGB(Target);
        double[][][] sourceRGB = imageTORGB(Source);
        double[][][] maskRGB = imageTORGB(Mask);
        //calculate the Laplacian of the target image
        double[][][] b = Laplacian(widthM,heightM,targetRGB);
        // initialize the sourceRGB
        for(int y=Ys;y<heightM+Ys;y++) {
            for (int x = Xs; x < widthM + Xs; x++) {
                if (maskRGB[x-Xs][y-Ys][0] == 255 & maskRGB[x-Xs][y-Ys][1] == 255 && maskRGB[x-Xs][y-Ys][2] == 255) {
                    for (int z = 0; z < 3; z++) {
                        sourceRGB[x][y][z] = targetRGB[x - Xs][y - Ys][z];
                    }
                }
            }
        }
        outputImage(Xs,Ys,widthM,heightM,maskRGB,Source,sourceRGB,"before.jpg");
        // initialize the target image
        for(int y=0;y<heightM;y++) {
            for (int x = 0; x < widthM; x++) {
                if (maskRGB[x][y][0] < 255 || maskRGB[x][y][1] < 255 || maskRGB[x][y][2] < 255) {
                    for (int z = 0; z < 3; z++) {
                        targetRGB[x][y][z]=sourceRGB[x+Xs][y+Ys][z];
                    }
                }
            }
        }
        //multigrid iteration
        boolean c = true;
        if(c) {
            int MaxIter = 5000;
            double e0 = Math.pow(10,-11);
            Clock clock = Clock.systemDefaultZone();
            long start = clock.millis();
            for (int i = 0; i < MaxIter; i++) {
                double err = 0;
                double[][][] newtargetRGB = copyRGB(targetRGB);
                newtargetRGB = multigridRecursion(widthM - 1, heightM - 1, 1, 1, maskRGB, 1, newtargetRGB, b);
                for (int y = 0; y < heightM; y++) {
                    for (int x = 0; x < widthM; x++) {
                        if (maskRGB[x][y][0] == 255 & maskRGB[x][y][1] == 255 && maskRGB[x][y][2] == 255) {
                            for (int z = 0; z < 3; z++) {
                                err += (newtargetRGB[x][y][z] - targetRGB[x][y][z]) * (newtargetRGB[x][y][z] - targetRGB[x][y][z]);
                            }
                        } else {
                            for (int z = 0; z < 3; z++) {
                                newtargetRGB[x][y][z] = targetRGB[x][y][z];
                            }
                        }
                    }
                }
                targetRGB = newtargetRGB;
                System.out.println(err);
                if (err < e0) {
                    long end = clock.millis();
                    finishingTime = end - start;
                    System.out.println(finishingTime);
                    break;
                }
            }
        }
        for(int y=Ys;y<heightM+Ys;y++) {
            for (int x = Xs; x < widthM + Xs; x++) {
                if (maskRGB[x-Xs][y-Ys][0] == 255 & maskRGB[x-Xs][y-Ys][1] == 255 && maskRGB[x-Xs][y-Ys][2] == 255) {
                    for (int z = 0; z < 3; z++) {
                        sourceRGB[x][y][z]=targetRGB[x-Xs][y-Ys][z];
                    }
                }
            }
        }
        // convert RGB back to image
        outputImage(Xs,Ys,widthM,heightM,maskRGB,Source,sourceRGB,"output.jpg");
        return finishingTime;
    }

    public static double[][][] multigridRecursion(int widthM, int heightM, int Xs, int Ys, double[][][] maskRGB, int delta, double[][][] X, double[][][] b){
        // pre-iterations
        for (int i=0; i<10;i++) {
            for (int y = Ys; y < heightM; y = y + delta) {
                for (int x = Xs; x < widthM; x= x + delta) {
                    if (maskRGB[x][y][0] == 255  && maskRGB[x][y][1] == 255 && maskRGB[x][y][2] == 255) {
                        for (int z = 0; z < 3; z++) {
                                double news = ((X[x - delta][y][z] + X[x + delta][y][z] + X[x][y - delta][z] + X[x][y + delta][z])
                                        - b[x][y][z] * delta * delta) / (4.0);
                                X[x][y][z] = news;
                        }
                   }
                }
            }
        }
        double[][][] residual = new double[widthM][heightM][3];
        for (int y = Ys; y < heightM; y = y + delta) {
            for (int x = Xs; x < widthM; x = x + delta) {
                if (maskRGB[x][y][0] == 255 & maskRGB[x][y][1] == 255 && maskRGB[x][y][2] == 255) {
                    for (int z = 0; z < 3; z++) {
                        residual[x][y][z] = b[x][y][z]-(X[x - delta][y][z] + X[x + delta][y][z] + X[x][y - delta][z] + X[x][y + delta][z]-4*X[x][y][z])/(double)(delta*delta);
                    }
                }
            }
        }
        // Start-Recursion
        delta *= 2;
        // Base Case
        if(Xs+delta>=widthM || Ys+delta>=heightM){
            return X;
        }
        double[][][] err = new double[widthM][heightM][3];


        err = multigridRecursion(widthM-delta,heightM-delta,Xs+delta,Ys+delta,maskRGB,delta,err,residual);

        //interpolation
        for (int y=Ys;y<heightM;y=y+delta){
            for(int x=Xs+delta;x<widthM ;x=x+delta){
                if (maskRGB[x][y][0] == 255 & maskRGB[x][y][1] == 255 && maskRGB[x][y][2] == 255) {
                    for (int z = 0; z < 3; z++) {
                        err[x-delta/2][y][z] = (err[x][y][z]+err[x-delta][y][z])/2.0;
                    }
               }
            }
        }
        for(int x=Xs;x<widthM ;x+=delta/2){
            for(int y=Ys+delta;y<heightM;y+=delta){
                if (maskRGB[x][y][0] == 255 & maskRGB[x][y][1] == 255 && maskRGB[x][y][2] == 255) {
                    for (int z = 0; z < 3; z++) {
                        err[x][y-delta/2][z] = (err[x][y][z]+err[x][y-delta][z])/2.0;
                    }
                }
            }
        }
        delta /= 2;
        // add the error back
        for (int y = Ys; y < heightM; y = y + delta) {
            for (int x = Xs; x < widthM; x= x + delta) {
                if (maskRGB[x][y][0] == 255 & maskRGB[x][y][1] == 255 && maskRGB[x][y][2] == 255) {
                    for (int z = 0; z < 3; z++) {
                        X[x][y][z] = X[x][y][z]+err[x][y][z];
                    }
                }
            }
        }
        // post-Iterations
        for (int i=0; i<10;i++) {
            for (int y = Ys; y < heightM; y = y + delta) {
                for (int x = Xs; x < widthM; x= x + delta) {
                    if (maskRGB[x][y][0] == 255 & maskRGB[x][y][1] == 255 && maskRGB[x][y][2] == 255) {
                        for (int z = 0; z < 3; z++) {
                            double news = ((X[x - delta][y][z] + X[x + delta][y][z] + X[x][y - delta][z] + X[x][y + delta][z])
                                    - b[x][y ][z]*delta*delta) / (4.0);
                            X[x][y][z] = news;
                        }
                    }
                }
            }
        }
        return X;

    }
    public static double[][][] Laplacian(int widthM, int heightM,double[][][] targetRGB){
        double[][][] b = new double[widthM][heightM][3];
        for (int x = 1; x < widthM-1; x++) {
            for (int y = 1; y < heightM-1; y++) {
                    double[] rgb = filter(x, y, targetRGB);
                    for (int z = 0; z < 3; z++) {
                        b[x][y][z] = rgb[z];
                    }
            }
        }
        return b;
    }

    public static void outputImage(int Xs, int Ys, int widthM, int heightM,double[][][] maskRGB,BufferedImage Source, double[][][] sourceRGB,String name){
        for(int y=Ys;y<heightM+Ys;y++){
            for(int x=Xs;x<widthM+Xs;x++){
                if(maskRGB[x-Xs][y-Ys][0] == 255 & maskRGB[x-Xs][y-Ys][1] == 255 && maskRGB[x-Xs][y-Ys][2] == 255){
                    for(int z = 0;z<3;z++){
                        int p = Source.getRGB(x,y);
                        int a = p>>24;
                        if(sourceRGB[x][y][0]>225){
                            sourceRGB[x][y][0] = 225;
                        }
                        if(sourceRGB[x][y][0]<0){
                            sourceRGB[x][y][0] = 0;
                        }
                        if(sourceRGB[x][y][1]>225){
                            sourceRGB[x][y][1] = 225;
                        }
                        if(sourceRGB[x][y][1]<0){
                            sourceRGB[x][y][1] = 0;
                        }
                        if(sourceRGB[x][y][2]>225){
                            sourceRGB[x][y][2] = 225;
                        }
                        if(sourceRGB[x][y][2]<0){
                            sourceRGB[x][y][2] = 0;
                        }
                        int red = (int) sourceRGB[x][y][0];
                        int green = (int) sourceRGB[x][y][1];
                        int blue = (int) sourceRGB[x][y][2];
                        p = (a<<24)|(red<<16)|(green<<8)|blue;
                        Source.setRGB(x,y,p);
                    }
                }
            }
        }

        try{
            // write the output file
            File Output = new File(System.getProperty("user.dir")+ "/static/"+name);
            ImageIO.write(Source,"jpg",Output);
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public static double[] filter(int x, int y, double[][][] imgRGB){
        double[] rgb = new double[3];
        for(int i=0;i<3;i++){
            rgb[i] = -4*imgRGB[x][y][i]+imgRGB[x-1][y][i]+imgRGB[x+1][y][i]+imgRGB[x][y-1][i]+imgRGB[x][y+1][i];
        }
        return rgb;
    }
    public static double[][][] imageTORGB(BufferedImage img){
        double[][][] imgRGB = new double[img.getWidth()][img.getHeight()][3];
        for(int i =0; i<img.getWidth();i++){
            for(int j=0; j<img.getHeight();j++) {
                Color c = new Color(img.getRGB(i, j));
                imgRGB[i][j][0] = c.getRed();
                imgRGB[i][j][1] = c.getGreen();
                imgRGB[i][j][2] = c.getBlue();
            }
        }
        return imgRGB;
    }
    public static double[][][] copyRGB(double[][][] RGB){
        double[][][] copy = new double[RGB.length][RGB[0].length][RGB[0][0].length];
        for(int i=0; i<RGB.length;i++){
            for(int j=0; j<RGB[0].length;j++){
                for(int k=0;k<RGB[0][0].length;k++){
                    copy[i][j][k] = RGB[i][j][k];
                }
            }
        }
        return copy;
    }
}
