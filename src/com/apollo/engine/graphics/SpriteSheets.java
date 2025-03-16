package com.apollo.engine.graphics;

import java.io.File;
import java.util.HashMap;

public class SpriteSheets {

  /**
   * A key to value table that maps sheets to their file names.
   */
  private final HashMap<String, SpriteSheet> sheets = new HashMap<String, SpriteSheet>();

  /**
   * A singleton reference point to restrict multiple instantiations of this class
   * file.
   */
  private static final SpriteSheets singleton = new SpriteSheets();

  /**
   * Loads all sheet files within a directory.
   * 
   * @param directory The path to the directory.
   */
  public void load(String directory) {

    final File folder = new File(directory);

    if (folder.isDirectory()) {

      final File[] files = folder.listFiles();

      if (files == null)
        return;

      for (File file : files) {
        if (!file.getName().endsWith(".png") && !file.getName().endsWith(".jpg"))
          continue;
        sheets.put(file.getName(), new SpriteSheet(file.getAbsolutePath()));
      }
    }
  }

  public HashMap<String, SpriteSheet> getSheets() {
    return sheets;
  }

  public static SpriteSheets getSingleton() {
    return singleton;
  }
}
