package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

import commands.OwnCommand;

public class Continue extends Command
{

    @Override
    public void accept(OwnCommand oC)
    {
        oC.visit(this);
    }

}
