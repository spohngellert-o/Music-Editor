package cs3500.music.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events

import javax.swing.*;

import cs3500.music.controller.KeyboardHandler;
import cs3500.music.model.SongEditor;

/**
 * A frame for the GUI representation of the model
 */
public class GuiViewFrame extends JFrame implements GuiView {
  private final ConcreteGuiViewPanel displayPanel;
  private final JScrollPane scrollPane;

  /**
   * Creates new GuiView0
   *
   * @param s the song editor to be displayed
   */
  public GuiViewFrame(SongEditor s) {
    this.displayPanel = new ConcreteGuiViewPanel(s);
    this.scrollPane = new JScrollPane(displayPanel);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(scrollPane);
    this.pack();
  }

  /**
   * Sets features of the GUI to be visible to the user
   */
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public void render() {
    this.initialize();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1000, 500);
  }

  @Override
  public void renderAtBeat() {
    JViewport viewport = this.scrollPane.getViewport();
    if (viewport.getViewPosition().getX() + this.getWidth() - 60 <
            this.displayPanel.getBeatPositionX()) {
      viewport.setViewPosition(new Point(this.displayPanel.getBeatPositionX(), viewport.getY()));
      this.scrollPane.setViewport(viewport);
    }
    this.displayPanel.renderAtBeat();
  }

  @Override
  public void movePosition(int factor, boolean orientation) {
    if (orientation) {
      int current = this.scrollPane.getHorizontalScrollBar().getValue();
      this.scrollPane.getHorizontalScrollBar().setValue(current + factor);
    } else {
      int current = this.scrollPane.getVerticalScrollBar().getValue();
      this.scrollPane.getVerticalScrollBar().setValue(current + factor);
    }
  }

  @Override
  public void goHome() {
    JViewport viewport = this.scrollPane.getViewport();
    viewport.setViewPosition(new Point(0, 0));
    this.scrollPane.setViewport(viewport);
  }

  @Override
  public void goEnd() {
    JViewport viewport = this.scrollPane.getViewport();
    Dimension dimension = this.displayPanel.getPreferredSize();
    viewport.setViewPosition(new Point((int) dimension.getWidth(), 0));
    this.scrollPane.setViewport(viewport);
  }

  @Override
  public void addMouseListener(MouseListener listener) {
    this.displayPanel.addMouseListener(listener);
  }
}