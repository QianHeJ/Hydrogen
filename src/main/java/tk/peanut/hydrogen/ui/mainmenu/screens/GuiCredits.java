package tk.peanut.hydrogen.ui.mainmenu.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.ui.mainmenu.MainMenu;
import tk.peanut.hydrogen.ui.mainmenu.utils.ExpandButton;
import tk.peanut.hydrogen.utils.FontHelper;
import tk.peanut.hydrogen.utils.FontUtil;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;
import java.io.IOException;

/**
 * Created by peanut on 26/02/2021
 */
public class GuiCredits extends GuiScreen {

    public GuiCredits() {}

    public void initGui() {
        this.buttonList.add(new GuiButton(1, Utils.getScaledRes().getScaledWidth() / 3, Utils.getScaledRes().getScaledHeight() - 30, Utils.getScaledRes().getScaledWidth() / 3 - 1, 20, "Back"));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        int left = Utils.getScaledRes().getScaledWidth() / 3 - 24;
        int right = Utils.getScaledRes().getScaledWidth() - Utils.getScaledRes().getScaledWidth() / 3 + 24;
        drawRect(left, 0, right, Utils.getScaledRes().getScaledHeight(), 0x55000000);

        FontUtil.drawTotalCenteredStringWithShadow("Contributors to Hydrogen", (float) Utils.getScaledRes().getScaledWidth() / 2, 50, -1);

        FontUtil.drawTotalCenteredStringWithShadow("zPeanut - Main Developer", (float) Utils.getScaledRes().getScaledWidth() / 2, (float) this.height / 2 - 48, -1);
        FontUtil.drawTotalCenteredStringWithShadow("UltramoxX - Co Developer", (float) Utils.getScaledRes().getScaledWidth() / 2, (float) this.height / 2 - 36, -1);
        FontUtil.drawTotalCenteredStringWithShadow("ProfKambing - Contributor", (float) Utils.getScaledRes().getScaledWidth() / 2, (float) this.height / 2 - 24, -1);
        FontUtil.drawTotalCenteredStringWithShadow("QianHeJ - Contributor", (float) Utils.getScaledRes().getScaledWidth() / 2, (float) this.height / 2  - 12, -1);

        FontUtil.drawTotalCenteredStringWithShadow("superblaubeere27 - Providing the initial ClientBase", (float) Utils.getScaledRes().getScaledWidth() / 2, (float) this.height / 2 + 12, -1);
        FontUtil.drawTotalCenteredStringWithShadow("HeroCode - Providing the settings system", (float) Utils.getScaledRes().getScaledWidth() / 2, (float) this.height / 2 + 24, -1);
        FontUtil.drawTotalCenteredStringWithShadow("Lemon - Providing the ClickGUI", (float) Utils.getScaledRes().getScaledWidth() / 2, (float) this.height / 2 + 36, -1);
        FontUtil.drawTotalCenteredStringWithShadow("Hexeption - Providing the OutlineESP code", (float) Utils.getScaledRes().getScaledWidth() / 2, (float) this.height / 2 + 48, -1);


        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1:
                mc.displayGuiScreen(new GuiMainMenu());
                break;
        }
    }

}

