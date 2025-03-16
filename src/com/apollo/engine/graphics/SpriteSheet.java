package com.apollo.engine.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteSheet {

  private BufferedImage sheet;

  public SpriteSheet(String path) {
    try {
      this.sheet = ImageIO.read(getClass().getResourceAsStream(path));
    } catch (Exception exception) {
      exception.printStackTrace(System.out);
    }
  }

  public BufferedImage getSprite(int x, int y, int width, int height) {
    return sheet.getSubimage(x * width, y * height, width, height);
  }

  public BufferedImage getSheet() {
    return sheet;
  }

  public void setSheet(BufferedImage sheet) {
    this.sheet = sheet;
  }
}