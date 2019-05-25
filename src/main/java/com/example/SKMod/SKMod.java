package com.example.SKMod;

import net.minecraft.init.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = SKMod.MODID, version = SKMod.VERSION, name=SKMod.MODNAME)
public class SKMod
{
    public static final String MODID = "SKMod";
    public static final String VERSION = "1.2";
    public static final String MODNAME = "ShadowK's Mod";

    @EventHandler
    public void preLoad(FMLPreInitializationEvent event) //preload方法
    {

    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        // some example code
        // System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
        //GameRegistry.registerTileEntity(new PowerPlayerEventHandler(), "SKPlayer");
        MinecraftForge.EVENT_BUS.register(new PowerPlayer());
    }


    @EventHandler
    public void postLoad(FMLPostInitializationEvent event) // postLoad方法
    {

    }



}


