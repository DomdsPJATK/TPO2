/**
 *
 *  @author Suchner Dominik S19036
 *
 */

package zad1;


import zad1.Utils.Service;
import zad1.View.SwingGui;

import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    Service s = new Service("Poland");
    String weatherJson = s.getWeather("Warsaw");
    Double rate1 = s.getRateFor("USD");
    Double rate2 = s.getNBPRate();

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        SwingGui gui = new SwingGui(800,700,"Aktualne dane państw");
      }
    });
  }
}
