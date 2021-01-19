package dev.tr7zw.transliterationlib.fabric.wrapper;

import java.util.function.BiConsumer;

import dev.tr7zw.transliterationlib.api.config.WrappedConfigEntry;
import dev.tr7zw.transliterationlib.api.wrapper.WrappedEntity;
import dev.tr7zw.transliterationlib.api.wrapper.WrappedEntityTrackerUpdate;
import dev.tr7zw.transliterationlib.api.wrapper.WrappedKeybind;
import dev.tr7zw.transliterationlib.api.wrapper.WrappedScreen;
import dev.tr7zw.transliterationlib.api.wrapper.WrappedText;
import dev.tr7zw.transliterationlib.api.wrapper.WrappedWorld;
import dev.tr7zw.transliterationlib.api.wrapper.OldWrapper;
import me.shedaniel.clothconfig2.api.AbstractConfigEntry;
import me.shedaniel.clothconfig2.gui.entries.EnumListEntry;
import me.shedaniel.clothconfig2.gui.entries.IntegerSliderEntry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class WrapperImpl implements OldWrapper{

	@Override
	public WrappedScreen wrapScreen(Object screen) {
		return new WrappedScreen() {
			
			private Screen handler = (Screen) screen;
			
			@Override
			public Screen getHandler() {
				return handler;
			}
		};
	}

	@Override
	public WrappedText wrapText(Object text) {
		return new WrappedText() {
			
			private Text handler = (Text) text;
			
			@Override
			public Object getHandler() {
				return handler;
			}
		};
	}

	@Override
	public WrappedText getTranslateableText(String text) {
		return wrapText(new TranslatableText(text));
	}

	@Override
	public WrappedConfigEntry getWrappedConfigEntry(Object entry) {
		return new WrappedConfigEntry() {
			
			private AbstractConfigEntry<?> handler = (AbstractConfigEntry<?>) entry;
			
			@Override
			public Object getHandler() {
				return handler;
			}

			@SuppressWarnings("unchecked")
			@Override
			public <T extends Enum<?>> T getEnumValue(Class<T> targetEnum) {
				return ((EnumListEntry<T>)handler).getValue();
			}

			@Override
			public int getIntValue() {
				return ((IntegerSliderEntry)handler).getValue();
			}
		};
	}

	@Override
	public WrappedKeybind createKeyBind(String name, int key, String namespace) {
		return new WrappedKeybind() {
			
			private KeyBinding keybind = new KeyBinding(name, key, namespace);
			
			@Override
			public Object getHandler() {
				return keybind;
			}

			@Override
			public boolean isPressed() {
				return keybind.isPressed();
			}
		};
	}

	@Override
	public WrappedWorld wrapWorld(Object world) {
		return new WrappedWorld() {
			
			private ClientWorld clientWorld = (ClientWorld) world;
			
			@Override
			public Object getHandler() {
				return clientWorld;
			}
			
			@Override
			public WrappedEntity getEntityById(int id) {
				return wrapEntity(clientWorld.getEntityById(id));
			}
		};
	}

	@Override
	public WrappedEntity wrapEntity(Object entity) {
		return new WrappedEntity() {
			
			private Entity ent = (Entity) entity;
			
			@Override
			public Object getHandler() {
				return ent;
			}
		};
	}

	@Override
	public WrappedEntityTrackerUpdate wrapEntityTrackerUpdatePacket(Object packet) {
		return new WrappedEntityTrackerUpdate() {
			
			private EntityTrackerUpdateS2CPacket etuPacket = (EntityTrackerUpdateS2CPacket) packet;
			
			@Override
			public Object getHandler() {
				return etuPacket;
			}

			@Override
			public int id() {
				return etuPacket.id();
			}

			@Override
			public boolean hasTrackedValues() {
				return etuPacket.getTrackedValues() != null;
			}

			@Override
			public void forEach(BiConsumer<Integer, Object> handler) {
				etuPacket.getTrackedValues().forEach(entry -> {
					handler.accept(entry.getData().getId(), entry.get());
				});
			}
		};
	}

}
