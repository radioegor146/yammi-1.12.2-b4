package ru.yammi.helpers;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.Timer;

public class ReflectionHelper {

    public static MethodHandles.Lookup lookup;

    public static Field getField(Class class_, String... arrstring) {
        for (Field field : class_.getDeclaredFields()) {
            field.setAccessible(true);
            for (String string : arrstring) {
                if (!field.getName().equals(string)) {
                    continue;
                }
                return field;
            }
        }
        return null;
    }

    public static MethodHandle getLookupMethod(Class class_, MethodType methodType, String... arrstring) throws Throwable {
        for (String string : arrstring) {
            MethodHandle methodHandle = ReflectionHelper.lookup().findVirtual(class_, string, methodType);
            if (methodHandle == null) {
                continue;
            }
            return methodHandle;
        }
        return null;
    }

    public static Method getMethod(Class class_, String... arrstring) {
        for (Method method : class_.getDeclaredMethods()) {
            method.setAccessible(true);
            for (String string : arrstring) {
                if (!method.getName().equals(string)) {
                    continue;
                }
                return method;
            }
        }
        return null;
    }

    public static float getPartialTicks() throws IllegalAccessException, Throwable {
        Field field = ReflectionHelper.getField(Minecraft.class, "timer", "field_71428_T", "Y");
        Timer timer = (Timer) ReflectionHelper.lookup().unreflectGetter(field).invoke(Minecraft.getMinecraft());
        return timer.renderPartialTicks;
    }

    public static double getRenderPosX() throws Throwable, IllegalAccessException {
        return (double) ReflectionHelper.lookup().unreflectGetter(ReflectionHelper.getField(RenderManager.class, "renderPosX", "field_78725_b", "o")).invoke(Minecraft.getMinecraft().getRenderManager());
    }

    public static double getRenderPosY() throws IllegalAccessException, Throwable {
        return (double) ReflectionHelper.lookup().unreflectGetter(ReflectionHelper.getField(RenderManager.class, "renderPosY", "field_78726_c", "p")).invoke(Minecraft.getMinecraft().getRenderManager());
    }

    public static double getRenderPosZ() throws IllegalAccessException, Throwable {
        return (double) ReflectionHelper.lookup().unreflectGetter(ReflectionHelper.getField(RenderManager.class, "renderPosZ", "field_78723_d", "q")).invoke(Minecraft.getMinecraft().getRenderManager());
    }

    public static void hookField(Field field, Object object, Object object2) throws IllegalAccessException, Throwable {
        ReflectionHelper.lookup().unreflectSetter(field).invoke(object, object2);
    }

    public static Object invoke(Method method, Object... arrobject) throws Throwable {
        return ReflectionHelper.lookup().unreflect(method).invoke(arrobject);
    }

    public static MethodHandles.Lookup lookup() {
        return lookup;
    }

    public static MethodHandle unreflect(Method method) throws IllegalAccessException {
        return ReflectionHelper.lookup().unreflect(method);
    }
}
