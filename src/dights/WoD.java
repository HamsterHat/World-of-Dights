package dights;

import arc.Events;
import mindustry.game.EventType;
import mindustry.mod.*;
import dights.content.*;



public class WoD extends Mod{
    public WoD(){
        // no
    }


    @Override
    public void loadContent(){
        WoDItems.load();
    }
}
