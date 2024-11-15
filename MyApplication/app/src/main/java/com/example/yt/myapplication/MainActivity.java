package com.example.yt.myapplication;

import android.opengl.GLSurfaceView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class LibraryClass {
    static
    {
        System.loadLibrary("nk-gl");
    }

    public static native void init();
    public static native void resize(int width, int height);
    public static native void render();
}

class DemoProjectRenderer implements GLSurfaceView.Renderer {

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        LibraryClass.init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int w, int h) {
        LibraryClass.resize(w, h);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        LibraryClass.render();
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        GLSurfaceView view = new GLSurfaceView(getApplicationContext());

        // Tell EGL to use a ES 2.0 Context
        view.setEGLContextClientVersion(2);

        // Set the renderer
        view.setRenderer(new DemoProjectRenderer());

        setContentView(view);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
