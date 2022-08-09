package uk.co.phoenixfirewingz.share.game;

public class Registries {
	public static final Registry<Registry<?>> REGISTRIES = new Registry<>();
	public static final Registry<Block> BLOCK = (Registry<Block>) REGISTRIES.register("block", new Registry<>());
	public static final Registry<Item> ITEM = (Registry<Item>) REGISTRIES.register("item", new Registry<>());
	public static final Registry<Entity> ENTITY = (Registry<Entity>) REGISTRIES.register("entity", new Registry<>());
}
