package com.example.SKMod;

import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PowerPlayer
{
    @SubscribeEvent
    public void pickupItem(PlayerPickupXpEvent event) {
        if (event.entityPlayer.worldObj.isRemote) return;
        //System.out.println("Xp picked up!");
        //event.entityPlayer.addExperience(100);
        int added = calcAddonXp(event.entityPlayer, event.orb.getXpValue());
        if(added > 0) {
            event.entityPlayer.addExperience(added);
        }
    }

    public int calcAddonXp(EntityPlayer player, int gainXp)
    {
        int nowLv = player.experienceLevel;
        int afterLv = nowLv;

        for(int lastXp = gainXp + getThisXp(nowLv,  player.experience);
            lastXp > xpBarCapNew(afterLv);)
        {
            lastXp = lastXp - xpBarCapNew(afterLv);
            afterLv++;
        }
        int ret = 0;
        if(nowLv >= afterLv)
            return 0;
        for(int i = nowLv ; i < afterLv; i += 1)
            ret += xpBarCap(i+1) - xpBarCapNew(i+1);
        if(ret > 0) {
            player.addChatMessage(new ChatComponentText(
                    "SKMod: Now Lv:" + nowLv + ", After Lv:" + afterLv + ", Added Exp :" + ret));
            return ret;
        }
        return 0;
    }

    private int getThisXp(int lv, float xpNormal)
    {
        return (int)Math.floor(xpNormal * xpBarCap(lv));
    }

    private int xpBarCap(int level)
    {
        return level >= 30 ? 62 + (level - 30) * 7 : (level >= 15 ? 17 + (level - 15) * 3 : 17);
    }

    private int xpBarCapNew(int level)
    {
        return level >= 30 ? 62 : (level >= 15 ? 17 + (level - 15) * 3 : 17);
    }

//    private int xp2lvNew(int xp)
//    {
//        int lv = 0;
//        for( int decr = xpBarCapNew( lv ); decr <= xp; decr = xpBarCapNew( lv ) ) {
//            xp -= decr;
//            lv++;
//        }
//        return lv;
//    }

    //    private int getFullXp(int lv, float xpNormal)
//    {
//        return lv2xp(lv) + getThisXp(lv, xpNormal);
//    }

//    private int xp2lv(int xp)
//    {
//        int lv = 0;
//        for( int decr = xpBarCap( lv ); decr <= xp; decr = xpBarCap( lv ) ) {
//            xp -= decr;
//            lv++;
//        }
//        return lv;
//    }

//    private int lv2xp(int lv)
//    {
//        int xp = 0;
//        for(int l = 0; l < lv; l++)
//            xp +=  xpBarCap(l);
//        return xp;
//    }
}