package xyz.xianyu.util.math;

import net.minecraft.block.Block;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.*;

public class RotationUtil
{
    static Minecraft mc;
    
    static {
        RotationUtil.mc = Minecraft.getMinecraft();
    }
    
    public static boolean canEntityBeSeen(final EntityItem e) {
        final Vec3 vec1 = new Vec3(RotationUtil.mc.thePlayer.posX, RotationUtil.mc.thePlayer.posY + RotationUtil.mc.thePlayer.getEyeHeight(), RotationUtil.mc.thePlayer.posZ);
        final AxisAlignedBB box = e.getEntityBoundingBox();
        Vec3 vec2 = new Vec3(e.posX, e.posY + e.getEyeHeight() / 1.32f, e.posZ);
        final double minx = e.posX - 0.25;
        final double maxx = e.posX + 0.25;
        final double miny = e.posY;
        final double maxy = e.posY + Math.abs(e.posY - box.maxY);
        final double minz = e.posZ - 0.25;
        final double maxz = e.posZ + 0.25;
        boolean see = RotationUtil.mc.theWorld.rayTraceBlocks(vec1, vec2) == null;
        if (see) {
            return true;
        }
        vec2 = new Vec3(maxx, miny, minz);
        see = (RotationUtil.mc.theWorld.rayTraceBlocks(vec1, vec2) == null);
        if (see) {
            return true;
        }
        vec2 = new Vec3(minx, miny, minz);
        see = (RotationUtil.mc.theWorld.rayTraceBlocks(vec1, vec2) == null);
        if (see) {
            return true;
        }
        vec2 = new Vec3(minx, miny, maxz);
        see = (RotationUtil.mc.theWorld.rayTraceBlocks(vec1, vec2) == null);
        if (see) {
            return true;
        }
        vec2 = new Vec3(maxx, miny, maxz);
        see = (RotationUtil.mc.theWorld.rayTraceBlocks(vec1, vec2) == null);
        if (see) {
            return true;
        }
        vec2 = new Vec3(maxx, maxy, minz);
        see = (RotationUtil.mc.theWorld.rayTraceBlocks(vec1, vec2) == null);
        if (see) {
            return true;
        }
        vec2 = new Vec3(minx, maxy, minz);
        see = (RotationUtil.mc.theWorld.rayTraceBlocks(vec1, vec2) == null);
        if (see) {
            return true;
        }
        vec2 = new Vec3(minx, maxy, maxz - 0.1);
        see = (RotationUtil.mc.theWorld.rayTraceBlocks(vec1, vec2) == null);
        if (see) {
            return true;
        }
        vec2 = new Vec3(maxx, maxy, maxz);
        see = (RotationUtil.mc.theWorld.rayTraceBlocks(vec1, vec2) == null);
        return see;
    }
    
    public static float getYawChangeToEntity(final Entity entity, final EntityItem entity2) {
        final Minecraft mc = Minecraft.getMinecraft();
        final double deltaX = entity.posX - entity2.posX;
        final double deltaZ = entity.posZ - entity2.posZ;
        final double yawToEntity = (deltaZ < 0.0 && deltaX < 0.0) ? (90.0 + Math.toDegrees(Math.atan(deltaZ / deltaX))) : ((deltaZ < 0.0 && deltaX > 0.0) ? (-90.0 + Math.toDegrees(Math.atan(deltaZ / deltaX))) : Math.toDegrees(-Math.atan(deltaX / deltaZ)));
        return MathHelper.wrapAngleTo180_float(-mc.thePlayer.rotationYaw - (float)yawToEntity);
    }

    public static float getYawChangeToEntity(Entity entity, Entity entity2) {
        Minecraft mc = Minecraft.getMinecraft();
        double deltaX = entity.posX - entity2.posX;
        double deltaZ = entity.posZ - entity2.posZ;
        double yawToEntity = deltaZ < 0.0 && deltaX < 0.0 ? 90.0 + Math.toDegrees(Math.atan(deltaZ / deltaX)) : (deltaZ < 0.0 && deltaX > 0.0 ? -90.0 + Math.toDegrees(Math.atan(deltaZ / deltaX)) : Math.toDegrees(-Math.atan(deltaX / deltaZ)));
        return MathHelper.wrapAngleTo180_float(-mc.thePlayer.rotationYaw - (float)yawToEntity);
    }
    
    public static float getYawChangeGiven(final double posX, final double posZ, final float yaw) {
        final double deltaX = posX - Minecraft.getMinecraft().thePlayer.posX;
        final double deltaZ = posZ - Minecraft.getMinecraft().thePlayer.posZ;
        double yawToEntity;
        if (deltaZ < 0.0 && deltaX < 0.0) {
            yawToEntity = 90.0 + Math.toDegrees(Math.atan(deltaZ / deltaX));
        }
        else if (deltaZ < 0.0 && deltaX > 0.0) {
            yawToEntity = -90.0 + Math.toDegrees(Math.atan(deltaZ / deltaX));
        }
        else {
            yawToEntity = Math.toDegrees(-Math.atan(deltaX / deltaZ));
        }
        return MathHelper.wrapAngleTo180_float(-(yaw - (float)yawToEntity));
    }
    
    public static int wrapAngleToDirection(final float yaw, final int zones) {
        int angle = (int)(yaw + 360 / (2 * zones) + 0.5) % 360;
        if (angle < 0) {
            angle += 360;
        }
        return angle / (360 / zones);
    }

    public static float[] getRotationsForAura(Entity entity, double maxRange) {
        double diffY;
        if (entity == null) {
            System.out.println("Fuck you ! Entity is nul!");
            return null;
        }
        Minecraft.getMinecraft();
        double diffX = entity.posX - mc.thePlayer.posX;
        Minecraft.getMinecraft();
        double diffZ = entity.posZ - mc.thePlayer.posZ;
        Location BestPos = new Location(entity.posX, entity.posY, entity.posZ);
        Minecraft.getMinecraft();
        Location myEyePos = new Location(mc.thePlayer.posX, mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight(), mc.thePlayer.posZ);
        for (diffY = entity.boundingBox.minY + 0.7; diffY < entity.boundingBox.maxY - 0.1; diffY += 0.1) {
            if (myEyePos.distanceTo(new Location(entity.posX, diffY, entity.posZ)) >= myEyePos.distanceTo(BestPos)) continue;
            BestPos = new Location(entity.posX, diffY, entity.posZ);
        }
        if (myEyePos.distanceTo(BestPos) >= maxRange) {
            return null;
        }
        diffY = BestPos.getY() - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / 3.141592653589793) - 90.0f;
        float pitch = (float)(- Math.atan2(diffY, dist) * 180.0 / 3.141592653589793);
        return new float[]{yaw, pitch};
    }


    public static float[] getRotationFromPosition(double x, double z, double y) {
        Minecraft.getMinecraft();
        double xDiff = x - mc.thePlayer.posX;
        Minecraft.getMinecraft();
        double zDiff = z - mc.thePlayer.posZ;
        Minecraft.getMinecraft();
        double yDiff = y - mc.thePlayer.posY - 1.2D;
        double dist = (double)MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
        float yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float)(-(Math.atan2(yDiff, dist) * 180.0D / 3.141592653589793D));
        return new float[]{yaw, pitch};
    }

    public static class Location {
        private double x;
        private double y;
        private double z;
        private float yaw;
        private float pitch;

        public Location(double x, double y, double z, float yaw, float pitch) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = yaw;
            this.pitch = pitch;
        }

        public Location(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.yaw = 0.0F;
            this.pitch = 0.0F;
        }

        public Location(BlockPos pos) {
            this.x = (double)pos.getX();
            this.y = (double)pos.getY();
            this.z = (double)pos.getZ();
            this.yaw = 0.0F;
            this.pitch = 0.0F;
        }

        public Location(int x, int y, int z) {
            this.x = (double)x;
            this.y = (double)y;
            this.z = (double)z;
            this.yaw = 0.0F;
            this.pitch = 0.0F;
        }

        public Location(EntityLivingBase entity) {
            this.x = entity.posX;
            this.y = entity.posY;
            this.z = entity.posZ;
            this.yaw = 0.0f;
            this.pitch = 0.0f;
        }

        public Location(Entity entity) {
            this.x = entity.posX;
            this.y = entity.posY;
            this.z = entity.posZ;
            this.yaw = 0.0f;
            this.pitch = 0.0f;
        }
        public Location add(int x, int y, int z) {
            this.x += (double)x;
            this.y += (double)y;
            this.z += (double)z;
            return this;
        }

        public Location add(double x, double y, double z) {
            this.x += x;
            this.y += y;
            this.z += z;
            return this;
        }

        public Location subtract(int x, int y, int z) {
            this.x -= (double)x;
            this.y -= (double)y;
            this.z -= (double)z;
            return this;
        }

        public Location subtract(double x, double y, double z) {
            this.x -= x;
            this.y -= y;
            this.z -= z;
            return this;
        }

        public Block getBlock() {
            return Minecraft.getMinecraft().theWorld.getBlockState(this.toBlockPos()).getBlock();
        }

        public double getX() {
            return this.x;
        }

        public Location setX(double x) {
            this.x = x;
            return this;
        }

        public double getY() {
            return this.y;
        }

        public Location setY(double y) {
            this.y = y;
            return this;
        }

        public double getZ() {
            return this.z;
        }

        public Location setZ(double z) {
            this.z = z;
            return this;
        }

        public float getYaw() {
            return this.yaw;
        }

        public Location setYaw(float yaw) {
            this.yaw = yaw;
            return this;
        }

        public float getPitch() {
            return this.pitch;
        }

        public Location setPitch(float pitch) {
            this.pitch = pitch;
            return this;
        }

        public static Location fromBlockPos(BlockPos blockPos) {
            return new Location(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        }

        public BlockPos toBlockPos() {
            return new BlockPos(this.getX(), this.getY(), this.getZ());
        }

        public double distanceTo(Location loc) {
            double dx = loc.x - this.x;
            double dz = loc.z - this.z;
            double dy = loc.y - this.y;
            return Math.sqrt(dx * dx + dy * dy + dz * dz);
        }

        public double distanceToY(Location loc) {
            double dy = loc.y - this.y;
            return Math.sqrt(dy * dy);
        }
    }
}
