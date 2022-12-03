package com.movie.web.plugins.feature;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class FeatureAnalyse {
    public static float[] str2Array(String s){
        String[] str_array = s.split(",");
        float[] feature = new float[str_array.length];
        for (int i = 0; i < str_array.length; i++) {
            feature[i] = Float.parseFloat(str_array[i]);
        }
        return feature;
    }
    
    public static String array2Str(float[] f){
        NumberFormat formatter = new DecimalFormat("0.000000");
        String s = "";
        for (float item :
                f) {
            s += "," + formatter.format(item);
        }
        return s.substring(1);
    }

    public static float similarity(String s1, String s2){
        float[] f1, f2;
        f1 = str2Array(s1);
        f2 = str2Array(s2);
        return similarity(f1, f2);
    }

    public static float similarity(float[] f1, float[] f2){
        float sum=0f, sum1=0f, sum2=0f;
        for (int i = 0; i < f1.length; i++) {
            sum += f1[i] * f2[i] * f1[i] * f2[i];
            sum1 += f1[i] * f1[i];
            sum2 += f2[i] * f2[i];
        }
        float temp = sum1 * sum2;
        if (temp == 0) {
            return 0;
        }
        return sum / (sum1 * sum2);
    }

    public static String addFeature(String s1, String s2) {
        float[] f1=str2Array(s1), f2=str2Array(s2);
        for (int i = 0; i < f1.length; i++) {
            f1[i] += f2[i];
        }
        return array2Str(f1);
    }

    public static String addFeature(String s1, String s2, float w1, float w2) {
        float[] f1=str2Array(s1), f2=str2Array(s2);
        for (int i = 0; i < f1.length; i++) {
            f1[i] = f1[i] * w1 + f2[i] * w2;
        }
        return array2Str(f1);
    }
}
