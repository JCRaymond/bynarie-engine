package bynarie.engine;

import java.util.HashSet;

public class Flags {
    private HashSet<Class<?>> falseFlags = new HashSet<>();

    public Flags(Class<?>... falseFlags){
        for (Class<?> flag : falseFlags){
            this.falseFlags.add(flag);
        }
    }

    public boolean getState(Class<?> flag){
        return !falseFlags.contains(flag);
    }

    public void setState(Class<?> flag, boolean state){
        if (state){
            falseFlags.remove(flag);
        }
        else{
            falseFlags.add(flag);
        }
    }

    public void flipState(Class<?> flag){
        if (falseFlags.contains(flag)){
            falseFlags.remove(flag);
        }
        else{
            falseFlags.add(flag);
        }
    }
}
