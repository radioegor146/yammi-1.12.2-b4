package ru.yammi.eventsystem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventBus {

    private static List<EventHandle> eventHandles = new ArrayList();

    public static void call(Event wvjBXAZZrrBntvi) {
        eventHandles.forEach((lbKkJhSdZboxacB2) -> {
            lbKkJhSdZboxacB2.getMethods().stream().filter((method) -> !(method.getParameterTypes()[0] != wvjBXAZZrrBntvi.getClass())).forEachOrdered((method) -> {
                try {
                    method.invoke(lbKkJhSdZboxacB2.getHandle(), wvjBXAZZrrBntvi);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        });
    }

    public static void clear() {
        eventHandles.clear();
    }

    public static void register(Object object) {
        ArrayList<Method> arrayList = new ArrayList<>();
        for (Method method : object.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (!method.isAnnotationPresent(EventTarget.class)) {
                continue;
            }
            Class<?>[] arrclass = method.getParameterTypes();
            if (arrclass.length != 1) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Method ").append(method).append(" has @EventTarget annotation, but requires ").append(arrclass.length).append(" arguments.  Event handler methods must require a single argument.")));
            }
            Class<?> class_ = arrclass[0];
            if (!Event.class.isAssignableFrom(class_)) {
                throw new IllegalArgumentException(String.valueOf(new StringBuilder().append("Method ").append(method).append(" has @EventTarget annotation, but takes a argument that is not an Event ").append(class_)));
            }
            arrayList.add(method);
        }
        try {
            if (arrayList.size() > 0) {
                eventHandles.add(new EventHandle(object, arrayList));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
