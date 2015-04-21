package me.soxey6.engine.objects;
/**
 * This is an object I used because I completly forgot about the Color object that's provided in the default java libarary.
 * To be removed in version DEV-0.0.2
 * @author Pat Childs || Soxey6
 * @see <a href="http://docs.oracle.com/javase/7/docs/api/java/awt/Color.html">Color (Default library)</a> || <a href="http://legacy.lwjgl.org/javadoc/org/lwjgl/util/Color.html">Color (LWJGL Util)</a>
 * @deprecated Please do not use this. <b>See: </b> <a href="http://docs.oracle.com/javase/7/docs/api/java/awt/Color.html">Color (Default library)</a> || <a href="http://legacy.lwjgl.org/javadoc/org/lwjgl/util/Color.html">Color (LWJGL Util)</a>
 *
 */
public class RGB {
	private float r;
	private float g;
	private float b;
	
	public RGB(float r, float g, float b)
	{
		this.r=r;
		this.g=g;
		this.b=b;
	}
	
	/**
	 * @return float R (Red value)
	 */
	public float getR() {
		return r;
	}
	/**
	 * @return float G (Green value)
	 */
	public float getG() {
		return g;
	}
	/**
	 * @return float B (Blue value)
	 */
	public float getB() {
		return b;
	}
	/**
	 * @param float to set the r
	 */
	public void setR(float r) {
		this.r = r;
	}
	/**
	 * @param float to set the g
	 */
	public void setG(float g) {
		this.g = g;
	}
	/**
	 * @param float to set the b
	 */
	public void setB(float b) {
		this.b = b;
	}
}
