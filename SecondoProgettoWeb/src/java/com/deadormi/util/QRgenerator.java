/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.log4j.Logger;

/**
 *
 * @author Davide
 */
public class QRgenerator {
    
    static Logger log = Logger.getLogger(QRgenerator.class);

     final static String QR_RESOURCE_PATH = "/resource/qrcode";

    static String create_qr(HttpServletRequest request, String website) {
        ByteArrayOutputStream out = QRCode.from(website)
                                        .to(ImageType.PNG).withSize(125, 125).stream();
        
        String qr_path = request.getServletContext().getRealPath(QR_RESOURCE_PATH + "/");
        //creo la cartella se non c'è
        File directory = new File(qr_path);
        if (!directory.exists()) {
            log.debug("la cartella non esiste la creo");
            directory.mkdirs();
        }
 
        String file_name = Md5.getMD5(website);
        
        try {
            
            FileOutputStream fout = new FileOutputStream(new File(qr_path +"/"+ file_name + ".png"));
 
            fout.write(out.toByteArray());
 
            fout.flush();
            fout.close();
 
        } catch (FileNotFoundException e) {
            log.warn("File not found" + e);
        } catch (IOException e) {
            log.warn("IOException: " + e);
        }
        return file_name + ".png";
    }
    
    
    
}
