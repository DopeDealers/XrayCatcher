package net.dopedealers.util;

import net.md_5.bungee.api.ChatColor;

public class C
{
    public static String Head;
    public static String Scramble;
    public static String Bold;
    public static String Strike;
    public static String Line;
    public static String Italics;
    public static String Reset;
    public static String Aqua;
    public static String Black;
    public static String Blue;
    public static String DAqua;
    public static String DBlue;
    public static String DGray;
    public static String DGreen;
    public static String DPurple;
    public static String DRed;
    public static String Gold;
    public static String Gray;
    public static String Green;
    public static String Purple;
    public static String Red;
    public static String White;
    public static String Yellow;
    public static String Split;

    static {
        C.Head = ChatColor.AQUA.toString();
        C.Scramble = ChatColor.MAGIC.toString();
        C.Bold = ChatColor.BOLD.toString();
        C.Strike = ChatColor.STRIKETHROUGH.toString();
        C.Line = ChatColor.UNDERLINE.toString();
        C.Italics = ChatColor.ITALIC.toString();
        C.Reset = ChatColor.WHITE.toString();
        C.Aqua = ChatColor.AQUA.toString();
        C.Black = ChatColor.BLACK.toString();
        C.Blue = ChatColor.BLUE.toString();
        C.DAqua = ChatColor.DARK_AQUA.toString();
        C.DBlue = ChatColor.DARK_BLUE.toString();
        C.DGray = ChatColor.DARK_GRAY.toString();
        C.DGreen = ChatColor.DARK_GREEN.toString();
        C.DPurple = ChatColor.DARK_PURPLE.toString();
        C.Green = ChatColor.GREEN.toString();
        C.DRed = ChatColor.DARK_RED.toString();
        C.Gold = ChatColor.GOLD.toString();
        C.Gray = ChatColor.GRAY.toString();
        C.Purple = ChatColor.LIGHT_PURPLE.toString();
        C.Red = ChatColor.RED.toString();
        C.White = ChatColor.WHITE.toString();
        C.Yellow = ChatColor.YELLOW.toString();
        C.Split = "?";
    }

    public static String strip(final String Text) {
        return ChatColor.stripColor(Text);
    }
}
