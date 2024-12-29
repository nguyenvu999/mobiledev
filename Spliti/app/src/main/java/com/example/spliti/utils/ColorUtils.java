package com.example.spliti.utils;

import android.graphics.Color;

public class ColorUtils {
    public static int hexToInt(String hexCode) {
        try {
            return Color.parseColor(hexCode);
        } catch (IllegalArgumentException e) {
            return Color.WHITE;
        }
    }
}
