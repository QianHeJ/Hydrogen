package tk.peanut.hydrogen.module.modules.combat;

import com.darkmagician6.eventapi.EventTarget;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import scala.collection.parallel.ParIterableLike;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventUpdate;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.ReflectionUtil;
import tk.peanut.hydrogen.utils.TimeHelper;

import java.awt.*;
import java.util.Random;

/**
 * Created by peanut on 05/02/2021
 */

@Info(name = "AutoClicker", description = "Automatically left clicks", category = Category.Combat)
public class AutoClicker extends Module {

    Random random = new Random();
    int randomD = this.random.nextInt(25);
    int randomInc = this.random.nextInt(15);
    TimeHelper time = new TimeHelper();
    int delay;

    public AutoClicker() {
        super(Keyboard.KEY_NONE);

        Hydrogen.getClient().settingsManager.rSetting(new Setting("CPS", this, 9, 1, 20, true));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("on Click", this, false));
    }


    @EventTarget
    public void onUpdate(EventUpdate e) {
        try
        {
            switch((int) Hydrogen.getClient().settingsManager.getSettingByName(this, "CPS").getValDouble()) {
                case 1:
                    delay = 1000;
                    break;
                case 2:
                    delay = 500;
                    break;
                case 3:
                    delay = 333;
                    break;
                case 4:
                    delay = 250;
                    break;
                case 5:
                    delay = 200;
                    break;
                case 6:
                    delay = 166;
                    break;
                case 7:
                    delay = 142;
                    break;
                case 8:
                    delay = 125;
                    break;
                case 9:
                    delay = 111;
                    break;
                case 10:
                    delay = 100;
                    break;
                case 11:
                    delay = 90;
                    break;
                case 12:
                    delay = 83;
                    break;
                case 13:
                    delay = 76;
                    break;
                case 14:
                    delay = 71;
                    break;
                case 15:
                    delay = 66;
                    break;
                case 16:
                    delay = 62;
                    break;
                case 17:
                    delay = 58;
                    break;
                case 18:
                    delay = 55;
                    break;
                case 19:
                    delay = 52;
                    break;
                case 20:
                    delay = 50;
                    break;
            }
                Random random = new Random();
                int randomD = random.nextInt(25);
                int randomInc = random.nextInt(15);
                if (this.time.hasDelayRun(delay - randomD + randomInc))
                {
                    if(Hydrogen.getClient().settingsManager.getSettingByName("on Click").isEnabled()) {
                        if(Minecraft.getMinecraft().gameSettings.keyBindAttack.pressed) {
                            this.mc.clickMouse();
                            this.mc.playerController.attackEntity(mc.thePlayer, this.mc.objectMouseOver.entityHit);
                            this.time.reset();
                        }
                    } else {
                        this.mc.clickMouse();
                        this.mc.playerController.attackEntity(mc.thePlayer, this.mc.objectMouseOver.entityHit);
                        this.time.reset();
                    }
                }

        }
        catch (Exception localException) {}
    }
}
