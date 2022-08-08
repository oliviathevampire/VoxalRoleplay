package uk.co.phoenixfirewingz.share.network;

import java.io.Serializable;

public class NetPackage implements Serializable {
    public PackageType type;
    public PackageSubType sub_type;
    public NetPackage(PackageType type) {
        this.type = type;
        this.sub_type = PackageSubType.NONE;
    }
    public NetPackage(PackageType type, PackageSubType sub_type) {
        this.type = type;
        this.sub_type = sub_type;
    }
}
