package net.minecraft.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import net.optifine.DynamicLights;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;
import xyz.xianyu.module.ModuleManager;
import xyz.xianyu.module.modules.render.Animations;

public class ItemRenderer
{
    private static final ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
    private static final ResourceLocation RES_UNDERWATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");

    /** A reference to the Minecraft object. */
    private final Minecraft mc;
    private ItemStack itemToRender;

    /**
     * How far the current item has been equipped (0 disequipped and 1 fully up)
     */
    private float equippedProgress;
    private float prevEquippedProgress;
    private final RenderManager renderManager;
    private final RenderItem itemRenderer;

    /** The index of the currently held item (0-8, or -1 if not yet updated) */
    private int equippedItemSlot = -1;

    private int ticks;

    public ItemRenderer(Minecraft mcIn)
    {
        this.mc = mcIn;
        this.renderManager = mcIn.getRenderManager();
        this.itemRenderer = mcIn.getRenderItem();
    }

    public void renderItem(EntityLivingBase entityIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform)
    {
        if (heldStack != null)
        {
            Item item = heldStack.getItem();
            Block block = Block.getBlockFromItem(item);
            GlStateManager.pushMatrix();

            if (this.itemRenderer.shouldRenderItemIn3D(heldStack))
            {
                GlStateManager.scale(2.0F, 2.0F, 2.0F);

                if (this.isBlockTranslucent(block) && (!Config.isShaders() || !Shaders.renderItemKeepDepthMask))
                {
                    GlStateManager.depthMask(false);
                }
            }

            this.itemRenderer.renderItemModelForEntity(heldStack, entityIn, transform);

            if (this.isBlockTranslucent(block))
            {
                GlStateManager.depthMask(true);
            }

            GlStateManager.popMatrix();
        }
    }

    /**
     * Returns true if given block is translucent
     */
    private boolean isBlockTranslucent(Block blockIn)
    {
        return blockIn != null && blockIn.getBlockLayer() == EnumWorldBlockLayer.TRANSLUCENT;
    }

    private void func_178101_a(float angle, float p_178101_2_)
    {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(angle, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(p_178101_2_, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    private void func_178109_a(AbstractClientPlayer clientPlayer)
    {
        int i = this.mc.theWorld.getCombinedLight(new BlockPos(clientPlayer.posX, clientPlayer.posY + (double)clientPlayer.getEyeHeight(), clientPlayer.posZ), 0);

        if (Config.isDynamicLights())
        {
            i = DynamicLights.getCombinedLight(this.mc.getRenderViewEntity(), i);
        }

        float f = (float)(i & 65535);
        float f1 = (float)(i >> 16);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f, f1);
    }

    private void func_178110_a(EntityPlayerSP entityplayerspIn, float partialTicks)
    {
        float f = entityplayerspIn.prevRenderArmPitch + (entityplayerspIn.renderArmPitch - entityplayerspIn.prevRenderArmPitch) * partialTicks;
        float f1 = entityplayerspIn.prevRenderArmYaw + (entityplayerspIn.renderArmYaw - entityplayerspIn.prevRenderArmYaw) * partialTicks;
        GlStateManager.rotate((entityplayerspIn.rotationPitch - f) * 0.1F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate((entityplayerspIn.rotationYaw - f1) * 0.1F, 0.0F, 1.0F, 0.0F);
    }

    private float func_178100_c(float p_178100_1_)
    {
        float f = 1.0F - p_178100_1_ / 45.0F + 0.1F;
        f = MathHelper.clamp_float(f, 0.0F, 1.0F);
        f = -MathHelper.cos(f * (float)Math.PI) * 0.5F + 0.5F;
        return f;
    }

    private void renderRightArm(RenderPlayer renderPlayerIn)
    {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(54.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(64.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(-62.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(0.25F, -0.85F, 0.75F);
        renderPlayerIn.renderRightArm(this.mc.thePlayer);
        GlStateManager.popMatrix();
    }

    private void renderLeftArm(RenderPlayer renderPlayerIn)
    {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(92.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(41.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.translate(-0.3F, -1.1F, 0.45F);
        renderPlayerIn.renderLeftArm(this.mc.thePlayer);
        GlStateManager.popMatrix();
    }

    private void renderPlayerArms(AbstractClientPlayer clientPlayer)
    {
        this.mc.getTextureManager().bindTexture(clientPlayer.getLocationSkin());
        Render<AbstractClientPlayer> render = this.renderManager.getEntityRenderObject(this.mc.thePlayer);
        RenderPlayer renderplayer = (RenderPlayer)render;

        if (!clientPlayer.isInvisible())
        {
            GlStateManager.disableCull();
            this.renderRightArm(renderplayer);
            this.renderLeftArm(renderplayer);
            GlStateManager.enableCull();
        }
    }

    private void renderItemMap(AbstractClientPlayer clientPlayer, float p_178097_2_, float p_178097_3_, float p_178097_4_)
    {
        float f = -0.4F * MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * (float)Math.PI);
        float f1 = 0.2F * MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * (float)Math.PI * 2.0F);
        float f2 = -0.2F * MathHelper.sin(p_178097_4_ * (float)Math.PI);
        GlStateManager.translate(f, f1, f2);
        float f3 = this.func_178100_c(p_178097_2_);
        GlStateManager.translate(0.0F, 0.04F, -0.72F);
        GlStateManager.translate(0.0F, p_178097_3_ * -1.2F, 0.0F);
        GlStateManager.translate(0.0F, f3 * -0.5F, 0.0F);
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f3 * -85.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
        this.renderPlayerArms(clientPlayer);
        float f4 = MathHelper.sin(p_178097_4_ * p_178097_4_ * (float)Math.PI);
        float f5 = MathHelper.sin(MathHelper.sqrt_float(p_178097_4_) * (float)Math.PI);
        GlStateManager.rotate(f4 * -20.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f5 * -20.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(f5 * -80.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(0.38F, 0.38F, 0.38F);
        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(0.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(-1.0F, -1.0F, 0.0F);
        GlStateManager.scale(0.015625F, 0.015625F, 0.015625F);
        this.mc.getTextureManager().bindTexture(RES_MAP_BACKGROUND);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GL11.glNormal3f(0.0F, 0.0F, -1.0F);
        worldrenderer.begin(7, DefaultVertexFormats.field_181707_g);
        worldrenderer.pos(-7.0D, 135.0D, 0.0D).tex(0.0D, 1.0D).endVertex();
        worldrenderer.pos(135.0D, 135.0D, 0.0D).tex(1.0D, 1.0D).endVertex();
        worldrenderer.pos(135.0D, -7.0D, 0.0D).tex(1.0D, 0.0D).endVertex();
        worldrenderer.pos(-7.0D, -7.0D, 0.0D).tex(0.0D, 0.0D).endVertex();
        tessellator.draw();
        MapData mapdata = Items.filled_map.getMapData(this.itemToRender, this.mc.theWorld);

        if (mapdata != null)
        {
            this.mc.entityRenderer.getMapItemRenderer().renderMap(mapdata, false);
        }
    }

    private void func_178095_a(AbstractClientPlayer clientPlayer, float p_178095_2_, float p_178095_3_)
    {
        float f = -0.3F * MathHelper.sin(MathHelper.sqrt_float(p_178095_3_) * (float)Math.PI);
        float f1 = 0.4F * MathHelper.sin(MathHelper.sqrt_float(p_178095_3_) * (float)Math.PI * 2.0F);
        float f2 = -0.4F * MathHelper.sin(p_178095_3_ * (float)Math.PI);
        GlStateManager.translate(f, f1, f2);
        GlStateManager.translate(0.64000005F, -0.6F, -0.71999997F);
        GlStateManager.translate(0.0F, p_178095_2_ * -0.6F, 0.0F);
        GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
        float f3 = MathHelper.sin(p_178095_3_ * p_178095_3_ * (float)Math.PI);
        float f4 = MathHelper.sin(MathHelper.sqrt_float(p_178095_3_) * (float)Math.PI);
        GlStateManager.rotate(f4 * 70.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f3 * -20.0F, 0.0F, 0.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(clientPlayer.getLocationSkin());
        GlStateManager.translate(-1.0F, 3.6F, 3.5F);
        GlStateManager.rotate(120.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(5.6F, 0.0F, 0.0F);
        Render<AbstractClientPlayer> render = this.renderManager.getEntityRenderObject(this.mc.thePlayer);
        GlStateManager.disableCull();
        RenderPlayer renderplayer = (RenderPlayer)render;
        renderplayer.renderRightArm(this.mc.thePlayer);
        GlStateManager.enableCull();
    }

    private void func_178105_d(float p_178105_1_)
    {
        float f = -0.4F * MathHelper.sin(MathHelper.sqrt_float(p_178105_1_) * (float)Math.PI);
        float f1 = 0.2F * MathHelper.sin(MathHelper.sqrt_float(p_178105_1_) * (float)Math.PI * 2.0F);
        float f2 = -0.2F * MathHelper.sin(p_178105_1_ * (float)Math.PI);
        GlStateManager.translate(f, f1, f2);
    }

    private void func_178104_a(AbstractClientPlayer clientPlayer, float p_178104_2_)
    {
        float f = (float)clientPlayer.getItemInUseCount() - p_178104_2_ + 1.0F;
        float f1 = f / (float)this.itemToRender.getMaxItemUseDuration();
        float f2 = MathHelper.abs(MathHelper.cos(f / 4.0F * (float)Math.PI) * 0.1F);

        if (f1 >= 0.8F)
        {
            f2 = 0.0F;
        }

        GlStateManager.translate(0.0F, f2, 0.0F);
        float f3 = 1.0F - (float)Math.pow(f1, 27.0D);
        GlStateManager.translate(f3 * 0.6F, f3 * -0.5F, f3 * 0.0F);
        GlStateManager.rotate(f3 * 90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f3 * 10.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f3 * 30.0F, 0.0F, 0.0F, 1.0F);
    }

    /**
     * Performs transformations prior to the rendering of a held item in first person.
     *  
     * @param equipProgress The progress of the animation to equip (raise from out of frame) while switching held items.
     * @param swingProgress The progress of the arm swing animation.
     */
    private void transformFirstPersonItem(float equipProgress, float swingProgress)
    {
        GlStateManager.translate(0.56F, -0.52F, -0.71999997F);
        GlStateManager.translate(0.0F, equipProgress * -0.6F, 0.0F);
        GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
        float f = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
        GlStateManager.rotate(f * -20.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(0.4F, 0.4F, 0.4F);
    }

    private void func_178098_a(float p_178098_1_, AbstractClientPlayer clientPlayer)
    {
        GlStateManager.rotate(-18.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(-12.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-8.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.translate(-0.9F, 0.2F, 0.0F);
        float f = (float)this.itemToRender.getMaxItemUseDuration() - ((float)clientPlayer.getItemInUseCount() - p_178098_1_ + 1.0F);
        float f1 = f / 20.0F;
        f1 = (f1 * f1 + f1 * 2.0F) / 3.0F;

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        if (f1 > 0.1F)
        {
            float f2 = MathHelper.sin((f - 0.1F) * 1.3F);
            float f3 = f1 - 0.1F;
            float f4 = f2 * f3;
            GlStateManager.translate(f4 * 0.0F, f4 * 0.01F, f4 * 0.0F);
        }

        GlStateManager.translate(f1 * 0.0F, f1 * 0.0F, f1 * 0.1F);
        GlStateManager.scale(1.0F, 1.0F, 1.0F + f1 * 0.2F);
    }

    private void func_178103_d()
    {
        GlStateManager.translate(-0.5F, 0.2F, 0.0F);
        GlStateManager.rotate(30.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-80.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(60.0F, 0.0F, 1.0F, 0.0F);
    }

    /**
     * Renders the active item in the player's hand when in first person mode. Args: partialTickTime
     *  
     * @param partialTicks The amount of time passed during the current tick, ranging from 0 to 1.
     */
    public void renderItemInFirstPerson(float partialTicks)
    {
        if (!Config.isShaders() || !Shaders.isSkipRenderHand())
        {
            float f2 = 1.0F - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTicks);
            final EntityPlayerSP abstractclientplayer = this.mc.thePlayer;
            float f3 = abstractclientplayer.getSwingProgress(partialTicks);
            float f4 = abstractclientplayer.prevRotationPitch + (abstractclientplayer.rotationPitch - abstractclientplayer.prevRotationPitch) * partialTicks;
            float f5 = abstractclientplayer.prevRotationYaw + (abstractclientplayer.rotationYaw - abstractclientplayer.prevRotationYaw) * partialTicks;
            this.func_178101_a(f4, f5);
            this.func_178109_a(abstractclientplayer);
            this.func_178110_a(abstractclientplayer, partialTicks);
            GlStateManager.enableRescaleNormal();
            GlStateManager.pushMatrix();
            final float var6 = 1.0f - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTicks);

            final EntityPlayerSP var7 = this.mc.thePlayer;
            final float var8 = var7.getSwingProgress(partialTicks);
            byte Mode = 0;

            if (this.itemToRender != null)
            {
                if (this.itemToRender.getItem() == Items.filled_map)
                {
                    this.renderItemMap(abstractclientplayer, f4, f3, f3);
                }
                else if (abstractclientplayer.getItemInUseCount() > 0)
                {
                    EnumAction enumaction = this.itemToRender.getItemUseAction();

                    switch (enumaction)
                    {
                        case NONE:
                            this.transformFirstPersonItem(f2, f3);
                            break;
                        case EAT:
                        case DRINK:
                            this.func_178104_a(abstractclientplayer, partialTicks);
                            if (ModuleManager.getModuleByClass(Animations.class).isEnabled()) {
                                transformFirstPersonItem(f2, f3);
                            } else {
                                transformFirstPersonItem(f2, 0.0F);
                            }
                            break;

                        case BLOCK:
                            if(!ModuleManager.getModuleByClass(Animations.class).isEnabled())
                            {
                                this.transformFirstPersonItem(f2, 0.0f);
                                this.doBlockTransformations();
                                break;
                            }
                            GlStateManager.translate(Animations.x.getValue(), Animations.y.getValue(), Animations.z.getValue());
                            if(Animations.Modes.getMode().equals("1.7")) {
                                Mode = 1;
                            }
                            if(Animations.Modes.getMode().equals("Remix")) {
                                Mode = 2;
                            }
                            if(Animations.Modes.getMode().equals("Screw")) {
                                Mode = 3;
                            }
                            if(Animations.Modes.getMode().equals("Swang")) {
                                Mode = 4;
                            }
                            if(Animations.Modes.getMode().equals("Swank")) {
                                Mode = 5;
                            }
                            if(Animations.Modes.getMode().equals("Swing")) {
                                Mode = 6;
                            }
                            if(Animations.Modes.getMode().equals("Swong")) {
                                Mode = 7;
                            }
                            if(Animations.Modes.getMode().equals("SwAing")) {
                                Mode = 8;
                            }
                            if(Animations.Modes.getMode().equals("Custom")) {
                                Mode = 9;
                            }
                            if(Animations.Modes.getMode().equals("Gay")) {
                                Mode = 10;
                            }
                            if(Animations.Modes.getMode().equals("Punch")) {
                                Mode = 11;
                            }
                            if(Animations.Modes.getMode().equals("Winter")) {
                                Mode = 12;
                            }
                            if(Animations.Modes.getMode().equals("rotate")) {
                                Mode = 13;
                            }
                            if(Animations.Modes.getMode().equals("Lunar")){
                                Mode = 14;
                            }
                            if(Animations.Modes.getMode().equalsIgnoreCase("Vanilla")){
                                Mode=15;
                            }
                            switch(Mode) {
                                case 1:{
                                    this.genCustom(f2, f3);
                                    this.func_178103_d();
                                    break;
                                }
                                case 2:{
                                    this.transformFirstPersonItem(f2, 0.83f);
                                    this.doBlockTransformations();
                                    final float f6 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.83f);
                                    GlStateManager.translate(-0.5f, 0.2f, 0.2f);
                                    GlStateManager.rotate(-f6 * 0.0f, 0.0f, 0.0f, 0.0f);
                                    GlStateManager.rotate(-f6 * 43.0f, 58.0f, 23.0f, 45.0f);
                                    break;
                                }
                                case 3:{
                                    this.circle(f3);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 4:{
                                    GL11.glTranslated(-0.10000000149011612, 0.15000000596046448, 0.0);
                                    this.func_178096_b(var6 / 2.0f, var8);
                                    final float var9 = MathHelper.sin(MathHelper.sqrt_float(var8) * 3.1415927f);
                                    GlStateManager.rotate(var9 * 30.0f / 2.0f, -var9, -0.0f, 9.0f);
                                    GlStateManager.rotate(var9 * 40.0f, 1.0f, -var9 / 2.0f, -0.0f);
                                    this.doBlockTransformations();
                                    break;
                                }
                                //Swank
                                case 5: {
                                    GL11.glTranslated(-0.10000000149011612, 0.15000000596046448, 0.0);
                                    this.transformFirstPersonItem(f2 / 2.0f, f3);
                                    final float var9 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927f);
                                    GlStateManager.rotate(var9 * 30.0f, -var9, -0.0f, 9.0f);
                                    GlStateManager.rotate(var9 * 40.0f, 1.0f, -var9, -0.0f);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 6:{
                                    GL11.glTranslated(-0.10000000149011612, 0.15000000596046448, 0.0);
                                    GL11.glTranslated(0.10000000149011612, -0.15000000596046448, 0.0);
                                    this.transformFirstPersonItem(var6 / 2.0f, var8);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 7:{
                                    GL11.glTranslated(-0.10000000149011612, 0.15000000596046448, 0.0);
                                    GlStateManager.translate(-0.0f, -0.1f, 0.0f);
                                    this.transformFirstPersonItem(f2 / 2.0f, 0.0f);
                                    final float var9 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927f);
                                    GL11.glRotated(-var9 * 40.0f / 2.0f, var9 / 2.0f, -0.0, 9.0);
                                    GL11.glRotated(-var9 * 30.0f, 1.0, var9 / 2.0f, -0.0);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 8:{
                                    GL11.glTranslated(-0.10000000149011612, 0.15000000596046448, 0.0);
                                    GL11.glTranslated(0.10000000149011612, -0.20000000298023224, 0.0);
                                    this.avatar(f2, f3);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 9:{
                                    this.func_178096_b(f2, 0.0f);
                                    this.doBlockTransformations();
                                    final float var19 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927f);
                                    GlStateManager.rotate(-var19 * 70.0f / 2.0f, -2.0f, -0.0f, 2.0f);
                                    GlStateManager.rotate(-var19 * 70.0f, 1.0f, -0.4f, -0.0f);
                                    break;
                                }
                                case 10:{
                                    this.transformFirstPersonItem(f2, 0.0f);
                                    this.doBlockTransformations();
                                    final float var11 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927f);
                                    GlStateManager.translate(-0.05f, 0.3f, 0.0f);
                                    GlStateManager.rotate(-var11 * 70.0f / 2.0f, -8.0f, -0.0f, 9.0f);
                                    GlStateManager.rotate(-var11 * 70.0f, 1.0f, -0.4f, -0.0f);
                                    break;
                                }
                                case 11:{
                                    this.transformFirstPersonItem(f2, 0.0f);
                                    this.doBlockTransformations();
                                    final float var9 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927f);
                                    GlStateManager.translate(0.1f, 0.2f, 0.3f);
                                    GlStateManager.rotate(-var9 * 30.0f, -5.0f, 0.0f, 9.0f);
                                    GlStateManager.rotate(-var9 * 10.0f, 1.0f, -0.4f, -0.5f);
                                    break;
                                }
                                case 12:{
                                    this.x3IsBlack(-0.3f, f3);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 13:{
                                    this.Random(f3);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 14:{
                                    this.transformFirstPersonItem(0.2f, mc.thePlayer.getSwingProgress(partialTicks));
                                    this.doBlockTransformations();
                                    GlStateManager.translate(-0.5F,0.2F,0.0F);
                                    break;
                                }
                                case 15:{
                                    this.transformFirstPersonItem(f2, mc.thePlayer.getSwingProgress(partialTicks));
                                    this.doBlockTransformations();
                                    break;
                                }
                            }
                            break;
                        case BOW:
                            this.transformFirstPersonItem(f2, 0.0F);
                            this.func_178098_a(partialTicks, abstractclientplayer);
                    }
                }
                else
                {
                    if(Animations.EveryThingBlock.isEnabled() && mc.gameSettings.keyBindUseItem.isKeyDown()){
                        if(!ModuleManager.getModuleByClass(Animations.class).isEnabled()) {
                            this.func_178105_d(f3);
                            this.transformFirstPersonItem(f2, f3);
                        }else {
                            GlStateManager.translate(Animations.x.getValue(), Animations.y.getValue(), Animations.z.getValue());
                            if (Animations.Modes.getMode().equals("1.7")) {
                                Mode = 1;
                            }
                            if (Animations.Modes.getMode().equals("Remix")) {
                                Mode = 2;
                            }
                            if (Animations.Modes.getMode().equals("Screw")) {
                                Mode = 3;
                            }
                            if (Animations.Modes.getMode().equals("Swang")) {
                                Mode = 4;
                            }
                            if (Animations.Modes.getMode().equals("Swank")) {
                                Mode = 5;
                            }
                            if (Animations.Modes.getMode().equals("Swing")) {
                                Mode = 6;
                            }
                            if (Animations.Modes.getMode().equals("Swong")) {
                                Mode = 7;
                            }
                            if (Animations.Modes.getMode().equals("SwAing")) {
                                Mode = 8;
                            }
                            if (Animations.Modes.getMode().equals("Custom")) {
                                Mode = 9;
                            }
                            if (Animations.Modes.getMode().equals("Gay")) {
                                Mode = 10;
                            }
                            if (Animations.Modes.getMode().equals("Punch")) {
                                Mode = 11;
                            }
                            if (Animations.Modes.getMode().equals("Winter")) {
                                Mode = 12;
                            }
                            if (Animations.Modes.getMode().equals("rotate")) {
                                Mode = 13;
                            }
                            if (Animations.Modes.getMode().equals("Lunar")) {
                                Mode = 14;
                            }
                            if(Animations.Modes.getMode().equals("Vanilla")){
                                Mode = 15;
                            }
                            switch (Mode) {
                                case 14:
                                case 5: {
                                    GL11.glTranslated(-0.10000000149011612, 0.15000000596046448, 0.0);
                                    this.transformFirstPersonItem(f2 / 2.0f, f3);
                                    final float var9 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927f);
                                    GlStateManager.rotate(var9 * 30.0f, -var9, -0.0f, 9.0f);
                                    GlStateManager.rotate(var9 * 40.0f, 1.0f, -var9, -0.0f);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 1: {
                                    this.genCustom(f2, f3);
                                    this.func_178103_d();
                                    break;
                                }
                                case 2: {
                                    this.transformFirstPersonItem(f2, 0.83f);
                                    this.doBlockTransformations();
                                    final float f6 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.83f);
                                    GlStateManager.translate(-0.5f, 0.2f, 0.2f);
                                    GlStateManager.rotate(-f6 * 0.0f, 0.0f, 0.0f, 0.0f);
                                    GlStateManager.rotate(-f6 * 43.0f, 58.0f, 23.0f, 45.0f);
                                    break;
                                }
                                case 3: {
                                    this.circle(f3);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 4: {
                                    GL11.glTranslated(-0.10000000149011612, 0.15000000596046448, 0.0);
                                    this.func_178096_b(var6 / 2.0f, var8);
                                    final float var9 = MathHelper.sin(MathHelper.sqrt_float(var8) * 3.1415927f);
                                    GlStateManager.rotate(var9 * 30.0f / 2.0f, -var9, -0.0f, 9.0f);
                                    GlStateManager.rotate(var9 * 40.0f, 1.0f, -var9 / 2.0f, -0.0f);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 6: {
                                    GL11.glTranslated(-0.10000000149011612, 0.15000000596046448, 0.0);
                                    GL11.glTranslated(0.10000000149011612, -0.15000000596046448, 0.0);
                                    this.transformFirstPersonItem(var6 / 2.0f, var8);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 7: {
                                    GL11.glTranslated(-0.10000000149011612, 0.15000000596046448, 0.0);
                                    GlStateManager.translate(-0.0f, -0.1f, 0.0f);
                                    this.transformFirstPersonItem(f2 / 2.0f, 0.0f);
                                    final float var9 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927f);
                                    GL11.glRotated(-var9 * 40.0f / 2.0f, var9 / 2.0f, -0.0, 9.0);
                                    GL11.glRotated(-var9 * 30.0f, 1.0, var9 / 2.0f, -0.0);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 8: {
                                    GL11.glTranslated(-0.10000000149011612, 0.15000000596046448, 0.0);
                                    GL11.glTranslated(0.10000000149011612, -0.20000000298023224, 0.0);
                                    this.avatar(f2, f3);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 9: {
                                    this.func_178096_b(f2, 0.0f);
                                    this.doBlockTransformations();
                                    final float var19 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927f);
                                    GlStateManager.rotate(-var19 * 70.0f / 2.0f, -2.0f, -0.0f, 2.0f);
                                    GlStateManager.rotate(-var19 * 70.0f, 1.0f, -0.4f, -0.0f);
                                    break;
                                }
                                case 10: {
                                    this.transformFirstPersonItem(f2, 0.0f);
                                    this.doBlockTransformations();
                                    final float var11 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927f);
                                    GlStateManager.translate(-0.05f, 0.3f, 0.0f);
                                    GlStateManager.rotate(-var11 * 70.0f / 2.0f, -8.0f, -0.0f, 9.0f);
                                    GlStateManager.rotate(-var11 * 70.0f, 1.0f, -0.4f, -0.0f);
                                    break;
                                }
                                case 11: {
                                    this.transformFirstPersonItem(f2, 0.0f);
                                    this.doBlockTransformations();
                                    final float var9 = MathHelper.sin(MathHelper.sqrt_float(f3) * 3.1415927f);
                                    GlStateManager.translate(0.1f, 0.2f, 0.3f);
                                    GlStateManager.rotate(-var9 * 30.0f, -5.0f, 0.0f, 9.0f);
                                    GlStateManager.rotate(-var9 * 10.0f, 1.0f, -0.4f, -0.5f);
                                    break;
                                }
                                case 12: {
                                    this.x3IsBlack(-0.3f, f3);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 13: {
                                    this.Random(f3);
                                    this.doBlockTransformations();
                                    break;
                                }
                                case 15:{
                                    this.transformFirstPersonItem(f2, mc.thePlayer.getSwingProgress(partialTicks));
                                    this.doBlockTransformations();
                                    break;
                                }
                            }
                        }
                    }else {
                        this.func_178105_d(f3);
                        this.transformFirstPersonItem(f2, f3);
                    }
                }

                this.renderItem(abstractclientplayer, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
            }
            else if (!abstractclientplayer.isInvisible())
            {
                this.func_178095_a(abstractclientplayer, f2, f3);
            }

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
        }
    }

    private void func_178096_b(final float p_178096_1_, final float p_178096_2_) {
        GlStateManager.translate(0.56f, -0.52f, -0.71999997f);
        GlStateManager.translate(0.0f, p_178096_1_ * -0.6f, 0.0f);
        GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
        final float var3 = MathHelper.sin(p_178096_2_ * p_178096_2_ * 3.1415927f);
        final float var4 = MathHelper.sin(MathHelper.sqrt_float(p_178096_2_) * 3.1415927f);
        GlStateManager.rotate(var3 * -20.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(var4 * -20.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(var4 * -80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(0.4f, 0.4f, 0.4f);
    }

    private void x3IsBlack(final float p_178096_1_, final float p_178096_2_) {
        GlStateManager.translate(0.56f, -0.52f, -0.71999997f);
        GlStateManager.translate(0.0f, p_178096_1_ * -0.6f, 0.0f);
        GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(MathHelper.sin(MathHelper.sqrt_float(p_178096_2_) * 3.1415927f) * -35.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(0.4f, 0.4f, 0.4f);
    }

    private void doBlockTransformations() {
        GlStateManager.translate(-0.5F, 0.2F, 0.0F);
        GlStateManager.rotate(30.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(60.0f, 0.0f, 1.0f, 0.0f);
    }

    private void genCustom(final float p_178096_1_, final float p_178096_2_) {
        GlStateManager.translate(0.56f, -0.52f, -0.71999997f);
        GlStateManager.translate(0.0f, p_178096_1_ * -0.6f, 0.0f);
        GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
        final float var3 = MathHelper.sin(p_178096_2_ * p_178096_2_ * 3.1415927f);
        final float var4 = MathHelper.sin(MathHelper.sqrt_float(p_178096_2_) * 3.1415927f);
        GlStateManager.rotate(var3 * -34.0f, 0.0f, 1.0f, 0.2f);
        GlStateManager.rotate(var4 * -20.7f, 0.2f, 0.1f, 1.0f);
        GlStateManager.rotate(var4 * -68.6f, 1.3f, 0.1f, 0.2f);
        GlStateManager.scale(0.4f, 0.4f, 0.4f);
    }
    private void circle(final float swingProgress) {
        ++this.ticks;
        GlStateManager.translate(0.7f, -0.4f, -0.8f);
        GlStateManager.rotate(this.ticks * 0.2f * (float)Animations.Speed.getValue(), 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(40.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(34.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(0.4, 0.4, 0.4);
    }

    private void Random(final float ignored) {
        this.ticks++;
        /*  858 */     GlStateManager.translate(0.7D, -0.4000000059604645D, -0.800000011920929D);
        /*  859 */     GlStateManager.rotate(50.0F, 1.0F, 0.0F, 0.0F);
        /*  860 */     GlStateManager.rotate(50.0F, 0.0F, 0.0F, -1.0F);
        /*  861 */     GlStateManager.rotate(this.ticks * 0.2F * ((Double)Animations.Speed.getValue()).floatValue(), 0.0F, 0.0F, 1.0F);
        /*  862 */     GlStateManager.rotate(-25.0F, 1.0F, 0.0F, 0.0F);
        /*  863 */     GlStateManager.scale(0.4D, 0.4D, 0.4D);
    }
    private void avatar(final float equipProgress, final float swingProgress) {
        GlStateManager.translate(0.6f, -0.48f, -0.79999995f);
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
        final float f = MathHelper.sin(swingProgress * swingProgress * 3.1415927f);
        final float f2 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927f);
        GlStateManager.rotate(f2 * -40.0f, 1.0f, -0.2f, 0.2f);
        GlStateManager.scale(0.4f, 0.4f, 0.4f);
    }

    /**
     * Renders all the overlays that are in first person mode. Args: partialTickTime
     */
    public void renderOverlays(float partialTicks)
    {
        GlStateManager.disableAlpha();

        if (this.mc.thePlayer.isEntityInsideOpaqueBlock())
        {
            IBlockState iblockstate = this.mc.theWorld.getBlockState(new BlockPos(this.mc.thePlayer));
            BlockPos blockpos = new BlockPos(this.mc.thePlayer);
            EntityPlayer entityplayer = this.mc.thePlayer;

            for (int i = 0; i < 8; ++i)
            {
                double d0 = entityplayer.posX + (double)(((float)((i >> 0) % 2) - 0.5F) * entityplayer.width * 0.8F);
                double d1 = entityplayer.posY + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F);
                double d2 = entityplayer.posZ + (double)(((float)((i >> 2) % 2) - 0.5F) * entityplayer.width * 0.8F);
                BlockPos blockpos1 = new BlockPos(d0, d1 + (double)entityplayer.getEyeHeight(), d2);
                IBlockState iblockstate1 = this.mc.theWorld.getBlockState(blockpos1);

                if (iblockstate1.getBlock().isVisuallyOpaque())
                {
                    iblockstate = iblockstate1;
                    blockpos = blockpos1;
                }
            }

            if (iblockstate.getBlock().getRenderType() != -1)
            {
                Object object = Reflector.getFieldValue(Reflector.RenderBlockOverlayEvent_OverlayType_BLOCK);

                if (!Reflector.callBoolean(Reflector.ForgeEventFactory_renderBlockOverlay, new Object[] {this.mc.thePlayer, Float.valueOf(partialTicks), object, iblockstate, blockpos}))
                {
                    this.func_178108_a(partialTicks, this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(iblockstate));
                }
            }
        }

        if (!this.mc.thePlayer.isSpectator())
        {
            if (this.mc.thePlayer.isInsideOfMaterial(Material.water) && !Reflector.callBoolean(Reflector.ForgeEventFactory_renderWaterOverlay, new Object[] {this.mc.thePlayer, Float.valueOf(partialTicks)}))
            {
                this.renderWaterOverlayTexture(partialTicks);
            }

            if (this.mc.thePlayer.isBurning() && !Reflector.callBoolean(Reflector.ForgeEventFactory_renderFireOverlay, new Object[] {this.mc.thePlayer, Float.valueOf(partialTicks)}))
            {
                this.renderFireInFirstPerson(partialTicks);
            }
        }

        GlStateManager.enableAlpha();
    }

    private void func_178108_a(float p_178108_1_, TextureAtlasSprite p_178108_2_)
    {
        this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        float f = 0.1F;
        GlStateManager.color(0.1F, 0.1F, 0.1F, 0.5F);
        GlStateManager.pushMatrix();
        float f1 = -1.0F;
        float f2 = 1.0F;
        float f3 = -1.0F;
        float f4 = 1.0F;
        float f5 = -0.5F;
        float f6 = p_178108_2_.getMinU();
        float f7 = p_178108_2_.getMaxU();
        float f8 = p_178108_2_.getMinV();
        float f9 = p_178108_2_.getMaxV();
        worldrenderer.begin(7, DefaultVertexFormats.field_181707_g);
        worldrenderer.pos(-1.0D, -1.0D, -0.5D).tex(f7, f9).endVertex();
        worldrenderer.pos(1.0D, -1.0D, -0.5D).tex(f6, f9).endVertex();
        worldrenderer.pos(1.0D, 1.0D, -0.5D).tex(f6, f8).endVertex();
        worldrenderer.pos(-1.0D, 1.0D, -0.5D).tex(f7, f8).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Renders a texture that warps around based on the direction the player is looking. Texture needs to be bound
     * before being called. Used for the water overlay. Args: parialTickTime
     */
    private void renderWaterOverlayTexture(float p_78448_1_)
    {
        if (!Config.isShaders() || Shaders.isUnderwaterOverlay())
        {
            this.mc.getTextureManager().bindTexture(RES_UNDERWATER_OVERLAY);
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            float f = this.mc.thePlayer.getBrightness(p_78448_1_);
            GlStateManager.color(f, f, f, 0.5F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.pushMatrix();
            float f1 = 4.0F;
            float f2 = -1.0F;
            float f3 = 1.0F;
            float f4 = -1.0F;
            float f5 = 1.0F;
            float f6 = -0.5F;
            float f7 = -this.mc.thePlayer.rotationYaw / 64.0F;
            float f8 = this.mc.thePlayer.rotationPitch / 64.0F;
            worldrenderer.begin(7, DefaultVertexFormats.field_181707_g);
            worldrenderer.pos(-1.0D, -1.0D, -0.5D).tex(4.0F + f7, 4.0F + f8).endVertex();
            worldrenderer.pos(1.0D, -1.0D, -0.5D).tex(0.0F + f7, 4.0F + f8).endVertex();
            worldrenderer.pos(1.0D, 1.0D, -0.5D).tex(0.0F + f7, 0.0F + f8).endVertex();
            worldrenderer.pos(-1.0D, 1.0D, -0.5D).tex(4.0F + f7, 0.0F + f8).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableBlend();
        }
    }

    /**
     * Renders the fire on the screen for first person mode. Arg: partialTickTime
     */
    private void renderFireInFirstPerson(float p_78442_1_)
    {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
        GlStateManager.depthFunc(519);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        float f = 1.0F;

        for (int i = 0; i < 2; ++i)
        {
            GlStateManager.pushMatrix();
            TextureAtlasSprite textureatlassprite = this.mc.getTextureMapBlocks().getAtlasSprite("minecraft:blocks/fire_layer_1");
            this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            float f1 = textureatlassprite.getMinU();
            float f2 = textureatlassprite.getMaxU();
            float f3 = textureatlassprite.getMinV();
            float f4 = textureatlassprite.getMaxV();
            float f5 = (0.0F - f) / 2.0F;
            float f6 = f5 + f;
            float f7 = 0.0F - f / 2.0F;
            float f8 = f7 + f;
            float f9 = -0.5F;
            GlStateManager.translate((float)(-(i * 2 - 1)) * 0.24F, -0.3F, 0.0F);
            GlStateManager.rotate((float)(i * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
            worldrenderer.begin(7, DefaultVertexFormats.field_181707_g);
            worldrenderer.setSprite(textureatlassprite);
            worldrenderer.pos(f5, f7, f9).tex(f2, f4).endVertex();
            worldrenderer.pos(f6, f7, f9).tex(f1, f4).endVertex();
            worldrenderer.pos(f6, f8, f9).tex(f1, f3).endVertex();
            worldrenderer.pos(f5, f8, f9).tex(f2, f3).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
        }

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.depthFunc(515);
    }

    public void updateEquippedItem()
    {
        this.prevEquippedProgress = this.equippedProgress;
        EntityPlayer entityplayer = this.mc.thePlayer;
        ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        boolean flag = false;

        if (this.itemToRender != null && itemstack != null)
        {
            if (!this.itemToRender.getIsItemStackEqual(itemstack))
            {
                if (Reflector.ForgeItem_shouldCauseReequipAnimation.exists())
                {
                    boolean flag1 = Reflector.callBoolean(this.itemToRender.getItem(), Reflector.ForgeItem_shouldCauseReequipAnimation, new Object[] {this.itemToRender, itemstack, Boolean.valueOf(this.equippedItemSlot != entityplayer.inventory.currentItem)});

                    if (!flag1)
                    {
                        this.itemToRender = itemstack;
                        this.equippedItemSlot = entityplayer.inventory.currentItem;
                        return;
                    }
                }

                flag = true;
            }
        }
        else if (this.itemToRender == null && itemstack == null)
        {
            flag = false;
        }
        else
        {
            flag = true;
        }

        float f2 = 0.4F;
        float f = flag ? 0.0F : 1.0F;
        float f1 = MathHelper.clamp_float(f - this.equippedProgress, -f2, f2);
        this.equippedProgress += f1;

        if (this.equippedProgress < 0.1F)
        {
            this.itemToRender = itemstack;
            this.equippedItemSlot = entityplayer.inventory.currentItem;

            if (Config.isShaders())
            {
                Shaders.setItemToRenderMain(itemstack);
            }
        }
    }

    /**
     * Resets equippedProgress
     */
    public void resetEquippedProgress()
    {
        this.equippedProgress = 0.0F;
    }

    /**
     * Resets equippedProgress
     */
    public void resetEquippedProgress2()
    {
        this.equippedProgress = 0.0F;
    }
}
