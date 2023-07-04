package com.project.converter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.project.model.ExcelRecord;

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

public class XmlWriter {

    public void writeXml(List<ExcelRecord> fattoriRipartizione, String filePath) {

        try {
            Element root = new Element("Periodo");

            if (!fattoriRipartizione.isEmpty()) {
                ExcelRecord firstRecord = fattoriRipartizione.get(0);
                root.addAttribute(new nu.xom.Attribute("MeseRif", String.valueOf(firstRecord.getMeseRif())));
                root.addAttribute(new nu.xom.Attribute("AnnoRif", String.valueOf(firstRecord.getAnnoRif())));
            }

            Element uvam = null;
            String uvamCodice = "";
            Element up_uc = null;
            String up_ucCodice = "";

            for (ExcelRecord record : fattoriRipartizione) {

                if (!uvamCodice.equals(record.getServizio())) {
                    uvam = new Element("UVAM");
                    uvamCodice = record.getServizio();
                    uvam.addAttribute(new nu.xom.Attribute("CODICE", uvamCodice));
                    root.appendChild(uvam);
                    up_ucCodice = ""; // reset the UP_UC code when changing UVAM
                }

                if (!up_ucCodice.equals(record.getCodice())) {
                    up_uc = new Element("UP_UC");
                    up_ucCodice = record.getCodice();
                    up_uc.addAttribute(new nu.xom.Attribute("CODICE", up_ucCodice));
                    uvam.appendChild(up_uc);
                }

                Element giorno = new Element("Giorno");
                giorno.addAttribute(new nu.xom.Attribute("ID", String.valueOf(record.getFirstID())));
                // Repeat the pattern for all quarter hours
                giorno.addAttribute(new nu.xom.Attribute("H1_Q1", String.valueOf(record.getH1_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H1_Q2", String.valueOf(record.getH1_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H1_Q3", String.valueOf(record.getH1_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H1_Q4", String.valueOf(record.getH1_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H2_Q1", String.valueOf(record.getH2_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H2_Q2", String.valueOf(record.getH2_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H2_Q3", String.valueOf(record.getH2_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H2_Q4", String.valueOf(record.getH2_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H3_Q1", String.valueOf(record.getH3_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H3_Q2", String.valueOf(record.getH3_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H3_Q3", String.valueOf(record.getH3_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H3_Q4", String.valueOf(record.getH3_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H4_Q1", String.valueOf(record.getH4_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H4_Q2", String.valueOf(record.getH4_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H4_Q3", String.valueOf(record.getH4_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H4_Q4", String.valueOf(record.getH4_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H5_Q1", String.valueOf(record.getH5_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H5_Q2", String.valueOf(record.getH5_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H5_Q3", String.valueOf(record.getH5_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H5_Q4", String.valueOf(record.getH5_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H9_Q1", String.valueOf(record.getH9_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H9_Q2", String.valueOf(record.getH9_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H9_Q3", String.valueOf(record.getH9_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H9_Q4", String.valueOf(record.getH9_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H7_Q1", String.valueOf(record.getH7_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H7_Q2", String.valueOf(record.getH7_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H7_Q3", String.valueOf(record.getH7_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H7_Q4", String.valueOf(record.getH7_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H8_Q1", String.valueOf(record.getH8_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H8_Q2", String.valueOf(record.getH8_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H8_Q3", String.valueOf(record.getH8_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H8_Q4", String.valueOf(record.getH8_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H9_Q1", String.valueOf(record.getH9_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H9_Q2", String.valueOf(record.getH9_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H9_Q3", String.valueOf(record.getH9_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H9_Q4", String.valueOf(record.getH9_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H10_Q1", String.valueOf(record.getH10_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H10_Q2", String.valueOf(record.getH10_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H10_Q3", String.valueOf(record.getH10_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H10_Q4", String.valueOf(record.getH10_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H11_Q1", String.valueOf(record.getH11_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H11_Q2", String.valueOf(record.getH11_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H11_Q3", String.valueOf(record.getH11_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H11_Q4", String.valueOf(record.getH11_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H12_Q1", String.valueOf(record.getH12_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H12_Q2", String.valueOf(record.getH12_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H12_Q3", String.valueOf(record.getH12_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H12_Q4", String.valueOf(record.getH12_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H13_Q1", String.valueOf(record.getH13_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H13_Q2", String.valueOf(record.getH13_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H13_Q3", String.valueOf(record.getH13_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H13_Q4", String.valueOf(record.getH13_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H14_Q1", String.valueOf(record.getH14_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H14_Q2", String.valueOf(record.getH14_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H14_Q3", String.valueOf(record.getH14_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H14_Q4", String.valueOf(record.getH14_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H15_Q1", String.valueOf(record.getH15_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H15_Q2", String.valueOf(record.getH15_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H15_Q3", String.valueOf(record.getH15_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H15_Q4", String.valueOf(record.getH15_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H16_Q1", String.valueOf(record.getH16_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H16_Q2", String.valueOf(record.getH16_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H16_Q3", String.valueOf(record.getH16_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H16_Q4", String.valueOf(record.getH16_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H17_Q1", String.valueOf(record.getH17_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H17_Q2", String.valueOf(record.getH17_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H17_Q3", String.valueOf(record.getH17_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H17_Q4", String.valueOf(record.getH17_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H18_Q1", String.valueOf(record.getH18_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H18_Q2", String.valueOf(record.getH18_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H18_Q3", String.valueOf(record.getH18_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H18_Q4", String.valueOf(record.getH18_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H19_Q1", String.valueOf(record.getH19_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H19_Q2", String.valueOf(record.getH19_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H19_Q3", String.valueOf(record.getH19_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H19_Q4", String.valueOf(record.getH19_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H20_Q1", String.valueOf(record.getH20_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H20_Q2", String.valueOf(record.getH20_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H20_Q3", String.valueOf(record.getH20_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H20_Q4", String.valueOf(record.getH20_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H21_Q1", String.valueOf(record.getH21_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H21_Q2", String.valueOf(record.getH21_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H21_Q3", String.valueOf(record.getH21_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H21_Q4", String.valueOf(record.getH21_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H22_Q1", String.valueOf(record.getH22_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H22_Q2", String.valueOf(record.getH22_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H22_Q3", String.valueOf(record.getH22_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H22_Q4", String.valueOf(record.getH22_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H23_Q1", String.valueOf(record.getH23_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H23_Q2", String.valueOf(record.getH23_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H23_Q3", String.valueOf(record.getH23_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H23_Q4", String.valueOf(record.getH23_Q4())));
                giorno.addAttribute(new nu.xom.Attribute("H24_Q1", String.valueOf(record.getH24_Q1())));
                giorno.addAttribute(new nu.xom.Attribute("H24_Q2", String.valueOf(record.getH24_Q2())));
                giorno.addAttribute(new nu.xom.Attribute("H24_Q3", String.valueOf(record.getH24_Q3())));
                giorno.addAttribute(new nu.xom.Attribute("H24_Q4", String.valueOf(record.getH24_Q4())));

                up_uc.appendChild(giorno);
            }

            Document doc = new Document(root);

            try (OutputStream os = new FileOutputStream(filePath)) {
                Serializer serializer = new Serializer(os, StandardCharsets.UTF_8.name());
                serializer.setIndent(4);
                serializer.setMaxLength(64);
                serializer.write(doc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
