package uk.co.phoenixfirewingz.server.util;

public class Identifier
{
    private String master_name,Location,file_name;
    public Identifier(String master_name, String location, String file_name) {
        this.master_name = master_name;
        Location = location;
        this.file_name = file_name;
    }

    public String getName() {
        return master_name;
    }

    public String getLocation() {
        return Location;
    }

    public String getFileName() {
        return file_name;
    }

    public String getPath()
    {
        if(master_name.isEmpty())
            return Location + "/" + file_name;
        return master_name + "/" + Location + "/" + file_name;
    }
}

