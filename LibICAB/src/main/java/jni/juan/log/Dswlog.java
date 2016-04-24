package jni.juan.log;

import jni.juan.natives.CyLalib;

/**
 * Created by chen on 16-4-24.
 */

public class Dswlog {
    public static final String AppTag = "ICAB";
    public static final int lever = 0;
    public String classTag = "Unknown";

    public static void i (String classname, int lever ) {

        CyLalib.nativesmartLog(lever,classname);
    }
}
