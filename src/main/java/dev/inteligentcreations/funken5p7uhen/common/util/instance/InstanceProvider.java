package dev.inteligentcreations.funken5p7uhen.common.util.instance;

public interface InstanceProvider<T extends InstanceIdentifiable>
{
    T getInstance();
}
