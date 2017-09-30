package thinkreed.testexo;

import java.nio.ByteBuffer;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;

import android.media.MediaCodec;

/**
 * Created by thinkreed on 2017/9/30.
 */

public class PcmAudioTrackRender extends MediaCodecAudioRenderer {

    private boolean isConnected = false;

    public PcmAudioTrackRender(MediaCodecSelector mediaCodecSelector) {
        super(mediaCodecSelector);
    }

    @Override
    protected boolean processOutputBuffer(long positionUs, long elapsedRealtimeUs, MediaCodec codec, ByteBuffer buffer,
                                          int bufferIndex, int bufferFlags, long bufferPresentationTimeUs,
                                          boolean shouldSkip) throws ExoPlaybackException {

        if (isConnected) {
            int size = buffer.remaining();
            byte[] chunk = new byte[size];
            buffer.get(chunk);
            buffer.clear();
            codec.releaseOutputBuffer(bufferIndex, false);
            return true;
        }

        return super.processOutputBuffer(positionUs, elapsedRealtimeUs, codec, buffer, bufferIndex, bufferFlags,
                bufferPresentationTimeUs, shouldSkip);
    }
}
