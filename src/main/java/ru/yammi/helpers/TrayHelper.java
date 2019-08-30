package ru.yammi.helpers;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import ru.yammi.Main;

public class TrayHelper {

    public static void displayTray() {
        try {
            if (SystemTray.isSupported()) {
                SystemTray systemTray = SystemTray.getSystemTray();
                Image image = Toolkit.getDefaultToolkit().getImage("images/tray.gif");
                TrayIcon trayIcon = new TrayIcon(image, String.valueOf(new StringBuilder().append("Yammi b").append(Main.getInstance().getVersion())));
                trayIcon.setImageAutoSize(true);
                trayIcon.setToolTip(String.valueOf(new StringBuilder().append("Yammi b").append(Main.getInstance().getVersion())));
                systemTray.add(trayIcon);
                trayIcon.displayMessage(String.valueOf(new StringBuilder().append("Yammi b").append(Main.getInstance().getVersion())), "\u0427\u0438\u0442 \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d! \u0423\u0434\u0430\u0447\u043d\u043e\u0439 \u0438\u0433\u0440\u044b!", TrayIcon.MessageType.INFO);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
