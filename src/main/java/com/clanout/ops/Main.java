package com.clanout.ops;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        if (args.length > 0)
        {
            String operation = args[0];
            if (operation.equals("--archive"))
            {
                ArchivingService archivingService = new ArchivingService();
                archivingService.run();
            }
        }
    }
}
