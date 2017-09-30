package thinkreed.testexo;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler mainHandler = new Handler();
        PcmAudioTrackRender audioRender = new PcmAudioTrackRender(MediaCodecSelector.DEFAULT);
        TrackSelector trackSelector = new DefaultTrackSelector();
        ExoPlayer player = ExoPlayerFactory.newInstance(new Renderer[]{audioRender}, trackSelector);
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "yourApplicationName"), bandwidthMeter);
        MediaSource audioSource = new HlsMediaSource(Uri.parse("http://ls.qingting.fm/live/386.m3u8"),
                dataSourceFactory, mainHandler, null);
        player.prepare(audioSource);
        player.setPlayWhenReady(true);
    }
}
