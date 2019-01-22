package net.dopedealers.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class UtilMath
{
    public static Random random = new Random();

    public static double round(double value, int places)
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double trim(int degree, double d) {
        String format = "#.#";

        for (int i = 1; i < degree; i++) {
            format = format + "#";
        }
        DecimalFormat twoDForm = new DecimalFormat(format);
        return Double.valueOf(twoDForm.format(d)).doubleValue();
    }

    public static int r(int i)
    {
        return random.nextInt(i);
    }

    public static double abs(double a) {
        return a <= 0.0D ? 0.0D - a : a;
    }

    public static String ArrayToString(String[] list) {
        String string = "";
        String[] arrayOfString = list; int j = list.length; for (int i = 0; i < j; i++) { String key = arrayOfString[i];
            string = string + key + ","; }
        if (string.length() != 0) {
            return string.substring(0, string.length() - 1);
        }
        return null;
    }

    public static String ArrayToString(List<String> list) {
        String string = "";
        for (String key : list)
            string = string + key + ",";
        if (string.length() != 0) {
            return string.substring(0, string.length() - 1);
        }
        return null;
    }

    public static String[] StringToArray(String string, String split) {
        return string.split(split);
    }

    public static double offset2d(Entity a, Entity b) {
        return offset2d(a.getLocation().toVector(), b.getLocation().toVector());
    }

    public static double offset2d(Location a, Location b) {
        return offset2d(a.toVector(), b.toVector());
    }

    public static double offset2d(Vector a, Vector b) {
        a.setY(0);
        b.setY(0);
        return a.subtract(b).length();
    }

    public static double offset(Entity a, Entity b) {
        return offset(a.getLocation().toVector(), b.getLocation().toVector());
    }

    public static double offset(Location a, Location b) {
        return offset(a.toVector(), b.toVector());
    }

    public static double offset(Vector a, Vector b) {
        return a.subtract(b).length();
    }

    public static Vector getHorizontalVector(Vector v) {
        v.setY(0);
        return v;
    }

    public static Vector getVerticalVector(Vector v) {
        v.setX(0);
        v.setZ(0);
        return v;
    }

    public static String serializeLocation(Location location) {
        int X = (int)location.getX();
        int Y = (int)location.getY();
        int Z = (int)location.getZ();
        int P = (int)location.getPitch();
        int Yaw = (int)location.getYaw();
        return new String(location.getWorld().getName() + "," + X + "," + Y + "," + Z + "," + P + "," + Yaw);
    }

    public static Location deserializeLocation(String string) {
        String[] parts = string.split(",");
        World world = Bukkit.getServer().getWorld(parts[0]);
        Double LX = Double.valueOf(Double.parseDouble(parts[1]));
        Double LY = Double.valueOf(Double.parseDouble(parts[2]));
        Double LZ = Double.valueOf(Double.parseDouble(parts[3]));
        Float P = Float.valueOf(Float.parseFloat(parts[4]));
        Float Y = Float.valueOf(Float.parseFloat(parts[5]));
        Location result = new Location(world, LX.doubleValue(), LY.doubleValue(), LZ.doubleValue());
        result.setPitch(P.floatValue());
        result.setYaw(Y.floatValue());
        return result;
    }

    public static long averageLong(List<Long> list) {
        long add = 0L;
        Long listlist;
        for (Iterator localIterator = list.iterator(); localIterator.hasNext(); add += listlist.longValue()) listlist = (Long)localIterator.next();

        return add / list.size();
    }

    public static double averageDouble(List<Double> list) {
        Double add = Double.valueOf(0.0D);
        Double listlist;
        for (Iterator localIterator = list.iterator(); localIterator.hasNext(); add = Double.valueOf(add.doubleValue() + listlist.doubleValue())) listlist = (Double)localIterator.next();

        return add.doubleValue() / list.size();
    }
}