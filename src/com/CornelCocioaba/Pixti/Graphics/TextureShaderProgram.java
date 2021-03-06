package com.CornelCocioaba.Pixti.Graphics;

import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDisableVertexAttribArray;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glVertexAttribPointer;

import java.nio.FloatBuffer;

import android.opengl.GLES20;

public class TextureShaderProgram extends ShaderProgram {

	private static final String DEFAULT_TEXTURE_VERTEX_SHADER = 
			  "uniform mat4 " + U_MATRIX + ";" 
			+ "attribute vec4 "	+ A_POSITION + ";" 
			+ "attribute vec4 " + A_COLOR + ";" 
			+ "varying vec4 " + V_COLOR + ";"
			+ "attribute vec2 " + A_TEXTURE_COORDINATES + ";" 
			+ "varying vec2 " + V_TEXTURE_COORDINATES + ";"
			+ "void main(){" 
				+ V_COLOR + " = " + A_COLOR + ";" 
				+ V_TEXTURE_COORDINATES + " = " + A_TEXTURE_COORDINATES	+ ";" 
				+ "gl_Position = " + U_MATRIX + " * " + A_POSITION + ";" 
			+ "}";

	private static final String DEFAULT_TEXTURE_FRAGMENT_SHADER = 
			"precision mediump float;" 
			+ "uniform sampler2D " + U_TEXTURE_UNIT + ";" 
			+ "varying vec4 " + V_COLOR + ";" 
			+ "varying vec2 " + V_TEXTURE_COORDINATES + ";"
			+ "void main(){" 
				+ "gl_FragColor = texture2D(" + U_TEXTURE_UNIT + ", " + V_TEXTURE_COORDINATES + ") * " + V_COLOR + ";" 
			+ "}";

	private final int uMatrixLocation;
	private final int uTextureUnitLocation;

	private final int aPositionLocation;
	private final int aColorLocation;
	private final int aTextureCoordinatesLocation;

	public TextureShaderProgram() {
		this(DEFAULT_TEXTURE_VERTEX_SHADER, DEFAULT_TEXTURE_FRAGMENT_SHADER);
	}

	public TextureShaderProgram(String vertexShader, String fragmentShader) {
		super(vertexShader, fragmentShader);

		uMatrixLocation = glGetUniformLocation(program, U_MATRIX);
		uTextureUnitLocation = glGetUniformLocation(program, U_TEXTURE_UNIT);

		aPositionLocation = glGetAttribLocation(program, A_POSITION);
		aColorLocation = glGetAttribLocation(program, A_COLOR);
		aTextureCoordinatesLocation = glGetAttribLocation(program, A_TEXTURE_COORDINATES);
	}

	public void setTextureId(float[] matrix, int textureId) {
		glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

		glActiveTexture(GL_TEXTURE0);

		glBindTexture(GL_TEXTURE_2D, textureId);

		glUniform1i(uTextureUnitLocation, 0);
	}

	public void setVertexBufferPointer(FloatBuffer vertexBuffer) {
		GLES20.glVertexAttribPointer(aPositionLocation, GLConstants.COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, 0,
				vertexBuffer);
	}

	public void setColorBufferPointer(FloatBuffer colorBuffer) {
		GLES20.glVertexAttribPointer(aColorLocation, GLConstants.COLORS_PER_VERTEX, GLES20.GL_FLOAT, false, 0,
				colorBuffer);
	}

	public void setTextureCoordinatesPointer(FloatBuffer uvBuffer) {
		glVertexAttribPointer(aTextureCoordinatesLocation, 2, GLES20.GL_FLOAT, false, 0, uvBuffer);
	}

	public int getPositionAttributeLocation() {
		return aPositionLocation;
	}
	
	public int getColorAttributeLocation(){
		return aColorLocation;
	}

	public int getTextureCoordinatesAttributeLocation() {
		return aTextureCoordinatesLocation;
	}

	public void enablePositionAttribute() {
		glEnableVertexAttribArray(aPositionLocation);
	}

	public void disablePositionAttribute() {
		glDisableVertexAttribArray(aPositionLocation);
	}

	public void enableColorAttribute() {
		glEnableVertexAttribArray(aColorLocation);
	}

	public void disableColorAttribute() {
		glDisableVertexAttribArray(aColorLocation);
	}

	public void enableTextureCoordinatesAttribute() {
		glEnableVertexAttribArray(aTextureCoordinatesLocation);
	}

	public void disableTextureCoordinatesAttribute() {
		glDisableVertexAttribArray(aTextureCoordinatesLocation);
	}

}
