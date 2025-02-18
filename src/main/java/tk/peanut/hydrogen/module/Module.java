package tk.peanut.hydrogen.module;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.file.files.ModuleFile;
import tk.peanut.hydrogen.settings.Setting;

import java.awt.*;

public class Module {

    public boolean visible;
    public static Minecraft mc = Minecraft.getMinecraft();
    public boolean toggled;
    public String suffix;
    private int slide = 0;
    private int keyBind;
    private Color color;


    public Module(int keyBind) {
        this.keyBind = keyBind;
    }

    public Info getModuleInfo() {
        if(this.getClass().isAnnotationPresent(Info.class)) {
            return this.getClass().getAnnotation(Info.class);
        }
        return null;
    }

    public String getName() {
        return this.getModuleInfo().name();
    }

    public String getDescription() {
        return this.getModuleInfo().description();
    }

    public void addSetting(Setting settingIn) {
        Hydrogen.getClient().settingsManager.rSetting(settingIn);
    }

    public Category getCategory() {
        return this.getModuleInfo().category();
    }

    public Color getColor() {
        if(this.getCategory().equals(Category.Combat)) {
            return new Color(255, 219, 171);
        } else if(this.getCategory().equals(Category.Movement)) {
            return new Color(173, 234, 255);
        } else if (this.getCategory().equals(Category.Player)) {
            return new Color(252, 255, 199);
        } else if (this.getCategory().equals(Category.Render)) {
            new Color(199, 255, 201);
        }
        return new Color(199, 255, 201);
    }


    public int getKeybind() {
        return this.keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public int getSlide() {
        return slide;
    }

    public void setSlide(int slide) {
        this.slide = slide;
    }

    public static String capitalize(String line)
    {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public boolean setDisabled() {
        return toggled = false;
    }

    public boolean setEnabled() {
        return toggled = true;
    }

    public void onUpdate() {}

    public boolean isEnabled() {
        return toggled;
    }

    public boolean isVisible() {
        return visible;
    }

    public void toggle() {
        this.toggled = (!this.toggled);
        if(this.toggled) {
            onEnable();
            this.setSuffix("");
        }else{
            onDisable();
        }
        ModuleFile.saveModules();
    }
    public void onEnable() {
        EventManager.register(this);

    }
    public void onDisable() {
        EventManager.unregister(this);
    }

    public void onPre() {}

    public void setup() {
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void onRender() {}

    public void render3DPost() {}

    public void onPreUpdate() {}

    public void onPacket() {}

    public void onTick() {}




}