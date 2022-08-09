package uk.co.phoenixfirewingz.share.network.packages;

import uk.co.phoenixfirewingz.share.game.BaseContent;
import uk.co.phoenixfirewingz.share.game.Registry;
import uk.co.phoenixfirewingz.share.network.NetPackage;
import uk.co.phoenixfirewingz.share.network.PackageSubType;

import static uk.co.phoenixfirewingz.share.network.PackageType.SET;

public class GiveRegistry<T extends BaseContent> extends NetPackage {

    public Registry<T> registry;

    public GiveRegistry(Registry<T> registry, PackageSubType packageSubType) {
        super(SET, packageSubType);
        this.registry = registry;
    }

}