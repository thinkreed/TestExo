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

import android.content.Context;
import android.net.Uri;
import android.os.Handler;

/**
 * Created by thinkreed on 2017/10/1.
 */

public class AudioOnlyPlayer implements PcmAudioTrackRender.MediaFrameConsumer {

    private ExoPlayer player;

    public void init(Context context) {
        Handler mainHandler = new Handler();
        PcmAudioTrackRender audioRender = new PcmAudioTrackRender(MediaCodecSelector.DEFAULT);
        TrackSelector trackSelector = new DefaultTrackSelector();
        player = ExoPlayerFactory.newInstance(new Renderer[]{audioRender}, trackSelector);
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "yourApplicationName"), bandwidthMeter);
        MediaSource audioSource = new HlsMediaSource(Uri.parse("http://ls.qingting.fm/live/386.m3u8"),
                dataSourceFactory, mainHandler, null);
        player.prepare(audioSource);
        player.setPlayWhenReady(true);
    }

    public void release() {

    }

    @Override
    public void onFrameRetrieved(Pair pair) {

    }
}
