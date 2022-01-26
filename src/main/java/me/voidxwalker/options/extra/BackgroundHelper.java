package me.voidxwalker.options.extra;

public class BackgroundHelper {
    public static class ColorMixer {
        public static int getArgb(int alpha, int red, int green, int blue) { return alpha << 24 | red << 16 | green << 8 | blue; }
    }
}
