package com.payAm.core.file;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/10 - 01:57 PM
 */
public enum FileType {
    WORD("application/msword"),
    DOC("application/msword"),
    DOCX("application/msword"),
    RTF("application/rtf"),
    EXCEL("application/vnd/ms/excel"),
    XLS("application/vnd/ms/excel"),
    XLSX("application/vnd/ms/excel"),
    POWERPOINT("application/vnd/ms/powerpoint"),
    PPT("application/vnd/ms/powerpoint"),
    PPTX("application/vnd/ms/powerpoint"),
    ODT("application/vnd/oasis/opendocument/text"),
    ODS("application/vnd/oasis/opendocument/spreadsheet"),
    ODP("application/vnd/oasis/opendocument/presentation"),
    PDF("application/pdf"),
    TXT("text/plain"),
    HTML("text/html"),
    HTM("text/html"),
    JPEG("image/jpeg"),
    JPG("image/jpeg"),
    PNG("image/png"),
    GIF("image/gif"),
    BMP("image/bmp"),
    CSV(""),
    XML("application/xml");

    private String mimeType;

    private FileType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public static FileType extensionToType(String extension, String mime) {
        for (FileType type : FileType.values()) {
            if (extension != null && (extension.equalsIgnoreCase(type.toString()))) {
                return type;
            }
        }
        for (FileType type : FileType.values()) {
            if (mime != null && mime.toLowerCase().contains(type.toString().toLowerCase())) {
                return type;
            }
        }
        return null;
    }

    public static List<FileType> getConvertibleTypesToPdf() {
        ArrayList<FileType> typesList = new ArrayList<>();
        Collections.addAll(typesList, new FileType[] { WORD, DOC, DOCX, EXCEL, XLS, XLSX, POWERPOINT, PPT, PPTX, RTF, ODT, ODS, ODP });
        return typesList;
    }

    public static List<FileType> getImageTypes() {
        ArrayList<FileType> typesList = new ArrayList<>();
        Collections.addAll(typesList, new FileType[] { JPEG, JPG, PNG, GIF, BMP });
        return typesList;
    }

    public static List<FileType> getAllOpenOfficeConvertableTypes() {
        ArrayList<FileType> typesList = new ArrayList<>();
        Collections.addAll(typesList, new FileType[] { WORD, DOC, DOCX, EXCEL, XLS, XLSX, RTF, ODT, ODS, ODP });
        return typesList;
    }

    public static FileType valueOfOrNull(String valueAsString) {
        try {
            return valueOf(valueAsString.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
