package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class ImageCropper {
    public static void main(String[] args) {
        String option = args[0];

        String directoryPath;
        int srcX;
        int srcY;
        int cropWidth;
        int cropHeight;

        if(option.equals("default")) {
            directoryPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Pictures\\Screenshots";
            srcX = 1900;
            srcY = 63;
            cropWidth = 680;
            cropHeight = 954;
        } else if (option.equals("custom")) {
            directoryPath = args[1];
            srcX = Integer.parseInt(args[2]);
            srcY = Integer.parseInt(args[3]);
            cropWidth = Integer.parseInt(args[4]);
            cropHeight = Integer.parseInt(args[5]);
        } else if (option.equals("super")) {
            directoryPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Pictures\\Screenshots";
            srcX = 1983;
            srcY = 24;
            cropWidth = 514;
            cropHeight = 1032;
        } else {
            return;
        }

        List<File> files = listFiles(directoryPath);

        for (File file : files) {
            cropImage(file, srcX, srcY, cropWidth, cropHeight);
        }
    }

    public static void cropImage(File src, int srcX, int srcY, int cropWidth, int cropHeight)
    {
        try {
            BufferedImage originalImage = ImageIO.read(src);

            // Create a new image that will contain the cropped section
            BufferedImage croppedImage = new BufferedImage(cropWidth, cropHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = croppedImage.createGraphics();
            g.drawImage(originalImage, 0, 0, cropWidth, cropHeight, srcX, srcY, srcX + cropWidth, srcY + cropHeight, null);
            g.dispose();

            // Save the cropped image to a file
            ImageIO.write(croppedImage, "png", new File(src.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<File> listFiles(String directoryPath) {
        File directory = new File(directoryPath);
        File[] fileList = directory.listFiles();
        List<File> files = new ArrayList<>();

        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    files.add(file);
                }
            }
        }
        return files;
    }
}