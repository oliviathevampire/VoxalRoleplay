package uk.co.phoenixfirewingz.share.network.packages;

import uk.co.phoenixfirewingz.share.game.BlockRegistry;
import uk.co.phoenixfirewingz.share.network.NetPackage;

import static uk.co.phoenixfirewingz.share.network.PackageSubType.BLOCK;
import static uk.co.phoenixfirewingz.share.network.PackageType.SET;

public class GiveBlockRegistry extends NetPackage {
    public BlockRegistry blockRegistry;
    public GiveBlockRegistry(BlockRegistry blockRegistry) {
        super(SET, BLOCK);
        this.blockRegistry = blockRegistry;
    }
}
