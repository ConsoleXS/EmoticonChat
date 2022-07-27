package com.consolex.emoticonchat.mixin;

import com.consolex.emoticonchat.screen.EmojiOptions;
import com.sun.jna.platform.win32.NTSecApi;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.network.ClientPlayerEntity;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.MemoryHandler;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
	ClientPlayerEntity entity = MinecraftClient.getInstance().player;

	@Shadow
	protected TextFieldWidget chatField;

	private final MinecraftClient client = MinecraftClient.getInstance();

	@Inject(at = @At(value = "HEAD"), method = "keyPressed", cancellable = true)
	private void addEmoji(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
		if (keyCode == GLFW.GLFW_KEY_ENTER || keyCode == GLFW.GLFW_KEY_KP_ENTER) {
			String string = this.chatField.getText().trim();
			HashMap<String, String> textToEmoji = new HashMap<>();
			textToEmoji.put(":skull:", "☠");
			textToEmoji.put(":v:", "✌");
			textToEmoji.put(":heart:", "❤");
			textToEmoji.put(":tm:", "™");
			textToEmoji.put(":checkmark:", "✓");
			textToEmoji.put(":star:", "★");
			textToEmoji.put(":smile:", "☻");
			textToEmoji.put(":infinity:", "∞");
			textToEmoji.put(":e:", "Ⓔ");
			textToEmoji.put(":x:", "✕");
			textToEmoji.put(":tableflip:", "(╯°□°）╯︵ ┻━┻");
			textToEmoji.put(":fire:", "🔥");




			if (!string.isEmpty()) {
				for (String key : textToEmoji.keySet())
				{
					if (string.contains(key))
					{
						string = string.replace(key, textToEmoji.get(key)).toString();

					}
				}
				entity.sendChatMessage(string);
				cir.cancel();

			}
			this.client.setScreen(null);

		}
		else if (keyCode == GLFW.GLFW_KEY_F4)
		{
			client.setScreen(new EmojiOptions());
		}
	}


}