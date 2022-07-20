package dev.inteligentcreations.funken5p7uhen.client.screen.impl;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.inteligentcreations.funken5p7uhen.common.screen.handler.impl.DevelopingStationScreenHandler;
import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DevelopingStationScreen
        extends HandledScreen<DevelopingStationScreenHandler>
{
    private static final Identifier TEXTURE = new Identifier(funken5p7uhen.MOD_ID, "textures/gui/developing_station.png");
    private final DevelopingStationScreenHandler handler;

    public DevelopingStationScreen(DevelopingStationScreenHandler handler,
                                   PlayerInventory inventory,
                                   Text title)
    {
        super(handler, inventory, title);
        this.handler = handler;
    }

    @Override
    protected void drawBackground(MatrixStack matrices,
                                  float delta,
                                  int mouseX,
                                  int mouseY)
    {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
        int l = handler.getProgress() <= 0 ? 0 : handler.getProgress() / 300 + 1;
        drawTexture(matrices, x + 83, y + 39, 176, 0, l, 4);
    }

    @Override
    public void render(MatrixStack matrices,
                       int mouseX,
                       int mouseY,
                       float delta)
    {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
