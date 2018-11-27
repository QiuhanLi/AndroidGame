package ca.uwaterloo.cw.castlewar.Base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.IOException;

public class Recorder {
    private static DisplayMetrics displayMetrics;
    private static MediaProjection mediaProjection;
    private static VirtualDisplay virtualDisplay;
    private static MediaRecorder mediaRecorder;
    private static int screenDensity;
    private static boolean isReady = false;

    private static final int REQUEST_PERMISSIONS = 10;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    public static void initialize(MediaProjection m, WindowManager windowManager) {
        displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mediaProjection = m;
        screenDensity = displayMetrics.densityDpi;

        if (ContextCompat.checkSelfPermission(System.getMainActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(System.getMainActivity(), new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
            ActivityCompat.requestPermissions(System.getMainActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS);
        }
        prepareRecording();
        isReady = true;
    }


    public static void startRecording() {
        if (mediaRecorder == null) prepareRecording();
        virtualDisplay = createVirtualDisplay();
        mediaRecorder.start();
    }

    public static void stopRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
        if (virtualDisplay != null) {
            virtualDisplay.release();
            virtualDisplay = null;
        }
    }

    private static void prepareRecording() {
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        mediaRecorder = new MediaRecorder();
        try {
            // mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(System.getMainActivity().getExternalCacheDir().getAbsolutePath() + "/Combat.mp4");
            mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            // mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setVideoEncodingBitRate(512 * 1000);
            mediaRecorder.setVideoSize(width, height);
            mediaRecorder.setVideoFrameRate(30);
            mediaRecorder.setMaxFileSize(12000000);
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static VirtualDisplay createVirtualDisplay() {
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        return mediaProjection.createVirtualDisplay("Record",
                width, height, screenDensity,
                DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                mediaRecorder.getSurface(), null /*Callbacks*/, null
                /*Handler*/);
    }

    public static boolean isReady() {
        return isReady;
    }

    public static void setReady(boolean ready) {
        isReady = ready;
    }
}
