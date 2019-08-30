package ru.yammi.eventsystem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

final class EventHandle {

    private Object handle;
    private List methods = new ArrayList();

    public EventHandle(Object object, List list) {
        this.handle = object;
        this.methods = list;
    }

    public Object getHandle() {
        return this.handle;
    }

    public List<Method> getMethods() {
        return this.methods;
    }

    public void setHandle(Object object) {
        this.handle = object;
    }

    public void setMethods(List list) {
        this.methods = list;
    }
}
