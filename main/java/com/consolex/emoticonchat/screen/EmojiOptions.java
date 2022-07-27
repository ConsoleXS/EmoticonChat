package com.consolex.emoticonchat.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.Clipboard;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class EmojiOptions extends Screen {


private static final Identifier DEMO_BG = new Identifier("textures/gui/demo_background.png");
private MultilineText movementText = MultilineText.EMPTY;
private MultilineText fullWrappedText = MultilineText.EMPTY;

    public EmojiOptions() {
        super(Text.of("Emoji Options"));
    }

    @Override
    protected void init() {
        int i = -16;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 116, this.height / 2 + 62 + -16, 114, 20, Text.of("Star me on GitHub!"), button -> {
            button.active = false;
            Util.getOperatingSystem().open("https://github.com/ConsoleXS/EmoticonChat");
            
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 2, this.height / 2 + 62 + -16, 114, 20, new TranslatableText("demo.help.later"), button -> {
            this.client.setScreen(null);
            this.client.mouse.lockCursor();
        }));
        GameOptions gameOptions = this.client.options;
        this.movementText = MultilineText.create(this.textRenderer, Text.of("A list of possible emojis that are currently in the game!"), 218);
        this.fullWrappedText = MultilineText.create(this.textRenderer, Text.of("  :skull: -> ☠" +
                "   :v: -> ✌\n" +
                "  :heart: -> ❤" +
                "  :tm: -> ™\n" +
                "  :star: -> ★" +
                "   :e: -> Ⓔ\n" +
                "  :smile: -> ☻\n" +
                "  :infinity -> ∞\n" +
                "  :checkmark: -> ✓\n" +

                "  :x: -> ✕\n" +
                "  :tableflip -> (╯°□°）╯︵ ┻━\n" +
                "  :fire: -> \uD83D\uDD25"), 218);

    }

    @Override
    public void renderBackground(MatrixStack matrices) {
        super.renderBackground(matrices);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, DEMO_BG);
        int i = (this.width - 248) / 2;
        int j = (this.height - 166) / 2;
        this.drawTexture(matrices, i, j, 0, 0, 248, 166);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        int i = (this.width - 248) / 2 + 10;
        int j = (this.height - 166) / 2 + 8;
        this.textRenderer.draw(matrices, this.title, (float)i, (float)j, 0x1F1F1F);
        j = this.movementText.draw(matrices, i, j + 12, 12, 0x4F4F4F);
        this.fullWrappedText.draw(matrices, i, j + 1, this.textRenderer.fontHeight, 0x1F1F1F);
        super.render(matrices, mouseX, mouseY, delta);
    }
}


