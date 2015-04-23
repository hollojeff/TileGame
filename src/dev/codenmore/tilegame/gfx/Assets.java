package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage player, dirt, grass, wall, thing;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		player = sheet.crop(0,0,width,height);
		grass = sheet.crop(width,0,width,height);
		dirt = sheet.crop(width*2, 0, width, height);
		wall = sheet.crop(width*3, 0, width, height);
		thing = sheet.crop(0, height, width, height);
		
	}
	
}
