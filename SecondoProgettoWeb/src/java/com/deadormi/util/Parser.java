/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.util;

import com.deadormi.controller.FileController;
import com.deadormi.entity.Crew;
import com.deadormi.entity.Post;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author Davide
 */
public class Parser {

    static Logger log = Logger.getLogger(Parser.class);

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public Parser() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean isEmail(final String hex) {
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    public static boolean isNumeric(String num) {
        Pattern pattern = Pattern.compile("^-?\\d+\\.?\\d*$");
        Matcher matcher = pattern.matcher(num);
        return matcher.matches();
    }

    public static String checkTesto(HttpServletRequest request, String testo, String crew_id_string) throws SQLException {
        testo = testo.replaceAll("<[^>]*>", "");
        FileController fc = new FileController();
        log.debug("Ecco la string prima del parsing: " + testo);
        Pattern pqr = Pattern.compile("\\$QR\\$(.*?)\\$\\$");
        Matcher mqr = pqr.matcher(testo);
        String inizio;
        String trovata;
        String target = testo;
        while (mqr.find()) {
            log.debug("parso i qrcode");
            inizio = testo.substring(mqr.start(), mqr.end());
            trovata = testo.substring(mqr.start() + 4, mqr.end() - 2);

            String website = Parser.checkWeb(trovata);

            if (website != null) {
                String qr_name = QRgenerator.create_qr(request, website);
                String attributo_pop = "\"<img src='" + request.getContextPath() + "/resource/qrcode/" + qr_name + "' />\"";
                target = target.replace(inizio, "<a href='" + website + "' target='_blank' class='qrpopup' rel='popover' data-content=" + attributo_pop + " >" + website + "</a>");
            } else if (fc.isFile(trovata, crew_id_string)) {
                String link = request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/resource/files/" + crew_id_string + "/" + trovata;
                String qr_name = QRgenerator.create_qr(request, link);
                String attributo_pop = "\"<img src='" + request.getContextPath() + "/resource/qrcode/" + qr_name + "' />\"";
                target = target.replace(inizio, "<a href='" + request.getContextPath() + "/resource/files/" + crew_id_string + "/" + trovata + "' target='_blank'class='qrpopup' rel='popover' data-content=" + attributo_pop + " >" + trovata + "</a>");
            } else {
                target = target.replace(inizio, trovata);
            }
        }

        log.debug("ecco il target " + target);
        //controllo qr code
        testo = target;
        Pattern p = Pattern.compile("\\$\\$(.*?)\\$\\$");
        Matcher m = p.matcher(testo);
        while (m.find()) {
            inizio = testo.substring(m.start(), m.end());
            trovata = testo.substring(m.start() + 2, m.end() - 2);
            log.debug("Sono il primo parser e ho trovato: " + inizio);
            String website = Parser.checkWeb(trovata);

            if (website != null) {
                target = target.replace(inizio, "<a href='" + website + "' target='_blank'>" + trovata + "</a>");
            } else if (fc.isFile(trovata, crew_id_string)) {
                target = target.replace(inizio, "<a href='" + request.getContextPath() + "/resource/files/" + crew_id_string + "/" + trovata + "' target='_blank'> " + trovata + "</a>");
            } else {
                target = target.replace(inizio, trovata);
            }

        }
        return target;
    }

    private static String checkWeb(String trovata) {
        if (trovata != null) {
            int dotOccurences = trovata.length() - trovata.replace(".", "").length();
            if (trovata.length() >= 12 && trovata.substring(0, 7).equals("http://") && dotOccurences >= 1) {
                return trovata;
            } else if (trovata.length() >= 9 && trovata.substring(0, 4).equals("www.") && dotOccurences >= 2) {
                return "http://" + trovata;
            }
        }
        return null;
    }
    
    
    public static ArrayList<Post> censura_mail(ArrayList<Post> posts) {
        log.debug("Sono nel parser!");
        Pattern p = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        for(int i=0; i<posts.size(); i++){
            log.debug("Analizzo il post # " + i);
            String text = posts.get(i).getText();
            Matcher m = p.matcher(text);
            while(m.find()){
                String email = text.substring(m.start(), m.end());
                log.debug("Ho trovato una mail: " + email);
                email = email.substring(email.indexOf('@')+1);
                String replacer = new String(new char[email.length()]).replace('\0', 'X');
                text = text.replace(email, replacer);
            }
            posts.get(i).setText(text);
        }
        return posts;
    }
    
}
