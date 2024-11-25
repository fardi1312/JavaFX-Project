/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author ACER
 */
public class QrCode  { 
    
    public Image Qrcode (String data)throws Exception {
    String details =data;
    ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();
   BufferedImage qrImage = ImageIO.read(new ByteArrayInputStream(out.toByteArray()));

        Image qrFxImage = SwingFXUtils.toFXImage(qrImage, null);
  return qrFxImage;

    
}
}
