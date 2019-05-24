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
        int added = calcAddonXp(event.entityPlayer.experienceTotal, event.orb.getXpValue(), event.entityPlayer);
        if(added > 0) {
            event.entityPlayer.addExperience(added);
        }
    }

    public int calcAddonXp(int totalXp, int gainXp, EntityPlayer player)
    {
        int nowLv = xptolv(totalXp);
        int afterLv = xptolv(totalXp + gainXp);
        int ret = 0;
        if(afterLv < 30 || nowLv >= afterLv)
            return 0;
        int startlv = (nowLv < 29) ? 29 : nowLv;
        //make 62 for every level
        for(int i = nowLv ; i < afterLv; i += 1)
            ret += xpBarCap(i + 1) - xpBarCap(30);
        //System.out.println("Will add xp :" + ret);
        player.addChatMessage(new ChatComponentText(
                "SKMod: Now Lv:" + nowLv +", After Lv:" + afterLv +", Added Exp :" + ret));
        return (ret > 0) ? ret : 0;
    }

    private int xpBarCap(int level)
    {
        return level >= 30 ? 62 + (level - 30) * 7 : (level >= 15 ? 17 + (level - 15) * 3 : 17);
    }

    private int xptolv( int xp )
    {
        int lv = 0;
        for( int decr = xpBarCap( lv ); decr <= xp; decr = xpBarCap( lv ) ) {
            xp -= decr;
            lv++;
        }
        return lv;
    }
}