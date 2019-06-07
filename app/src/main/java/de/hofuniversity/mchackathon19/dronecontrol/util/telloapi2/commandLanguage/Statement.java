package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public abstract class Statement implements Visitable
{
    public abstract void accept(OwnCommand oC);
}
