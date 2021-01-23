package dev.tr7zw.transliterationlib.fabric.wrapper.item;

import dev.tr7zw.transliterationlib.api.wrapper.item.UseAction;

public class TRLUseAction implements UseAction {
	
	private static final UseAction NONE = new TRLUseAction(net.minecraft.util.UseAction.NONE);
	private static final UseAction EAT = new TRLUseAction(net.minecraft.util.UseAction.EAT);
	private static final UseAction DRINK = new TRLUseAction(net.minecraft.util.UseAction.DRINK);
	private static final UseAction BLOCK = new TRLUseAction(net.minecraft.util.UseAction.BLOCK);
	private static final UseAction BOW = new TRLUseAction(net.minecraft.util.UseAction.BOW);
	private static final UseAction SPEAR = new TRLUseAction(net.minecraft.util.UseAction.SPEAR);
	private static final UseAction CROSSBOW = new TRLUseAction(net.minecraft.util.UseAction.CROSSBOW);
	
	private final net.minecraft.util.UseAction handle;

	public TRLUseAction() {
		handle = null;
	}
	
	public TRLUseAction(net.minecraft.util.UseAction action) {
		handle = action;
	}
	
	@Override
	public Object getHandler() {
		return handle;
	}

	@Override
	public UseAction of(Object handler) {
		return new TRLUseAction(handle);
	}

	@Override
	public UseAction getNone() {
		return NONE;
	}

	@Override
	public UseAction getEat() {
		return EAT;
	}

	@Override
	public UseAction getDrink() {
		return DRINK;
	}

	@Override
	public UseAction getBlock() {
		return BLOCK;
	}

	@Override
	public UseAction getBow() {
		return BOW;
	}

	@Override
	public UseAction getSpear() {
		return SPEAR;
	}

	@Override
	public UseAction getCrossbow() {
		return CROSSBOW;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <H> H getHandler(Class<H> clazz) {
		return (H) this.handle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((handle == null) ? 0 : handle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TRLUseAction other = (TRLUseAction) obj;
		if (handle != other.handle)
			return false;
		return true;
	}
}