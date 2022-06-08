package me.voidxwalker.options.extra;

import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class OptionsMixinPlugin implements IMixinConfigPlugin{
    private static boolean reducedFOVEffects;
    private static boolean reducedDistortionEffects;
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger("ExtraOptionsConfig");
    @Override
    public void onLoad(String mixinPackage) {
        File file = new File("./config/extra-options.properties");
        if (file.exists()) {
            Properties props = new Properties();

            try (FileInputStream fin = new FileInputStream(file)) {
                props.load(fin);
                if (props.containsKey("reducedFOVEffects") && props.containsKey("reducedDistortionEffects")) {

                    reducedFOVEffects=((String)props.get("reducedFOVEffects")).equalsIgnoreCase("true");
                    reducedDistortionEffects=((String)props.get("reducedDistortionEffects")).equalsIgnoreCase("true");
                    System.out.println(reducedDistortionEffects);
                }
                else {
                    LOGGER.warn("Invalid config file");
                    createDefaultConfig(file);
                }

            } catch (IOException e) {
                throw new RuntimeException("Could not load config file", e);
            }
        }
        else {
            try {
                createDefaultConfig(file);
            } catch (IOException e) {
                throw new RuntimeException("Could not create config file", e);
            }
        }

    }
    private static void createDefaultConfig(File file) throws IOException {
        File dir = file.getParentFile();
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("Could not create parent directories");
            }
        } else if (!dir.isDirectory()) {
            throw new IOException("The parent file is not a directory");
        }

        try (Writer writer = new FileWriter(file)) {
            writer.write("# This is the configuration file for ExtraOptions.\n");
            writer.write("# Please open a ticket in the official minecraft java edition discord server if you want to use this mod for speedrunning: \n");
            writer.write("# https://discord.gg/jmdFn3C\n");
            writer.write("#\n");
            writer.write("reducedFOVEffects:false\n");
            writer.write("reducedDistortionEffects:false");
        }
        reducedDistortionEffects=false;
        reducedFOVEffects=false;
    }
    @Override
    public String getRefMapperConfig() {return null;}

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return (!mixinClassName.endsWith("FOV")||reducedFOVEffects)&&(!mixinClassName.endsWith("Distortion")||reducedDistortionEffects);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {return null;}

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}