/*
 * TrayIconDemo.java
 */
 
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
 
public class TrayIconDemo {
	
    private static Wetter weather = new Wetter();

    private static void createAndShowGUI() {
        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon =
                new TrayIcon(createImage("logo.gif", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();
         
        // Create a popup menu components
        MenuItem aboutItem = new MenuItem("About");
        MenuItem exitItem = new MenuItem("Exit");
         
        //Add components to popup menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(exitItem);
         
        trayIcon.setPopupMenu(popup);
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Geomar Wetterdaten");
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
         
        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This dialog box is run from System Tray");
            }
        });
         
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Windgeschwindigkeit: " + weather.getWindspeed() + "m/s\n" + 
                        "Windrichtung: " + weather.getDirection() + "° " + 
                        		           weather.calcDir(weather.getDirection()) );
            }
        });
        
        
         
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }
     
    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = TrayIconDemo.class.getResource(path);
         
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
    
    public static void main(String[] args) {
   	 /* Use an appropriate Look and Feel */
       try {
           UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
           //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
       } catch (UnsupportedLookAndFeelException ex) {
           ex.printStackTrace();
       } catch (IllegalAccessException ex) {
           ex.printStackTrace();
       } catch (InstantiationException ex) {
           ex.printStackTrace();
       } catch (ClassNotFoundException ex) {
           ex.printStackTrace();
       }
       /* Turn off metal's use of bold fonts */
       UIManager.put("swing.boldMetal", Boolean.FALSE);
       //Schedule a job for the event-dispatching thread:
       //adding TrayIcon.
       SwingUtilities.invokeLater(new Runnable() {
           public void run() {
           	   weather = new Wetter();
               createAndShowGUI();
           }
       });
   }
}