package tk.peanut.hydrogen.module.modules.hud;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.utils.FontHelper;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;

/**
 * Created by peanut on 18/02/2021
 */
@tk.peanut.hydrogen.module.Info(name = "Info", description = "Shows FPS and Coordinates", color = -1, category = Category.Gui)
public class Info extends Module {
    public Info() {
        super(0x00);
    }

    @EventTarget
    public static void drawInfo(EventRender2D e) {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo)
            return;
        if (Hydrogen.getClient().moduleManager.getModulebyName("Info").isEnabled() && !Hydrogen.getClient().moduleManager.getModulebyName("Hotbar").isEnabled()) {
            String x = String.valueOf((int) mc.thePlayer.posX);
            String y = String.valueOf((int) mc.thePlayer.posY);
            String z = String.valueOf((int) mc.thePlayer.posZ);
            String coordinates = String.format("XYZ §7(%s, %s, %s)", x, y, z);
            String fps = String.format("FPS §7%s", mc.getDebugFPS());
            if (!Boolean.toString(mc.ingameGUI.getChatGUI().getChatOpen()).equals("true")) {
                FontHelper.hfontbold.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.hfontbold.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 12, Color.white);
                FontHelper.hfontbold.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.hfontbold.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 22, Color.white);
            } else {
                FontHelper.hfontbold.drawStringWithShadow(coordinates, Utils.getScaledRes().getScaledWidth() - FontHelper.hfontbold.getStringWidth(coordinates) - 2, Utils.getScaledRes().getScaledHeight() - 26, Color.white);
                FontHelper.hfontbold.drawStringWithShadow(fps, Utils.getScaledRes().getScaledWidth() - FontHelper.hfontbold.getStringWidth(fps) - 2, Utils.getScaledRes().getScaledHeight() - 36, Color.white);
            }
        }
    }
}
