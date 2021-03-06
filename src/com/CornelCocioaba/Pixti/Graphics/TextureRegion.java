package com.CornelCocioaba.Pixti.Graphics;

import java.nio.FloatBuffer;

public class TextureRegion {

	private Texture texture;
	public float u1, v1, u2, v2;
	private float x, y, width, height;
	
	private FloatBuffer uvBuffer;

	public TextureRegion(Texture texture) {
		this(texture, 0, 0, texture.mWidth, texture.mHeight);
	}

	public TextureRegion(Texture texture, float x, float y, float width, float height) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		float invWidth = 1f / texture.mWidth;
		float invHeight = 1f / texture.mHeight;
		
		this.setUvs(x * invWidth, y * invHeight, (x + width) * invWidth, (y + height) * invHeight);
		
		this.uvBuffer = BufferUtils.createFloatBuffer(getUVs());
	}
	
	public static TextureRegion[] CreateTextureRegionsFromTiledTexture(Texture texture, int rows, int columns) {
		TextureRegion[] regions = new TextureRegion[rows * columns];

		int regionWidth = texture.mWidth / columns;
		int regionHeight = texture.mHeight / rows;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int index = i * columns + j;
				int x = j * regionWidth;
				int y = i * regionHeight;
				regions[index] = new TextureRegion(texture, x, y, regionWidth, regionHeight);
			}
		}

		return regions;
	}
	
	public TextureRegion deepCopy(){
		return new TextureRegion(this.texture, this.x, this.y, this.width, this.height);
	}
	
	public float[] getUVs() {
		return new float[] { 
				u1, v2,
				u1, v1, 
				u2, v1,
				u2, v2
				};
		//return new float[] { u2, v1, u2, v2, u1, v1, u1, v2 };
	}

	public void setUvs(float u1, float v1, float u2, float v2) {
		this.u1 = u1;
		this.v1 = v1;
		this.u2 = u2;
		this.v2 = v2;
	}

	public FloatBuffer getUvBuffer(){
		return uvBuffer;
	}
	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getU1() {
		return u1;
	}

	public void setU1(float u1) {
		this.u1 = u1;
	}

	public float getV1() {
		return v1;
	}

	public void setV1(float v1) {
		this.v1 = v1;
	}

	public float getU2() {
		return u2;
	}

	public void setU2(float u2) {
		this.u2 = u2;
	}

	public float getV2() {
		return v2;
	}

	public void setV2(float v2) {
		this.v2 = v2;
	}

}
