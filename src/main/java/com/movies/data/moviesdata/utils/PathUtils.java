package com.movies.data.moviesdata.utils;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class PathUtils {

    public static String MOVIEDATA_HOME;
    public static String CSV_HOME;

    static {
        MOVIEDATA_HOME = System.getProperty("user.dir");
        CSV_HOME = new File(MOVIEDATA_HOME).getAbsolutePath() + File.separator + "csv";
    }
}
