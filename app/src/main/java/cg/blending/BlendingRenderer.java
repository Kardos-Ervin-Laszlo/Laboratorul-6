package cg.blending;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class BlendingRenderer implements GLSurfaceView.Renderer {
    private final Context context;
    private final Square square;
    private float transY;

    public BlendingRenderer(Context context) {
        this.context = context;
        this.square = new Square();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Pentru blending corect in exemplul de laborator, depth test este dezactivat.
        gl.glDisable(GL10.GL_DEPTH_TEST);
        gl.glShadeModel(GL10.GL_SMOOTH);

        square.createTexture(gl, context, R.drawable.hedly);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();

        float ratio = (float) width / (float) height;
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);

        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // Patrat albastru, in spate, miscat vertical.
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, (float) Math.sin(transY), -3.0f);
        gl.glColor4f(0.0f, 0.0f, 1.0f, 0.65f);
        square.drawColor(gl);

        // Patrat rosu, in fata, miscat orizontal.
        gl.glLoadIdentity();
        gl.glTranslatef((float) Math.sin(transY) / 2.0f, 0.0f, -2.9f);
        gl.glColor4f(1.0f, 0.0f, 0.0f, 0.50f);
        square.drawColor(gl);

        // Patrat texturat transparent.
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, -0.15f, -2.8f);
        gl.glScalef(0.75f, 0.75f, 1.0f);
        gl.glColor4f(1.0f, 1.0f, 1.0f, 0.75f);
        square.drawTexture(gl);

        // Pentru experiment GL_ONE + GL_ONE:
        // schimba glBlendFunc de mai sus in:
        // gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE);

        // Pentru experiment glColorMask:
        // gl.glColorMask(true, false, true, true);

        gl.glDisable(GL10.GL_BLEND);
        transY += 0.075f;
    }
}
