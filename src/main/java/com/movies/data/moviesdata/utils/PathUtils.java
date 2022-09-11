package com.movies.data.moviesdata.utils;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class PathUtils {

    public static String MOVIEDATA_HOME;
    public static String MOVIEDATA_RESOURCES;
    public static String CSV_HOME_PROD;
    public static String CSV_HOME_DEV;

    static {
        MOVIEDATA_HOME = System.getProperty("user.dir");
        MOVIEDATA_RESOURCES = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        CSV_HOME_PROD = new File(MOVIEDATA_HOME).getAbsolutePath() + File.separator + "csv";
        CSV_HOME_DEV = new File(MOVIEDATA_RESOURCES).getAbsolutePath() + File.separator + "csv";
    }
}
