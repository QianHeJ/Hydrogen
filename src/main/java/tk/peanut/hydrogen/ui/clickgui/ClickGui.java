package tk.peanut.hydrogen.ui.clickgui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.file.files.ClickGuiFile;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.ui.clickgui.component.Component;
import tk.peanut.hydrogen.ui.clickgui.component.Frame;
import tk.peanut.hydrogen.utils.BlurUtil;
import tk.peanut.hydrogen.utils.ParticleGenerator;
import tk.peanut.hydrogen.utils.ReflectionUtil;
import tk.peanut.hydrogen.utils.Utils;

public class ClickGui extends GuiScreen {

	public static ArrayList<Frame> frames;
	public static int color = 0x99cfdcff;
	private ParticleGenerator particleGen;
	
	public ClickGui() {
		this.frames = new ArrayList<Frame>();
		this.particleGen = new ParticleGenerator(60, Utils.getScaledRes().getScaledWidth(), Utils.getScaledRes().getScaledHeight());
		int frameX = 5;
		for(Category category : Category.values()) {
			Frame frame = new Frame(category);
			frame.setX(frameX);
			frames.add(frame);
			frameX += frame.getWidth() + 1;
		}
		}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		drawRect(0, 0, this.width, this.height, 0x66101010);
		for (Frame frame : frames) {
			frame.renderFrame(this.fontRendererObj);
			frame.updatePosition(mouseX, mouseY);
			for (Component comp : frame.getComponents()) {
				comp.updateComponent(mouseX, mouseY);
			}
		}
		if (Hydrogen.getClient().settingsManager.getSettingByName("Blur").isEnabled()) {
			if (OpenGlHelper.shadersSupported) {
				if (mc.entityRenderer.getShaderGroup() != null) {
					mc.entityRenderer.getShaderGroup().deleteShaderGroup();
				}
				mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
			}
		} else {
			if (this.mc.entityRenderer.getShaderGroup() != null) {
				this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
				try {
					ReflectionUtil.theShaderGroup.set(Minecraft.getMinecraft().entityRenderer, (ShaderGroup) null);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
		for(Frame frame : frames) {
			if(frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
				frame.setDrag(true);
				frame.dragX = mouseX - frame.getX();
				frame.dragY = mouseY - frame.getY();
			}
			if(frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
				frame.setOpen(!frame.isOpen());
			}
			if(frame.isOpen()) {
				if(!frame.getComponents().isEmpty()) {
					for(Component component : frame.getComponents()) {
						component.mouseClicked(mouseX, mouseY, mouseButton);
					}
				}
			}
		}
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) {
		for(Frame frame : frames) {
			if(frame.isOpen() && keyCode != 1) {
				if(!frame.getComponents().isEmpty()) {
					for(Component component : frame.getComponents()) {
						component.keyTyped(typedChar, keyCode);
					}
				}
			}
		}
		if (keyCode == 1) {
            this.mc.displayGuiScreen(null);
        }
	}

	@Override
	public void onGuiClosed() {
		if(this.mc.entityRenderer.getShaderGroup() != null) {
			this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
			try {
				ReflectionUtil.theShaderGroup.set(Minecraft.getMinecraft().entityRenderer, (ShaderGroup)null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		ClickGuiFile.saveClickGui();

		super.onGuiClosed();
	}

	
	@Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
		for(Frame frame : frames) {
			frame.setDrag(false);
		}
		for(Frame frame : frames) {
			if(frame.isOpen()) {
				if(!frame.getComponents().isEmpty()) {
					for(Component component : frame.getComponents()) {
						component.mouseReleased(mouseX, mouseY, state);
					}
				}
			}
		}
	}

	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
