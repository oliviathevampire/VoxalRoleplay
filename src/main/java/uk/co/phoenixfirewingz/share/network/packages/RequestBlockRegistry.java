package uk.co.phoenixfirewingz.share.network.packages;

import uk.co.phoenixfirewingz.share.network.NetPackage;

import static uk.co.phoenixfirewingz.share.network.PackageSubType.BLOCK;
import static uk.co.phoenixfirewingz.share.network.PackageType.GET;

public class RequestBlockRegistry extends NetPackage
{
    public RequestBlockRegistry() {
        super(GET,BLOCK);
    }
}
