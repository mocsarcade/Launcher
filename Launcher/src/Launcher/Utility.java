package Launcher;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;

public class Utility {
	
  public static Image Transparent(BufferedImage image)
  {
    ImageFilter filter = new RGBImageFilter()
    {
      public final int filterRGB(int x, int y, int rgb)
      {
        //return (rgb << 8) & 0xFFFFFFFF;
    	//Right now does nothing. If you want to make any color transparent, please do
        return rgb;
      }
    };

    ImageProducer ip = new FilteredImageSource(image.getSource(), filter);
      return Toolkit.getDefaultToolkit().createImage(ip);
  }

}
