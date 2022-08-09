package uk.co.phoenixfirewingz.share.network.packages;

import uk.co.phoenixfirewingz.share.network.NetPackage;
import uk.co.phoenixfirewingz.share.network.PackageSubType;

import static uk.co.phoenixfirewingz.share.network.PackageType.GET;

public class RequestRegistry extends NetPackage {
    public RequestRegistry(PackageSubType type) {
        super(GET, type);
    }
}
