package xyz.xianyu.util.math;

import java.util.*;
import net.minecraft.util.*;
import java.util.concurrent.*;
import xyz.xianyu.util.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;

public class MathUtil
{
    public static Random random;
    
    static {
        MathUtil.random = new Random();
    }
    
    public static double toDecimalLength(final double in, final int places) {
        return Double.parseDouble(String.format("%." + places + "f", in));
    }
    
    public static double round(final double in, int places) {
        places = (int)MathHelper.clamp_double(places, 0.0, 2.147483647E9);
        return Double.parseDouble(String.format("%." + places + "f", in));
    }
    
    public static boolean parsable(final String s, final byte type) {
        try {
            switch (type) {
                case 0: {
                    Short.parseShort(s);
                    break;
                }
                case 1: {
                    Byte.parseByte(s);
                    break;
                }
                case 2: {
                    Integer.parseInt(s);
                    break;
                }
                case 3: {
                    Float.parseFloat(s);
                    break;
                }
                case 4: {
                    Double.parseDouble(s);
                    break;
                }
                case 5: {
                    Long.parseLong(s);
                    break;
                }
            }
        }
        catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public static double square(final double in) {
        return in * in;
    }
    
    public static double randomDouble(final double min, final double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
    
    
 
    
    public static double clamp(final double value, final double minimum, final double maximum) {
        return (value > maximum) ? maximum : ((value < minimum) ? minimum : value);
    }

    public static double getBaseMovementSpeed() {
        double baseSpeed = 0.2873;
        if (Helper.mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            int amplifier = Helper.mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier();
            baseSpeed *= 1.0 + 0.2 * (double)(amplifier + 1);
        }
        return baseSpeed;
    }

    public static class NumberType
    {
        public static final byte SHORT = 0;
        public static final byte BYTE = 1;
        public static final byte INT = 2;
        public static final byte FLOAT = 3;
        public static final byte DOUBLE = 4;
        public static final byte LONG = 5;
        
        public static byte getByType(final Class cls) {
            if (cls == Short.class) {
                return 0;
            }
            if (cls == Byte.class) {
                return 1;
            }
            if (cls == Integer.class) {
                return 2;
            }
            if (cls == Float.class) {
                return 3;
            }
            if (cls == Double.class) {
                return 4;
            }
            if (cls == Long.class) {
                return 5;
            }
            return -1;
        }
    }
}
